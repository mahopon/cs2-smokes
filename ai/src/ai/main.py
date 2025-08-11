from fastapi import FastAPI, status
import logging
import time
from fastapi.responses import JSONResponse
from starlette.middleware.base import BaseHTTPMiddleware, RequestResponseEndpoint
from starlette.middleware.gzip import GZipMiddleware
from starlette.requests import Request
from starlette.responses import Response, StreamingResponse
from pydantic import ValidationError
from .api import api_router

app = FastAPI(title="CS2-Smokes-AI", docs_url="/docs")
log = logging.getLogger(__name__)


def get_path_template(request: Request) -> str:
    if hasattr(request, "path"):
        return ",".join(request.path.split("/")[1:])
    return ".".join(request.url.path.split("/")[1:])


class MetricsMiddleware(BaseHTTPMiddleware):
    async def dispatch(
        self, request: Request, call_next: RequestResponseEndpoint
    ) -> Response:
        path_template = get_path_template(request)

        method = request.method
        tags = {"method": method, "endpoint": path_template}

        try:
            start = time.perf_counter()
            response = await call_next(request)
            elapsed_time = time.perf_counter() - start
            tags.update({"status_code": response.status_code})
            log.debug(f"server.call.elapsed.{path_template}: {elapsed_time}")
        except Exception as e:
            raise e from None
        return response


class ExceptionMiddleware(BaseHTTPMiddleware):
    async def dispatch(
        self, request: Request, call_next: RequestResponseEndpoint
    ) -> StreamingResponse:
        try:
            response = await call_next(request)
        except ValidationError as e:
            log.exception(e)
            response = JSONResponse(
                status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                content={"detail": e.errors()},
            )
        except ValueError as e:
            log.exception(e)
            response = JSONResponse(
                status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                content={
                    "detail": [
                        {"msg": "Unknown", "loc": ["Unknown"], "type": "Unknown"}
                    ]
                },
            )
        except Exception as e:
            log.exception(e)
            response = JSONResponse(
                status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
                content={
                    "detail": [
                        {"msg": "Unknown", "loc": ["Unknown"], "type": "Unknown"}
                    ]
                },
            )

        return response


app.add_middleware(GZipMiddleware, minimum_size=1000)
app.add_middleware(ExceptionMiddleware)
app.add_middleware(MetricsMiddleware)
app.include_router(api_router)

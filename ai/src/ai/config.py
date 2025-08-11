import base64
import logging
import os
from urllib import parse

from pydantic import BaseModel
from starlette.config import Config
from starlette.datastructures import CommaSeparatedStrings

log = logging.getLogger(__name__)

config = Config(".env")

SECRET_PROVIDER = config("SECRET_PROVIDER", default=None)
if SECRET_PROVIDER == "metatron-secret":
    import metatron.decrypt

    class Secret:
        """
        Holds a string value that should not be revealed in tracebacks etc.
        You should cast the value to `str` at the point it is required.
        """

        def __init__(self, value: str):
            self._value = value
            self._decrypted_value = (
                metatron.decrypt.MetatronDecryptor()
                .decryptBytes(base64.b64decode(self._value))
                .decode("utf-8")
            )

        def __repr__(self) -> str:
            class_name = self.__class__.__name__
            return f"{class_name}('**********')"

        def __str__(self) -> str:
            return self._decrypted_value

elif SECRET_PROVIDER == "kms-secret":
    import boto3

    class Secret:
        """
        Holds a string value that should not be revealed in tracebacks etc.
        You should cast the value to `str` at the point it is required.
        """

        def __init__(self, value: str):
            self._value = value
            self._decrypted_value = (
                boto3.client("kms")
                .decrypt(CiphertextBlob=base64.b64decode(value))["Plaintext"]
                .decode("utf-8")
            )

        def __repr__(self) -> str:
            class_name = self.__class__.__name__
            return f"{class_name}('**********')"

        def __str__(self) -> str:
            return self._decrypted_value

else:
    from starlette.datastructures import Secret

# database
DATABASE_HOSTNAME = config("DATABASE_HOSTNAME")
DATABASE_CREDENTIALS = config("DATABASE_CREDENTIALS", cast=Secret)
# this will support special chars for credentials
_DATABASE_CREDENTIAL_USER, _DATABASE_CREDENTIAL_PASSWORD = str(
    DATABASE_CREDENTIALS
).split(":")
_QUOTED_DATABASE_PASSWORD = parse.quote(str(_DATABASE_CREDENTIAL_PASSWORD))
DATABASE_NAME = config("DATABASE_NAME", default="dispatch")
DATABASE_PORT = config("DATABASE_PORT", default="5432")
DATABASE_ENGINE_MAX_OVERFLOW = config(
    "DATABASE_ENGINE_MAX_OVERFLOW", cast=int, default=10
)
# Deal with DB disconnects
# https://docs.sqlalchemy.org/en/20/core/pooling.html#pool-disconnects
DATABASE_ENGINE_POOL_PING = config("DATABASE_ENGINE_POOL_PING", default=False)
DATABASE_ENGINE_POOL_RECYCLE = config(
    "DATABASE_ENGINE_POOL_RECYCLE", cast=int, default=3600
)
DATABASE_ENGINE_POOL_SIZE = config("DATABASE_ENGINE_POOL_SIZE", cast=int, default=20)
DATABASE_ENGINE_POOL_TIMEOUT = config(
    "DATABASE_ENGINE_POOL_TIMEOUT", cast=int, default=30
)
SQLALCHEMY_DATABASE_URI = f"postgresql+psycopg2://{_DATABASE_CREDENTIAL_USER}:{_QUOTED_DATABASE_PASSWORD}@{DATABASE_HOSTNAME}:{DATABASE_PORT}/{DATABASE_NAME}"

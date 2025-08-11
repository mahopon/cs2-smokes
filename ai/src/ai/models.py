from datetime import datetime, timedelta, timezone
from sqlalchemy import Boolean, Column, DateTime, Integer, String, event, ForeignKey
from pydantic import BaseModel
from pydantic import EmailStr


class TimeStampMixin(object):
    """Timestamping mixin for created_at and updated_at fields."""

    created_at = Column(DateTime, default=lambda: datetime.now(timezone.utc))
    created_at._creation_order = 9998
    updated_at = Column(DateTime, default=lambda: datetime.now(timezone.utc))
    updated_at._creation_order = 9998

    @staticmethod
    def _updated_at(mapper, connection, target):
        """Updates the updated_at field to the current UTC time."""
        target.updated_at = datetime.now(timezone.utc)

    @classmethod
    def __declare_last__(cls):
        """Registers the before_update event to update the updated_at field."""
        event.listen(cls, "before_update", cls._updated_at)


class Pagination(BaseModel):
    """Pydantic model for paginated results."""

    itemsPerPage: int
    page: int
    total: int


class ContactBase(BaseModel):
    """Base Pydantic model for contact-related fields."""

    email: EmailStr
    name: str | None = None
    is_active: bool | None = True
    is_external: bool | None = False
    company: str | None = None
    contact_type: str | None = None
    notes: str | None = None
    owner: str | None = None

package com.web.backend.exception.enums;

public enum FriendlyMessageCodes implements IFriendlyMessageCode {
    OK(1000),
    ERROR(1001),
    SUCCESS(1002),

    PRODUCT_NOT_CREATED_EXCEPTION(1500),
    PRODUCT_SUCCESSFULLY_CREATED(1501),
    PRODUCT_NOT_FOUND_EXCEPTION(1502),
    PRODUCT_SUCCESSFULLY_UPDATED(1503),
    PRODUCT_ALREADY_DELETED(1504),
    PRODUCT_SUCCESSFULLY_DELETED(1505),

    NOT_CREATED_EXCEPTION(1506),

    SUCCESSFULLY_CREATED(1507),
    SUCCESSFULLY_UPDATED(1508),
    SUCCESSFULLY_DELETED(1509),
    NOT_FOUND_EXCEPTION(1510),
    ALREADY_DELETED(1511),

    NOT_EMPTY(1512)
    ;

    private final int value;

    FriendlyMessageCodes(int value) {
        this.value = value;
    }

    @Override
    public int getFriendlyMessageCode() {
        return value;
    }
}

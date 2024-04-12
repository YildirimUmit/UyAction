package com.web.backend.enums;

import javax.persistence.*;

public enum Size {
    ACTIVATED(1), DEACTIVATED(2), SUSPENDED(3);

    int sizeId;

    private Size(int size) {
        this.sizeId = sizeId;
    }

    public int getSizeId() {
        return sizeId;
    }
}

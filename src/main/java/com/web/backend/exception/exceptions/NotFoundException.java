package com.web.backend.exception.exceptions;


import com.web.backend.enums.*;
import com.web.backend.exception.enums.*;
import com.web.backend.exception.utils.*;
import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
@Getter
public class NotFoundException extends RuntimeException {
    private final Language language;
    private final IFriendlyMessageCode friendlyMessageCode;

    public NotFoundException(Language language, IFriendlyMessageCode friendlyMessageCode, String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode));
        this.language = language;
        this.friendlyMessageCode = friendlyMessageCode;
        log.error("[NotFoundException] -> message:{} developer message: {}", FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode), message);
    }
}

package com.web.backend.model;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class MaileSendInfo {
    String maileAddress;
    String sendInfo;
    boolean sendError=false;
    String maileMessage;

}

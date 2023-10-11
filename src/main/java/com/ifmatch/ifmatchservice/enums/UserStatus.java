package com.ifmatch.ifmatchservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserStatus {

    FORA_CLINICA("1", "FORA_CLINICA"),
    AGUARDANDO_ATENDIMENTO("2", "AGUARDANDO_ATENDIMENTO");

    @Getter
    private String value;

    @Getter
    private String description;
}

package com.ifmatch.ifmatchservice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;

@AllArgsConstructor
public enum UserStatus implements EnumValue {

    FORA_CLINICA("1", "FORA_CLINICA"),
    AGUARDANDO_ATENDIMENTO("2", "AGUARDANDO_ATENDIMENTO");

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static synchronized UserStatus create(final HashMap<?, ?> value) {
        return UserStatus.getByName(value.get("name").toString());
    }

    public static synchronized UserStatus getByName(final String name) {
        return Arrays.stream(UserStatus.values()).filter(filter -> filter.name().equals(name)).findFirst().orElse(null);
    }

    private String value;

    private String description;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String getName() {
        return super.name();
    }
}

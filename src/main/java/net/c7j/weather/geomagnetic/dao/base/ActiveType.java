package net.c7j.weather.geomagnetic.dao.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.EnumSet;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum ActiveType {

    ENABLED(true),
    DELETED(false);

    private final boolean active;

    public static ActiveType activeOf(boolean active) {
        return EnumSet.allOf(ActiveType.class)
                .stream()
                .filter(it -> Objects.equals(it.active, active))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public String toString() {
        return String.valueOf(active);
    }
}
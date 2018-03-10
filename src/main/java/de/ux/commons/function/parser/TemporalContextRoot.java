package de.ux.commons.function.parser;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TemporalContextRoot {

    private final ZoneId zone;

    public TemporalContextRoot(ZoneId zone) {
        this.zone = zone;
    }

    public OffsetDateTime today() {
        return OffsetDateTime.now(zone);
    }

    public OffsetDateTime tomorrow() {
        return today().plusDays(1);
    }

    public OffsetDateTime yesterday() {
        return today().minusDays(1);
    }

    public DateTimeFormatter formatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

    public DateTimeFormatter as(String pattern) {
        return formatter(pattern);
    }

}
package de.ux.commons.function.parser;

import java.time.ZoneId;

import static java.time.ZoneId.of;

public class TemporalContextExpressionParser extends ContextExpressionParser {

    private final static ZoneId DEFAULT_ZONE = of("Europe/Berlin");

    public TemporalContextExpressionParser() {
        this(DEFAULT_ZONE);
    }

    public TemporalContextExpressionParser(ZoneId zone) {
        super(new TemporalContextRoot(zone));
    }

}
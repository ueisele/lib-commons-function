package de.ux.commons.function.replacer;

import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class ReplacementDefinition {

    private final Pattern pattern;
    private final Function<MatchResult, String> function;

    public ReplacementDefinition(ReplacementDefinition patternDefinition, Function<String, Object> function) {
        this(patternDefinition.getPattern(), Function.<MatchResult>identity()
                .andThen(patternDefinition.getFunction())
                .andThen(function)
                .andThen(String::valueOf));
    }

    public ReplacementDefinition(Pattern pattern, int matchGroup) {
        this(pattern, (match) -> match.group(matchGroup));
    }

    public ReplacementDefinition(Pattern pattern, Function<MatchResult, String> function) {
        this.pattern = pattern;
        this.function = function;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Function<MatchResult, String> getFunction() {
        return function;
    }

}
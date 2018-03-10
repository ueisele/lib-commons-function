package de.ux.commons.function.replacer;

import de.ux.commons.function.function.Function;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class MatchReplacer implements Function<String, String> {

    private final Pattern pattern;
    private final java.util.function.Function<MatchResult, String> replacementFunction;

    public MatchReplacer(ReplacementDefinition replacementDefinition) {
        this(replacementDefinition.getPattern(), replacementDefinition.getFunction());
    }

    public MatchReplacer(Pattern pattern, java.util.function.Function<MatchResult, String> replacementFunction) {
        this.pattern = pattern;
        this.replacementFunction = replacementFunction;
    }

    @Override
    public String apply(String text) {
        Matcher matcher = pattern.matcher(text);
        StringBuffer buffer = new StringBuffer();
        while(matcher.find()) {
            String replacement = replacementFunction.apply(matcher);
            try {
                matcher.appendReplacement(buffer, replacement);
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                throw new ReplacementException(
                        format("The replacement string refers to a capturing group that does not exist in the pattern. %s. " +
                                "The pattern is '%s' and the replacement string is '%s'.", e.getMessage(), pattern.pattern(), replacement),
                        e);
            }
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

}
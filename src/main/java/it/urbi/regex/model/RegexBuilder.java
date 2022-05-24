package it.urbi.regex.model;

import java.util.Objects;

public class RegexBuilder {
    private final StringBuilder regex;

    private RegexBuilder() {
        regex = new StringBuilder();
    }

    public static RegexBuilder getBuilder() {
        return new RegexBuilder();
    }

    public void withPatternPart(PatternPart patternPart) {
        if (patternPart.getMin() == 1) {
            regex.append(patternPart.getType().getPattern().pattern());
        } else {
            var min = patternPart.getMin();
            var max = patternPart.getMax();
            regex.append(String.format("%s{%s}", patternPart.getType().getPattern().pattern(),
                    Objects.equals(min, max) ? String.valueOf(min)
                            : String.format("%s,%s", min, max)));
        }
    }

    public String build() {
        return regex.toString();
    }
}

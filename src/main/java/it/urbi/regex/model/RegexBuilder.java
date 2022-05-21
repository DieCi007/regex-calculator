package it.urbi.regex.model;

import it.urbi.regex.service.RegexService;

import java.util.Objects;

public class RegexBuilder {
    private final StringBuilder regex;

    private RegexBuilder() {
        regex = new StringBuilder();
    }

    public static RegexBuilder getBuilder() {
        return new RegexBuilder();
    }

    public RegexBuilder withPattern(RegexService.PatternType patternType, Integer min, Integer max) {
        addConstraint(patternType, min, max);
        return this;
    }

    private void addConstraint(RegexService.PatternType patternType, Integer min, Integer max) {
        if (min == 1) {
            regex.append(patternType.getPattern().pattern());
        } else {
            regex.append(String.format("%s{%s}", patternType.getPattern().pattern(), Objects.equals(min, max) ? String.valueOf(min)
                    : String.format("%s,%s", min, max)));
        }
    }

    public String build() {
        return regex.toString();
    }
}

package it.urbi.regex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatternPart {
    private Integer min;
    private Integer max;
    private PatternType type;

    public static PatternPart withDefault(Integer minMax, PatternType patternType) {
        return PatternPart.builder()
                .min(minMax)
                .max(minMax)
                .type(patternType)
                .build();
    }

    public void confrontAndUpdate(Integer length) {
        if (min > length) {
            min = length;
        }

        if (max < length) {
            max = length;
        }
    }

    public enum PatternType {
        STRING(Pattern.compile("[A-Z]")), NUMBER(Pattern.compile("\\d"));
        private final Pattern pattern;

        PatternType(Pattern pattern) {
            this.pattern = pattern;
        }

        public Pattern getPattern() {
            return this.pattern;
        }

        public PatternType invert() {
            return this == PatternType.STRING ? PatternType.NUMBER : PatternType.STRING;
        }
    }
}

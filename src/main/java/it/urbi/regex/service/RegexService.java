package it.urbi.regex.service;

import it.urbi.regex.model.MinMaxDto;
import it.urbi.regex.model.RegexBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@Service
public class RegexService {

    /**
     * Takes for granted:
     * Input is a string of only Uppercase letters and numbers
     * Input always begins with a letter
     *
     * @param args arguments to calculate regex from
     * @return calculated regex
     */
    public String calcRegex(List<String> args) {
        Map<Integer, MinMaxDto> partsWithMinMax = new HashMap<>();
        args.forEach(arg -> handleNextPart(PatternType.STRING, arg, partsWithMinMax, 0));
        var regexBuilder = RegexBuilder.getBuilder();
        for (Integer entry : partsWithMinMax.keySet()) {
            var minMaxDto = partsWithMinMax.get(entry);
            var patternType = entry % 2 == 0 ? PatternType.STRING : PatternType.NUMBER;
            regexBuilder.withPattern(patternType, minMaxDto.getMin(), minMaxDto.getMax());
        }
        return regexBuilder.build();
    }

    private void handleNextPart(PatternType type, String arg, Map<Integer, MinMaxDto> parts, Integer partIndex) {
        if (arg.length() == 0) return;
        var length = 0;
        for (int i = 0; i < arg.length(); i++) {
            if (!type.pattern.matcher(String.valueOf(arg.charAt(i))).matches()) {
                break;
            }
            length += 1;
        }

        if (!parts.containsKey(partIndex)) {
            parts.put(partIndex, MinMaxDto.withDefault(length));
        }
        var savedMinMax = parts.get(partIndex);
        if (savedMinMax.getMin() > length) {
            savedMinMax.setMin(length);
        }

        if (savedMinMax.getMax() < length) {
            savedMinMax.setMax(length);
        }

        arg = arg.substring(length);
        type = type.invert();

        handleNextPart(type, arg, parts, partIndex + 1);
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

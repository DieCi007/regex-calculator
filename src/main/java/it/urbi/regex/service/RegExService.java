package it.urbi.regex.service;

import it.urbi.regex.model.PatternPart;
import it.urbi.regex.model.RegexBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RegExService {

    /**
     * Takes for granted:
     * Input is a string of only Uppercase letters and numbers
     * Input always begins with a letter
     *
     * @param args arguments to calculate regex from
     * @return calculated regex
     */
    public String calcRegex(List<String> args) {
        List<PatternPart> patternParts = new ArrayList<>();
        args.forEach(arg -> handleNextPart(PatternPart.PatternType.STRING, arg, patternParts, 0));
        var regexBuilder = RegexBuilder.getBuilder();
        for (PatternPart patternPart : patternParts) {
            regexBuilder.withPatternPart(patternPart);
        }
        return regexBuilder.build();
    }

    private void handleNextPart(PatternPart.PatternType type, String arg, List<PatternPart> parts, Integer partIndex) {
        if (arg.length() == 0) return;
        var length = 0;
        for (int i = 0; i < arg.length(); i++) {
            if (!type.getPattern().matcher(String.valueOf(arg.charAt(i))).matches()) {
                break;
            }
            length += 1;
        }

        if (parts.size() <= partIndex) {
            parts.add(PatternPart.withDefault(length, type));
        } else {
            var savedPatternPart = parts.get(partIndex);
            savedPatternPart.confrontAndUpdate(length);
        }

        arg = arg.substring(length);
        type = type.invert();

        handleNextPart(type, arg, parts, partIndex + 1);
    }

}

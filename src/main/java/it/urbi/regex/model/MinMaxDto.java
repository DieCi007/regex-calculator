package it.urbi.regex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MinMaxDto {
    private Integer min;
    private Integer max;

    public static MinMaxDto withDefault(Integer minMax) {
        return MinMaxDto.builder()
                .min(minMax)
                .max(minMax)
                .build();
    }
}

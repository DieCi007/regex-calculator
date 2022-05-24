package it.urbi.regex.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RegExServiceTest {

    private final RegExService service = new RegExService();

    @Test
    void calcRegex_shouldWork_withTaxCode() {
        var result = service.calcRegex(List.of(
                "TNTTST80A01F205E", "TSPNGK94P11E506W"
        ));
        assertEquals("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]",
                result);
    }

    @Test
    void calcRegex_shouldWork_withVariousLengthCodes() {
        var result = service.calcRegex(List.of(
                "AA123", "BA1234", "AB12345"
        ));
        assertEquals("[A-Z]{2}\\d{3,5}", result);
    }

    @Test
    void calcRegex_shouldWork_withCarPlates() {
        var result = service.calcRegex(List.of(
                "AB123ZZ", "BB742TG", "CF678HG"
        ));
        assertEquals("[A-Z]{2}\\d{3}[A-Z]{2}", result);
    }

}

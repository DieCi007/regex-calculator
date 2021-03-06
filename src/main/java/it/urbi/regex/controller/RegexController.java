package it.urbi.regex.controller;

import it.urbi.regex.service.RegExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegexController {

    private final RegExService service;

    @Autowired
    public RegexController(RegExService service) {
        this.service = service;
    }

    @GetMapping("/regex")
    public String calcRegex(@RequestParam("args") List<String> args) {
        return service.calcRegex(args);
    }
}

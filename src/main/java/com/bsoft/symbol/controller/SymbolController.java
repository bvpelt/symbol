package com.bsoft.symbol.controller;

import com.bsoft.symbol.model.Symbol;
import com.bsoft.symbol.service.SymbolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class SymbolController {
    private final SymbolService symbolService;

    public SymbolController(SymbolService symbolService) {
        this.symbolService = symbolService;
    }

    @RequestMapping("/symbols/{name}")
    public ResponseEntity<?> getSymbol(@PathVariable("name") String name) {
        log.info("Start get: {}", name);
        Symbol symbol = symbolService.findSymbol(name);

        if (symbol != null) {
            return ResponseEntity.ok(symbol);
        } else {
            return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/symbols/lookup/{name}")
    public ResponseEntity<?> lookupSymbol(@PathVariable("name") String name) {
        log.info("Lookup: {}", name);
        List<Symbol> symbol = symbolService.lookupSymbol(name);

        return ResponseEntity.ok(symbol);

    }
}

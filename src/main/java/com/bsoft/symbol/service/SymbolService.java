package com.bsoft.symbol.service;

import com.bsoft.symbol.model.Symbol;
import com.bsoft.symbol.repository.SymbolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SymbolService {
    private final SymbolRepository symbolRepository;

    @Autowired
    public SymbolService(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;
    }

    public Symbol findSymbol(final String name) {
        Symbol symbol = null;
        Optional<Symbol> symbolOptional = symbolRepository.findByName(name);

        if (symbolOptional.isPresent()) {
            symbol = symbolOptional.get();
        }
        return symbol;
    }

    public List<Symbol> lookupSymbol(String name, int limit) {
        List<Symbol> symbolList = symbolRepository.lookUp(name + "%", limit);

        return symbolList;
    }

    public Symbol findSymbolById(Long id) {
        Symbol symbol = null;
        Optional<Symbol> symbolOptional = symbolRepository.findById(id);

        if (symbolOptional.isPresent()) {
            symbol = symbolOptional.get();
        }
        return symbol;
    }

    public List<String> prefixSymbol() {
        return symbolRepository.lookUpPrefix();
    }
}

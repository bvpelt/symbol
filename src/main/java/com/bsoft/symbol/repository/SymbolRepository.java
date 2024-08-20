package com.bsoft.symbol.repository;

import com.bsoft.symbol.model.Symbol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolRepository extends PagingAndSortingRepository<Symbol, Long>,
        CrudRepository<Symbol, Long>,
        JpaSpecificationExecutor<Symbol> {
}

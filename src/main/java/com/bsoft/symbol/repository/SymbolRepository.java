package com.bsoft.symbol.repository;


import com.bsoft.symbol.model.Prefix;

import com.bsoft.symbol.model.Symbol;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SymbolRepository extends PagingAndSortingRepository<Symbol, Long>,
        CrudRepository<Symbol, Long>,
        JpaSpecificationExecutor<Symbol> {

    Optional<Symbol> findByName(final String name);

    @Query(value = "SELECT * FROM Symbol s WHERE s.name LIKE ?1 ORDER BY name LIMIT ?2", nativeQuery = true)
    List<Symbol> lookUp(String name, int limit);

    //@Query(value = "SELECT new Prefix(DISTINCT(SUBSTRING(name, 1, 3)) prefix, type) FROM symbol  ORDER BY prefix", nativeQuery = true)
    @Query(value = "SELECT distinct(substring(name,1,3)) as prefix, type from symbol order by prefix", nativeQuery = true)
    List<Prefix> lookUpPrefix();

}

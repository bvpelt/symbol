package com.bsoft.symbol.repository;

import com.bsoft.symbol.model.Line;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends PagingAndSortingRepository<Line, Long>,
        CrudRepository<Line, Long>,
        JpaSpecificationExecutor<Line> {
}

package com.bsoft.symbol.repository;

import com.bsoft.symbol.model.Area;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends PagingAndSortingRepository<Area, Long>,
        CrudRepository<Area, Long>,
        JpaSpecificationExecutor<Area> {
}

package com.bsoft.symbol.repository;

import com.bsoft.symbol.model.Graphic;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrapphicRepository extends PagingAndSortingRepository<Graphic, Long>,
        CrudRepository<Graphic, Long>,
        JpaSpecificationExecutor<Graphic> {
}

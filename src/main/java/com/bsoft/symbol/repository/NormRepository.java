package com.bsoft.symbol.repository;

import com.bsoft.symbol.model.Norm;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormRepository extends PagingAndSortingRepository<Norm, Long>,
        CrudRepository<Norm, Long>,
        JpaSpecificationExecutor<Norm> {
}

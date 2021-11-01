package com.karkowski.dddexample.warehouse.infra.repository;

import com.karkowski.dddexample.warehouse.infra.model.ProductDb;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<ProductDb, String> {
}

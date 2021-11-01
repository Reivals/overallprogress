package com.karkowski.dddexample.warehouse.infra.model;

import com.karkowski.dddexample.warehouse.controller.cqrs.dto.ProductDTO;
import com.karkowski.dddexample.warehouse.domain.Product;
import com.karkowski.dddexample.warehouse.domain.ProductId;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Table("Product")
public class ProductDb {

    @Id
    @Column("id")
    private String id;
    @Column("name")
    private String name;
    @Column("code")
    private String code;

    public ProductDb(final Product product) {
        this.id = product.getProductId().getId();
        this.name = product.getName();
        this.code = product.getCode();
    }

    public ProductDb(final String name, final String code) {
        this.code = code;
        this.name = name;
    }

    public Product toDomainModel() {
        return new Product(new ProductId(id), name, code);
    }
}

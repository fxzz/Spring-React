package com.mall.apiserver.repository;

import com.mall.apiserver.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

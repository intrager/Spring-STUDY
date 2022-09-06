package com.study.essentialguide.data.repository;

import com.study.essentialguide.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

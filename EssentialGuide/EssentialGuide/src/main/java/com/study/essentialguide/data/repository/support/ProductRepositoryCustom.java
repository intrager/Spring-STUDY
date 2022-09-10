package com.study.essentialguide.data.repository.support;

import com.study.essentialguide.data.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findByName(String name);
}

package com.study.essentialguide.data.repository;

import com.study.essentialguide.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

package com.study.essentialguide.data.repository;

import com.study.essentialguide.data.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}

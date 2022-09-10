package com.study.essentialguide.data.repository;

import com.study.essentialguide.data.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}

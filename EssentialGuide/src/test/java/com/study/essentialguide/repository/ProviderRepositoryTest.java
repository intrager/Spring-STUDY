package com.study.essentialguide.repository;

import com.study.essentialguide.data.entity.Product;
import com.study.essentialguide.data.entity.Provider;
import com.study.essentialguide.data.repository.ProductRepository;
import com.study.essentialguide.data.repository.ProviderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProviderRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Test
    void relationshipTest1() {
        // 테스트 데이터 생성
        Provider provider = new Provider();
        provider.setName("농협");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("쌀");
        product.setPrice(20000);
        product.setStock(100);
        product.setProvider(provider);

        productRepository.save(product);

        // 테스트
        System.out.println("product = " + productRepository.findById(1L)
                .orElseThrow(RuntimeException::new));

        System.out.println("provider = " + productRepository.findById(1L)
                .orElseThrow(RuntimeException::new).getProvider());
    }
}

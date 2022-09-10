package com.study.essentialguide.repository;

import com.study.essentialguide.data.entity.Product;
import com.study.essentialguide.data.entity.Provider;
import com.study.essentialguide.data.repository.ProductRepository;
import com.study.essentialguide.data.repository.ProviderRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    void cascadeTest() {
        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = saveProduct("상품1", 1000, 1000);
        Product product2 = saveProduct("상품2", 500, 1500);
        Product product3 = saveProduct("상품3", 750, 500);

        // 연관관계 설정
        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.save(provider);
    }

    private Provider savedProvider(String name) {
        Provider provider = new Provider();
        provider.setName(name);
        return provider;
    }

    private Product saveProduct(String name, Integer price ,Integer stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }

    @Test
    @Transactional
    void orphanRemovalTest() {
        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = saveProduct("상품1", 1000, 1000);
        Product product2 = saveProduct("상품2", 500, 1500);
        Product product3 = saveProduct("상품3", 750, 500);

        // 연관관계 설정
        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.saveAndFlush(provider);
        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);

        Provider foundProvider = providerRepository.findById(1L).get();
        foundProvider.getProductList().remove(0);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);
    }

}

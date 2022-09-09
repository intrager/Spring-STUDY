package com.study.essentialguide.repository.support;

import com.study.essentialguide.data.entity.Product;
import com.study.essentialguide.data.entity.Provider;
import com.study.essentialguide.data.repository.ProductRepository;
import com.study.essentialguide.data.repository.ProviderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;
    
    @Test
    void findByNameTest() {
        List<Product> productList = productRepository.findByName("펜");

        for(Product product : productList) {
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }

    @Test
    void auditingTest() {
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(100);

        Product savedProduct = productRepository.save(product);

        System.out.println("productName : " + savedProduct.getName());
        System.out.println("createdAt : " + savedProduct.getCreatedAt());
    }
    
    @Test
    void relationshipTest() {
        // 테스트 데이터 생성
        Provider provider = new Provider();
        provider.setName("농협");

        providerRepository.save(provider);

        Product product1 = new Product();
        product1.setName("쌀");
        product1.setPrice(20000);
        product1.setStock(100);
        product1.setProvider(provider);

        Product product2 = new Product();
        product2.setName("고기");
        product2.setPrice(40000);
        product2.setStock(50);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("채소");
        product3.setPrice(10000);
        product3.setStock(400);
        product3.setProvider(provider);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> products = providerRepository.findById(provider.getId()).get()
                .getProductList();

        for(Product product : products) {
            System.out.println(product);
        }
    }
}

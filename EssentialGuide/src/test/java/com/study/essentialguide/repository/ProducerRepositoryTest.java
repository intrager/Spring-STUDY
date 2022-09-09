package com.study.essentialguide.repository;

import com.study.essentialguide.data.entity.Producer;
import com.study.essentialguide.data.entity.Product;
import com.study.essentialguide.data.entity.Provider;
import com.study.essentialguide.data.repository.ProducerRepository;
import com.study.essentialguide.data.repository.ProductRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ProducerRepositoryTest {
    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    void relationshipTest() {
        Product product1 = saveProduct("쌀", 100, 20000);
        Product product2 = saveProduct("고기", 50, 40000);
        Product product3 = saveProduct("채소", 400, 10000);

        Producer producer1 = saveProducer("한국유통");
        Producer producer2 = saveProducer("식자재마트");

        producer1.addProduct(product1);
        producer1.addProduct(product2);

        producer2.addProduct(product2);
        producer2.addProduct(product3);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));

        System.out.println(producerRepository.findById(1L).get().getProducts());
    }

    private Producer saveProducer(String name) {
        Producer producer = new Producer();
        producer.setName(name);

        return producerRepository.save(producer);
    }

    private Product saveProduct(String name, Integer price, Integer stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return productRepository.save(product);
    }

    @Test
    @Transactional
    void relationshipTest2() {
        Product product1 = saveProduct("쌀", 100, 20000);
        Product product2 = saveProduct("고기", 50, 40000);
        Product product3 = saveProduct("채소", 400, 10000);

        Producer producer1 = saveProducer("한국유통");
        Producer producer2 = saveProducer("식자재마트");

        producer1.addProduct(product1);
        producer1.addProduct(product2);
        producer2.addProduct(product2);
        producer2.addProduct(product3);

        product1.addProducer(producer1);
        product2.addProducer(producer1);
        product2.addProducer(producer2);
        product3.addProducer(producer2);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));
        productRepository.saveAll(Lists.newArrayList(product1, product2, product3));

        System.out.println("products : " + producerRepository.findById(1L).get().getProducts());

        System.out.println("producers : " + productRepository.findById(2L).get().getProducers());
    }

}

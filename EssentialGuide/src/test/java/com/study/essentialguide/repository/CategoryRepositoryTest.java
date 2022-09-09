package com.study.essentialguide.repository;

import com.study.essentialguide.data.entity.Category;
import com.study.essentialguide.data.entity.Product;
import com.study.essentialguide.data.repository.CategoryRepository;
import com.study.essentialguide.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Test
    void relationshipTest() {
        Product product = new Product();
        product.setName("쌀");
        product.setPrice(20000);
        product.setStock(100);
        
        productRepository.save(product);

        Category category = new Category();
        category.setCode("S1");
        category.setName("도서");
        category.getProducts().add(product);

        categoryRepository.save(category);

        // 테스트 코드
        List<Product> products = categoryRepository.findById(1L).get().getProducts();

        for(Product foundProduct : products) {
            System.out.println(foundProduct);
        }
    }
}

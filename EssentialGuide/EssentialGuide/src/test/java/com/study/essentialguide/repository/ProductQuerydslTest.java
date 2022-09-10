package com.study.essentialguide.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.essentialguide.data.entity.Product;
import com.study.essentialguide.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
public class ProductQuerydslTest {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void queryDslTest() {
        JPAQuery<Product> query = new JPAQuery(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Product product : productList) {
            System.out.println("======================");
            System.out.println();
            System.out.println("Product Number: " + product.getNumber());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Product Price: " + product.getPrice());
            System.out.println("Product Stock: " + product.getStock());
            System.out.println();
            System.out.println("======================");
        }
    }

    @Test
    void queryDslTest2() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Product product : productList) {
            System.out.println("=====================");
            System.out.println();
            System.out.println("Product Number: " + product.getNumber());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Product Price: " + product.getPrice());
            System.out.println("Product Stock: " + product.getStock());
            System.out.println();
            System.out.println("=====================");
        }
    }

    @Test
    void queryDslTest3() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("팬"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(String product : productList) {
            System.out.println("=====================");
            System.out.println("Product name : " + product);
            System.out.println("=====================");
        }

        List<Tuple> tupleList = jpaQueryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Tuple product : tupleList) {
            System.out.println("=====================");
            System.out.println("Product name : " + product.get(qProduct.name));
            System.out.println("Product price : " + product.get(qProduct.price));
            System.out.println("=====================");
        }
    }

    @Test
    void queryDslTest4() {
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(String product : productList) {
            System.out.println("----------------------");
            System.out.println("Product Name : " + product);
            System.out.println("----------------------");
        }
    }
}

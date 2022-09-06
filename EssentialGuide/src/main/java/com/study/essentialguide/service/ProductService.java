package com.study.essentialguide.service;

import com.study.essentialguide.data.dto.ProductDto;
import com.study.essentialguide.data.dto.ProductResponseDto;
import com.study.essentialguide.data.entity.Product;

import java.time.LocalDateTime;

public interface ProductService {
    ProductResponseDto getProduct(Long number);
    ProductResponseDto saveProduct(ProductDto productDto);
    ProductResponseDto changeProductName(Long number, String name) throws Exception;
    void deleteProduct(Long number) throws Exception;

    default ProductResponseDto getProductResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    default Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }
}

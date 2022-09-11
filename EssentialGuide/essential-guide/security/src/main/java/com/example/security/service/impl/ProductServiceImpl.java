package com.example.security.service.impl;

import com.example.security.data.dto.ProductDto;
import com.example.security.data.dto.ProductResponseDto;
import com.example.security.data.entity.Product;
import com.example.security.data.repository.ProductRepository;
import com.example.security.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto getProduct(Long number) {
        LOGGER.info("[getProduct] input number : {}", number);
        Product product = productRepository.findById(number).get();
        LOGGER.info("[getProduct] product number : {}, name : {}", product.getNumber(), product.getName());
        ProductResponseDto productResponseDto = getProductResponseDto(product);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        LOGGER.info("[saveProduct] productDTO : {}", productDto.toString());
        Product product = getProduct(productDto);
        Product savedProduct = productRepository.save(product);
        ProductResponseDto productResponseDto = getProductResponseDto(savedProduct);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        Product foundProduct = productRepository.findById(number).get();
        foundProduct.setName(name);
        Product changedProduct = productRepository.save(foundProduct);

        ProductResponseDto productResponseDto = getProductResponseDto(changedProduct);
        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productRepository.deleteById(number);
    }
}


package com.study.essentialguide.service.impl;

import com.study.essentialguide.data.dao.ProductDAO;
import com.study.essentialguide.data.dto.ProductDto;
import com.study.essentialguide.data.dto.ProductResponseDto;
import com.study.essentialguide.data.entity.Product;
import com.study.essentialguide.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);
        ProductResponseDto productResponseDto = getProductResponseDto(product);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = getProduct(productDto);
        Product savedProduct = productDAO.insertProduct(product);
        ProductResponseDto productResponseDto = getProductResponseDto(savedProduct);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        Product changedProduct = productDAO.updateProductName(number, name);
        ProductResponseDto productResponseDto = getProductResponseDto(changedProduct);
        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }


}

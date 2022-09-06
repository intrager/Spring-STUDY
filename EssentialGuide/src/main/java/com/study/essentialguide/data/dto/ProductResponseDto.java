package com.study.essentialguide.data.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private Long number;
    private String name;
    private int price;
    private int stock;
}

package com.xxbank.shop0825.model;

import com.xxbank.shop0825.constant.ProductType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

    @NotNull
    private String productName;

    @NotNull
    private ProductType productType; //Enum類型>裡面事先固定好的值

    @NotNull
    private String imageUrl;

    @NotNull
    private Double price ;

    @NotNull
    private Integer stock ;

    private String productContent;
}

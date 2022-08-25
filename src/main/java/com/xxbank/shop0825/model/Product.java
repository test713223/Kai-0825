package com.xxbank.shop0825.model;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private Integer productId;
    private String productName;
    private String productType;
    private String imageUrl;
    private Double price ;
    private Integer stock ;
    private String productContent;
    private Date createDate;
    private Date lastModifiedDate;
}

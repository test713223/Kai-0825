package com.xxbank.shop0825.model;

import com.xxbank.shop0825.constant.ProductType;
import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private Integer productId;
    private String productName;
    private ProductType productType; //Enum類型>裡面事先固定好的值
    private String imageUrl;
    private Double price ;
    private Integer stock ;
    private String productContent;
    private Date createDate;
    private Date lastModifiedDate;
}

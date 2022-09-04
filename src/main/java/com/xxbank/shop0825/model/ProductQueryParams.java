package com.xxbank.shop0825.model;

import com.xxbank.shop0825.constant.ProductType;
import lombok.Data;

@Data
public class ProductQueryParams {
    ProductType productType ;
    String search ;

}

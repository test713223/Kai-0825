package com.xxbank.shop0825.model;

import com.xxbank.shop0825.constant.ProductType;
import lombok.Data;

@Data
public class ProductQueryParams {
   private ProductType productType ;
   private String search ;
   private String orderBy ;
   private String sort ;
   private Integer limit;
   private Integer offset;
}

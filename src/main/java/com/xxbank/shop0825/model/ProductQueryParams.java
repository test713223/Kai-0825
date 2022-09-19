package com.xxbank.shop0825.model;

import com.xxbank.shop0825.constant.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductQueryParams {
   private ProductType productType ;
   private String search ;
   private String orderBy ;
   private String sort ;
   private Integer limit;
   private Integer offset;
}

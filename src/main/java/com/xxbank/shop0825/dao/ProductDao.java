package com.xxbank.shop0825.dao;

import com.xxbank.shop0825.constant.ProductType;
import com.xxbank.shop0825.model.Product;
import com.xxbank.shop0825.model.ProductQueryParams;
import com.xxbank.shop0825.model.ProductRequest;

import java.util.List;

public interface ProductDao {
     List<Product> getProducts(ProductQueryParams productQueryParams);
     Integer gettotalProducts(ProductQueryParams productQueryParams);
     Product getProductById(Integer productId);
     Integer createProduct(ProductRequest productRequest);
     void updateProduct(Integer productId,ProductRequest productRequest);
     void deleteProductById(Integer productId);

}

package com.xxbank.shop0825.service;

import com.xxbank.shop0825.model.Product;
import com.xxbank.shop0825.model.ProductRequest;

public interface ProductService {
    Product getProducts();
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
}

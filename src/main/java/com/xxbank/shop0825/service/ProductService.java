package com.xxbank.shop0825.service;

import com.xxbank.shop0825.model.Product;

public interface ProductService {
    Product getProducts();
    Product getProductById(Integer productId);
}

package com.xxbank.shop0825.dao;

import com.xxbank.shop0825.model.Product;

public interface ProductDao {
     Product getProducts();
     Product getProductById(Integer productId);
}

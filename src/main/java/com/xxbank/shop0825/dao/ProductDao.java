package com.xxbank.shop0825.dao;

import com.xxbank.shop0825.model.Product;
import com.xxbank.shop0825.model.ProductRequest;

public interface ProductDao {
     Product getProducts();
     Product getProductById(Integer productId);
     Integer createProduct(ProductRequest productRequest);

}

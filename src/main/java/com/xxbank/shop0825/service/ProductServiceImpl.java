package com.xxbank.shop0825.service;

import com.xxbank.shop0825.dao.ProductDao;
import com.xxbank.shop0825.model.Product;
import com.xxbank.shop0825.model.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService{


    @Autowired
    private ProductDao ProductDao ; //盡量注入interface

    @Override
    public Product getProducts() {
        return null;
    }

    @Override
    public Product getProductById(Integer productId) {
        return ProductDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return ProductDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        ProductDao.updateProduct(productId,productRequest);
    }
}

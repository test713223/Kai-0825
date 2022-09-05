package com.xxbank.shop0825.service;

import com.xxbank.shop0825.constant.ProductType;
import com.xxbank.shop0825.dao.ProductDao;
import com.xxbank.shop0825.model.Product;
import com.xxbank.shop0825.model.ProductQueryParams;
import com.xxbank.shop0825.model.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService{


    @Autowired
    private ProductDao ProductDao ; //盡量注入interface


    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return ProductDao.getProducts(productQueryParams);
    }

    @Override
    public Integer gettotalProducts(ProductQueryParams productQueryParams) {
        return ProductDao.gettotalProducts(productQueryParams);
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

    @Override
    public void deleteProductById(Integer productId) {
        ProductDao.deleteProductById(productId);
    }
}

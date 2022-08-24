package com.xxbank.shop0825.dao;

import com.xxbank.shop0825.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDaoImpl implements ProductDao {

    //查詢所有商品
    @Override
    public Product getProducts() {
        String sql = "";
        return null;
    }

    //查詢特定商品by id
    @Override
    public Product getProductById(Integer productId) {
        return null;
    }
}

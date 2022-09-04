package com.xxbank.shop0825.dao;

import com.xxbank.shop0825.model.Product;
import com.xxbank.shop0825.model.ProductRequest;
import com.xxbank.shop0825.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    @Qualifier("test1JdbcTemplate")
    private NamedParameterJdbcTemplate test1JdbcTemplate ;

    //查詢所有商品
    @Override
    public Product getProducts() {
        return null;
    }

    //查詢特定商品by id
    @Override
    public Product getProductById(Integer productId) {
        //產生sql
        String sql = " SELECT * FROM product WHERE product_id = :productId";

        //產生Map
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);   //傳入參數的名稱與值相同

        //產生RowMapper
        List<Product> productList = test1JdbcTemplate.query(sql, map, new ProductRowMapper());

        if(productList.size()>0){
            return productList.get(0) ;
        }else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        //sql
        String sql = "INSERT INTO product (product_name,product_type,image_url,price,stock,product_content,create_date,last_modified_date)" +
                     "VALUES              (:productName,:productType,:imageUrl,:price,:stock,:productContent,:createDate,:lastModifiedDate)";
        //map 塞值
        Map<String,Object> map = new HashMap<>();
        map.put("productName",productRequest.getProductName());
        map.put("productType",productRequest.getProductType().toString()); //Enun抓出來要轉成String類型
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("productContent",productRequest.getProductContent());

        Date now = new Date();
        map.put("createDate",now);
        map.put("lastModifiedDate",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        //執行語法
        test1JdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        return keyHolder.getKey().intValue();
    }
}

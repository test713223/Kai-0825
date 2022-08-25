package com.xxbank.shop0825.rowmapper;

import com.xxbank.shop0825.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        //將資料庫欄位對應Product類型，在product變數中set從資料庫回傳的值(resultSet.get型態(By Name))
        Product product = new Product();

        product.setProductId(resultSet.getInt("product_id"));
        product.setProductName(resultSet.getString("product_name"));
        product.setProductType(resultSet.getString("product_type"));
        product.setImageUrl(resultSet.getString("image_url"));
        product.setPrice(resultSet.getDouble("price"));
        product.setStock(resultSet.getInt("stock"));
        product.setProductContent(resultSet.getString("product_content"));
        product.setCreateDate(resultSet.getTimestamp("create_date"));
        product.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return product;
    }
}

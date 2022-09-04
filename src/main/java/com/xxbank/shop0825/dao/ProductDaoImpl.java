package com.xxbank.shop0825.dao;

import com.xxbank.shop0825.constant.ProductType;
import com.xxbank.shop0825.model.Product;
import com.xxbank.shop0825.model.ProductQueryParams;
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

    //查詢特定分類商品
    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        //sql
        String sql = " SELECT product_id,product_name,product_type,image_url,price,stock,product_content,create_date,last_modified_date" +
                     " FROM product WHERE 1=1 " ;
        //map
        Map<String,Object> map = new HashMap<>();

        //產品類別
        if(productQueryParams.getProductType() != null){
            sql += " AND product_type = :productType";
            map.put("productType",productQueryParams.getProductType().name());//Enum.name() 可以找自訂內容的關鍵字，轉為String顯示
        }

        //關鍵字搜尋特定商品,模糊查詢
        if(productQueryParams.getSearch() != null){
            sql += " AND product_name LIKE  :search";
            map.put("search","%" + productQueryParams.getSearch() + "%");
        }

        //執行語法
        List<Product> productList =  test1JdbcTemplate.query(sql,map,new ProductRowMapper());

        return productList;
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

    //新增商品
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

    //修改商品by id
    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        //sql
        String sql  = "UPDATE  product " +
                      "SET product_name=:productName, product_type=:productType, image_url=:imageUrl, " +
                      "price=:price, stock=:stock, product_content=:productContent, last_modified_date=:lastModifiedDate " +
                      "WHERE   product_id =:productId " ;
        //塞值
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("productName",productRequest.getProductName());
        map.put("productType",productRequest.getProductType().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("productContent",productRequest.getProductContent());
        map.put("lastModifiedDate",new Date());

        //執行語法
        test1JdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        //sql
        String sql = "DELETE FROM product WHERE product_id =:productId";

        //map
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);

        //執行語法
        test1JdbcTemplate.update(sql,map);
    }


}

package com.xxbank.shop0825.controller;

import com.xxbank.shop0825.constant.ProductType;
import com.xxbank.shop0825.model.Product;
import com.xxbank.shop0825.model.ProductQueryParams;
import com.xxbank.shop0825.model.ProductRequest;
import com.xxbank.shop0825.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService ;


    @GetMapping ("/products")
    public ResponseEntity<List<Product>> getProducts(
            //查詢條件 filtering
            @RequestParam (required = false) ProductType productType,
            @RequestParam (required = false) String search,

            //預設排序 sorting
            @RequestParam (defaultValue = "create_date") String orderBy,
            @RequestParam (defaultValue = "desc")String sort

    ){
        //傳遞參數>塞值
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setProductType(productType);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);

        List<Product> list = productService.getProducts(productQueryParams) ;
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }



    @GetMapping ("/products/{productId}")
    public ResponseEntity <Product> getProductById(@PathVariable Integer productId){
        Product product  = productService.getProductById(productId);

        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest);

        Product product =productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId ,
                                                 @RequestBody @Valid ProductRequest productRequest){
        //驗證商品是否存在
        Product product = productService.getProductById(productId);
        if (product == null) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改商品資料
        productService.updateProduct(productId,productRequest);
        Product updateProduct = productService.getProductById(productId);
        return  ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }





}

package com.xxbank.shop0825.controller;

import com.xxbank.shop0825.constant.ProductType;
import com.xxbank.shop0825.model.PageReuqest;
import com.xxbank.shop0825.model.Product;
import com.xxbank.shop0825.model.ProductQueryParams;
import com.xxbank.shop0825.model.ProductRequest;
import com.xxbank.shop0825.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService ;


    @GetMapping ("/products")
    public ResponseEntity<PageReuqest<Product>> getProducts(
            //查詢條件 filtering
            @RequestParam (required = false) ProductType productType,
            @RequestParam (required = false) String search,

            //預設排序 sorting
            @RequestParam (defaultValue = "create_date") String orderBy,
            @RequestParam (defaultValue = "desc") String sort,

            //分頁 Pagination
            @RequestParam (defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam (defaultValue = "0") @Min(0) Integer offset

    ){
        //傳遞參數>塞值
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setProductType(productType);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        //丟入參數、取得回傳商品列表的json格式的List
        List<Product> list = productService.getProducts(productQueryParams) ;


        //取得total總數
        Integer total =  productService.gettotalProducts(productQueryParams);

        PageReuqest<Product> page = new PageReuqest<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(list);

        return ResponseEntity.status(HttpStatus.OK).body(page);
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

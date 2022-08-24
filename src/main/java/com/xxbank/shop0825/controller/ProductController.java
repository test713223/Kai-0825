package com.xxbank.shop0825.controller;

import com.xxbank.shop0825.model.Product;
import com.xxbank.shop0825.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService ;

    @PostMapping ("/products/{id}")
    public Product getProducts(@PathVariable Integer id){

        if(id != null){
            return productService.getProductById(id);
        }else{
            return null ;
        }
    }


}

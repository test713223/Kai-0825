package com.xxbank.shop0825.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageReuqest<T> {
    private Integer limit ;
    private Integer offset ;
    private Integer total ;
    private List<T> results;
}

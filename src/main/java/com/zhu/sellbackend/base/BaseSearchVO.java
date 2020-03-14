package com.zhu.sellbackend.base;

import lombok.Data;

/**
 * @ClassName BaseSearchVO
 * @Description
 * @Author qgh
 * @Date 2020-02-29 10:44
 **/
@Data
public class BaseSearchVO {

    private static final long serialVersionUID = -4583011808265809611L;
    private int pageNum = 1;
    private int pageSize = 5;
    private String searchField;
    private String sortField;
    private String sortType;

}

package com.zhu.sellbackend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhu.sellbackend.orm.Category;
import com.zhu.sellbackend.orm.Product;
import com.zhu.sellbackend.vo.ProductVO;

import java.util.List;
import java.util.Map;

public interface ItemService {

    void addCategory(Category category);

    List<Category> getAllCategory();

    void delCategoryById(Long id);

    void addProduct(Product product);

    IPage<ProductVO> getProduct(ProductVO product);

    Map<String,List<Product>> getProductGroupByCategory();
}

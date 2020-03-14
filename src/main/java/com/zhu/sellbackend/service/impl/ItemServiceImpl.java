package com.zhu.sellbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhu.sellbackend.mapper.CategoryMapper;
import com.zhu.sellbackend.mapper.ProductMapper;
import com.zhu.sellbackend.orm.Category;
import com.zhu.sellbackend.orm.Product;
import com.zhu.sellbackend.service.ItemService;
import com.zhu.sellbackend.vo.ProductVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ItemServiceImpl
 * @Description
 * @Author qgh
 * @Date 2020-03-14 16:10
 **/
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private ProductMapper productMapper;

    /**
     * @Description  新增商品类目
     * @Param
     * @Return
     **/
    @Override
    public void addCategory(Category category) {
        category.setCreateTime(new Date());
        category.setStatus("N");
        categoryMapper.insert(category);
    }

    /**
     * @Description  获取所有商品类目
     * @Param
     * @Return
     **/
    @Override
    public List<Category> getAllCategory() {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("status", "N");
        List<Category> list = categoryMapper.selectList(wrapper);
        return list;
    }

    /**
     * @Description  删除某个商品类目(逻辑删除)
     * @Param
     * @Return
     **/
    @Override
    public void delCategoryById(Long id) {
        Category category = new Category();
        category.setId(id);
        category.setStatus("D");
        categoryMapper.updateById(category);
    }

    /**
     * @Description  新增商品
     * @Param
     * @Return
     **/
    @Override
    public void addProduct(Product product) {
        product.setProductStatus("N");
        productMapper.insert(product);
    }

    /**
     * @Description  根据条件查询商品
     * @Param
     * @Return
     **/
    @Override
    public IPage<ProductVO> getProduct(ProductVO product) {
        IPage<ProductVO> page = new Page<>(product.getPageNum(), product.getPageSize());
        IPage<ProductVO> result = productMapper.getProduct(page, product);
        return result;
    }

    @Override
    public Map<String, List<Product>> getProductGroupByCategory() {
        Map<String, List<Product>> map = new HashMap<>();
        QueryWrapper<Category> categoryWrapper = new QueryWrapper<>();
        categoryWrapper.eq("status", "N");
        List<Category> categories = categoryMapper.selectList(categoryWrapper);
        for (Category category : categories) {
            QueryWrapper<Product> productWrapper = new QueryWrapper<>();
            productWrapper.eq("product_status", "N");
            productWrapper.eq("category_id", category.getId());
            List<Product> products = productMapper.selectList(productWrapper);
            map.put(category.getCategoryName(), products);
        }
        return map;
    }

}

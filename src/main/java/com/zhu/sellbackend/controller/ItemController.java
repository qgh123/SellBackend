package com.zhu.sellbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhu.sellbackend.base.BaseController;
import com.zhu.sellbackend.base.BaseModel;
import com.zhu.sellbackend.orm.Category;
import com.zhu.sellbackend.orm.Product;
import com.zhu.sellbackend.service.ItemService;
import com.zhu.sellbackend.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ItemController
 * @Description
 * @Author qgh
 * @Date 2020-03-12 16:14
 **/
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Resource
    private ItemService itemService;

    /**
      * @Description  新增商品类目
      * @Param
      * @Return 
      **/
    @PostMapping("/addCategory")
    public BaseModel addCategory() {
        try {
            JSONObject json = super.convertRequestBody();
            Category category = JSONObject.toJavaObject(json, Category.class);
            itemService.addCategory(category);
            return BaseModel.buildSuccess();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return BaseModel.buildError(e);
        }
    }

    /**
     * @Description  获取所有商品类目
     * @Param
     * @Return
     **/
    @RequestMapping("/getAllCategory")
    public BaseModel<List<Category>> getAllCategory() {
        try {
            List<Category> list = itemService.getAllCategory();
            return BaseModel.buildSuccess(list);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return BaseModel.buildError(e);
        }
    }

    /**
     * @Description  删除某个商品类目
     * @Param
     * @Return
     **/
    @RequestMapping("/delCategoryById")
    public BaseModel delCategoryById() {
        try {
            JSONObject json = super.convertRequestBody();
            Long id = json.getLong("id");
            itemService.delCategoryById(id);
            return BaseModel.buildSuccess();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return BaseModel.buildError(e);
        }
    }

    /**
     * @Description  新增商品
     * @Param
     * @Return
     **/
    @RequestMapping("/addProduct")
    public BaseModel addProduct() {
        try {
            JSONObject json = super.convertRequestBody();
            Product product = JSONObject.toJavaObject(json, Product.class);
            itemService.addProduct(product);
            return BaseModel.buildSuccess();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return BaseModel.buildError(e);
        }
    }

    /**
     * @Description  根据条件查询商品
     * @Param
     * @Return
     **/
    @RequestMapping("/getProduct")
    public BaseModel<IPage<ProductVO>> getProduct() {
        try {
            JSONObject json = super.convertRequestBody();
            ProductVO product = JSONObject.toJavaObject(json, ProductVO.class);
            IPage<ProductVO> page = itemService.getProduct(product);
            return BaseModel.buildSuccess(page);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return BaseModel.buildError(e);
        }
    }

    /**
     * @Description  分组（根据种类）查询商品
     * @Param
     * @Return
     **/
    @RequestMapping("/getProductGroupByCategory")
    public BaseModel<Map<String, List<Product>>> getProductGroupByCategory() {
        try {
            Map<String, List<Product>> result = itemService.getProductGroupByCategory();
            return BaseModel.buildSuccess(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return BaseModel.buildError(e);
        }
    }

}

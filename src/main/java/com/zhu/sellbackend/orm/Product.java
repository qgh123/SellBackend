package com.zhu.sellbackend.orm;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName Product
 * @Description
 * @Author qgh
 * @Date 2020-03-14 15:40
 **/
@Data
@TableName("product")
public class Product {

    private Long id;
    //商品名称
    @TableField("product_name")
    private String productName;
    //商品价格
    @TableField("product_price")
    private BigDecimal productPrice;
    //商品库存
    @TableField("product_stock")
    private Integer productStock;
    //商品描述
    @TableField("product_description")
    private String productDescription;
    //商品图片
    @TableField("product_image")
    private String productImage;
    //商品状态
    @TableField("product_status")
    private String productStatus;
    //订单中商品数量
    private Integer number;
    @TableField("category_id")
    //所属类目
    private Long categoryId;

}

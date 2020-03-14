package com.zhu.sellbackend.vo;

import com.zhu.sellbackend.base.BaseSearchVO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName ProductVO
 * @Description
 * @Author qgh
 * @Date 2020-03-14 19:48
 **/
@Data
public class ProductVO extends BaseSearchVO {

    private Long id;
    //商品名称
    private String productName;
    //商品价格
    private BigDecimal productPrice;
    //商品库存
    private Integer productStock;
    //商品描述
    private String productDescription;
    //商品图片
    private String productImage;
    //商品状态
    private String productStatus;
    //订单中商品数量
    private Integer number;
    //所属类目
    private Long categoryId;
    //类目名称
    private String categoryName;

}

package com.zhu.sellbackend.orm;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Category
 * @Description
 * @Author qgh
 * @Date 2020-03-14 15:26
 **/
@Data
@TableName("category")
public class Category {

    private Long id;
    //类目名称
    @TableField("category_name")
    private String categoryName;
    //类目描述
    @TableField("category_desc")
    private String categoryDesc;
    //创建时间
    @TableField("create_time")
    private Date createTime;
    //状态 N正常 D删除
    private String status;

}

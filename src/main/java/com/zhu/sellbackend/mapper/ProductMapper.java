package com.zhu.sellbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhu.sellbackend.orm.Product;
import com.zhu.sellbackend.vo.ProductVO;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper extends BaseMapper<Product> {

    IPage<ProductVO> getProduct(@Param("pg") IPage<ProductVO> page, @Param("searchVO") ProductVO product);

}

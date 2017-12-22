package com.supply.front.module.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.supply.base.repository.Repository;
import com.supply.entity.PageInfo;
import com.supply.entity.po.ProductPo;

@Mapper
public interface ProductMapper extends Repository
{
	int updateProductNum(List<ProductPo> products);

	// List<ProductPo> findAll(long pageStart, int pageSize);

	List<ProductPo> findAll(@Param("page") PageInfo page, @Param("productName") String productName);

	List<ProductPo> findByIds(List<Long> ids);

	long count(@Param("productName") String productName);
}

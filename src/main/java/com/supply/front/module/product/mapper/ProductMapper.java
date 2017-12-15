package com.supply.front.module.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.supply.base.repository.Repository;
import com.supply.entity.PageInfo;
import com.supply.entity.po.ProductPo;

@Mapper
public interface ProductMapper extends Repository
{
	int updateProductNum(List<ProductPo> products);
	
//	List<ProductPo> findAll(long pageStart, int pageSize);
	
	List<ProductPo> findAll(PageInfo page);
	
	
	List<ProductPo> findByIds(List<Long> ids);
}

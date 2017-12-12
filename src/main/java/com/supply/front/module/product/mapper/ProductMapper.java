package com.supply.front.module.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.supply.base.repository.Repository;
import com.supply.entity.PageInfo;
import com.supply.entity.po.ProductPo;

@Mapper
public interface ProductMapper extends Repository
{
	int reduceNum(long id, int productNum);
	
//	List<ProductPo> findAll(long pageStart, int pageSize);
	
	List<ProductPo> findAll(PageInfo page);
	
	long findProductNum(long id);
}

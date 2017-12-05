package com.supply.front.module.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supply.entity.PageInfo;
import com.supply.entity.po.ProductPo;
import com.supply.front.module.product.mapper.ProductMapper;
import com.supply.front.module.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService
{
	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<ProductPo> findProducts(PageInfo page)
	{
		return productMapper.findAll(page);
	}
	
}

package com.supply.front.module.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supply.entity.PageInfo;
import com.supply.entity.po.OrderPo;
import com.supply.entity.po.ProductPo;
import com.supply.front.module.product.mapper.ProductMapper;
import com.supply.front.module.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService
{
	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public PageInfo<ProductPo> findProducts(PageInfo page, String productName)
	{
		PageInfo<ProductPo> result = new PageInfo<>();
		List<ProductPo> list = productMapper.findAll(page, productName);
		long count = productMapper.count(productName);
		result.setList(list);
		result.setTotalNum(count);
		result.setItemNum(page.getItemNum());
		result.setTotalPage(result.calcTotalPage());
		result.setCurrentPage(page.getCurrentPage());
		return result;
	}
	
}

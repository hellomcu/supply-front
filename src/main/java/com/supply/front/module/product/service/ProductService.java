package com.supply.front.module.product.service;

import java.util.List;

import com.supply.base.service.BaseService;
import com.supply.entity.PageInfo;
import com.supply.entity.po.ProductPo;

public interface ProductService extends BaseService
{

	List<ProductPo> findProducts(PageInfo page);
	

}

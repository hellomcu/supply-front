package com.supply.front.module.order.service;

import java.util.List;

import com.supply.base.service.BaseService;
import com.supply.entity.PageInfo;
import com.supply.entity.po.OrderDetailPo;
import com.supply.entity.po.OrderPo;

public interface OrderService extends BaseService
{

	void createOrder(OrderPo order, List<OrderDetailPo> detailParams);

	
	PageInfo<OrderPo> findMyOrders(PageInfo page, long storeId);
}

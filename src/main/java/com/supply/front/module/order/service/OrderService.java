package com.supply.front.module.order.service;

import java.util.List;

import com.supply.base.service.BaseService;
import com.supply.entity.PageInfo;
import com.supply.entity.po.OrderDetailPo;
import com.supply.entity.po.OrderPo;
import com.supply.entity.po.OrderSumPo;

public interface OrderService extends BaseService
{

	void createOrder(OrderPo order, List<OrderDetailPo> detailParams);

	
	List<OrderPo> findMyOrders(PageInfo<Void> page, long storeId, long createTime);

	OrderSumPo findMyOrderSum(long storeId, long createTime);
}

package com.supply.front.module.order.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supply.contant.OrderStatus;
import com.supply.entity.PageInfo;
import com.supply.entity.po.OrderDetailPo;
import com.supply.entity.po.OrderPo;
import com.supply.exception.SupplyException;
import com.supply.front.module.order.mapper.OrderMapper;
import com.supply.front.module.order.service.OrderService;
import com.supply.front.module.product.mapper.ProductMapper;

@Service
public class OrderServiceImpl implements OrderService
{

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private ProductMapper productMapper;

	@Transactional
	@Override
	public void createOrder(OrderPo order, List<OrderDetailPo> orderDetails)
	{
		// 创建订单
		int size = orderDetails.size();
		order.setProductNum(size);
		OrderPo tmp = calcTotalPriceAndNum(orderDetails);
		order.setTotalPrice(tmp.getTotalPrice());
		order.setTotalNum(tmp.getTotalNum());
		order.setOrderStatus(OrderStatus.STATUS_UNDER);
		int row = orderMapper.save(order);
		if (row != 1)
		{
			throw new SupplyException("创建订单失败,请稍后重试");
		}

		int rows = orderMapper.saveDetails(order.getId(), orderDetails);
		if (rows != size)
		{
			throw new SupplyException("创建订单失败,请稍后重试");
		}

		// 修改库存
		for (OrderDetailPo detail : orderDetails)
		{
			productMapper.reduceNum(detail.getProductId(), detail.getProductNum());
		}

	}

	@Override
	public List<OrderPo> findMyOrders(PageInfo page, long storeId)
	{
		return orderMapper.findByStoreId(storeId, page);
	}

	private OrderPo calcTotalPriceAndNum(List<OrderDetailPo> orderDetails)
	{
		BigDecimal totalPrice = new BigDecimal("0.00");
		int totalNum = 0;
		for (OrderDetailPo detail : orderDetails)
		{
			int num = detail.getProductNum();
			totalPrice = totalPrice.add(detail.getUnitPrice().multiply(new BigDecimal(num)));
			totalNum += num;
		}
		OrderPo order = new OrderPo();
		order.setTotalPrice(totalPrice);
		order.setTotalNum(totalNum);
		return order;
	}

}

package com.supply.front.module.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supply.contant.OrderStatus;
import com.supply.entity.PageInfo;
import com.supply.entity.po.OrderDetailPo;
import com.supply.entity.po.OrderPo;
import com.supply.entity.po.ProductPo;
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
	public void createOrder(OrderPo order, List<OrderDetailPo> detailParams)
	{
		if (order == null || detailParams == null || detailParams.isEmpty())
		{
			throw new SupplyException("下单失败,参数错误");
		}

		List<Long> productIds = new ArrayList<>();
		for (OrderDetailPo detail : detailParams)
		{
			long productId = detail.getProductId();
			productIds.add(productId);
		}
		// 根据商品id查询所购买的商品
		List<ProductPo> myProducts = productMapper.findByIds(productIds);
		int num = myProducts.size();
		if (myProducts == null || myProducts.size() != num)
		{
			throw new SupplyException("下单失败,购买的产品已下架");
		}
		OrderPo tmp = processOrderProduct(detailParams, myProducts);
		
		order.setProductNum(num);
		order.setTotalPrice(tmp.getTotalPrice());
		order.setTotalNum(tmp.getTotalNum());
		order.setOrderStatus(OrderStatus.STATUS_UNDER);
		// 保存订单信息
		int row = orderMapper.save(order);
		if (row != 1)
		{
			throw new SupplyException("创建订单失败,请稍后重试");
		}
		
		//保存订单明细信息
		int rows = orderMapper.saveDetails(order.getId(), detailParams);
		if (rows != num)
		{
			throw new SupplyException("创建订单失败,请稍后重试");
		}

		// 修改库存
		updateProductStock(tmp.getDetails());
	}

	/**
	 * 处理订单商品包括计算订单总价，计算商品总数，检查库存,设置订单详情的商品信息
	 * 
	 * @param productParams
	 *            前台传过来的商品参数，包含商品id和购买数量
	 * @param myProducts
	 *            实际从库中查询的商品详情
	 */
	private OrderPo processOrderProduct(List<OrderDetailPo> productParams, List<ProductPo> myProducts)
			throws SupplyException
	{
		BigDecimal totalPrice = new BigDecimal("0.00");
		int totalNum = 0;
		for (ProductPo productItem : myProducts)
		{
			OrderDetailPo detailPo = getOrderDetailByProductId(productParams, productItem.getId());
			if (detailPo == null)
			{
				throw new SupplyException("下单失败,请重试");
			}
			int buyNum = detailPo.getProductNum();
			int actuallyNum = productItem.getProductNum();
			if (buyNum > actuallyNum)
			{
				throw new SupplyException(
						String.format("下单失败,您购买的 [%s] 库存只剩 %d 件", productItem.getProductName(), actuallyNum));
			}
			detailPo.setProductName(productItem.getProductName());
			detailPo.setProductUnit(productItem.getProductUnit());
			BigDecimal productPrice = productItem.getProductPrice();
			detailPo.setUnitPrice(productPrice);
			totalPrice = totalPrice.add(productPrice.multiply(new BigDecimal(buyNum)));
			totalNum += buyNum;
		}
		OrderPo order = new OrderPo();
		order.setTotalPrice(totalPrice);
		order.setTotalNum(totalNum);
		order.setDetails(myProducts);
		return order;
	}

	private void updateProductStock(List<ProductPo> products) throws SupplyException
	{
		if (products == null || products.isEmpty())
		{
			throw new SupplyException("更新库存失败");
		}
		int rows = productMapper.updateProductNum(products);
		if (rows != products.size())
		{
			throw new SupplyException("更新库存失败");
		}
	}
	
	/**
	 * 根据商品id从List中获取所对应的商品信息(购买数量)
	 * @param productParams
	 * @param productId
	 * @return
	 */
	private OrderDetailPo getOrderDetailByProductId(List<OrderDetailPo> productParams, long productId)
	{
		for (OrderDetailPo detailPo : productParams)
		{
			if (productId == detailPo.getProductId())
			{
				return detailPo;
			}
		}
		return null;
	}

	@Override
	public List<OrderPo> findMyOrders(PageInfo page, long storeId)
	{
		return orderMapper.findByStoreId(storeId, page);
	}
}

package com.supply.front.module.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supply.base.controller.BaseController;
import com.supply.entity.PageInfo;
import com.supply.entity.base.BaseResponse;
import com.supply.entity.po.OrderDetailPo;
import com.supply.entity.po.OrderPo;
import com.supply.entity.po.OrderSumPo;
import com.supply.entity.po.UserPo;
import com.supply.exception.SupplyException;
import com.supply.front.auth.util.JwtUtil;
import com.supply.front.entity.dto.CreateOrderDto;
import com.supply.front.entity.dto.OrderDto;
import com.supply.front.module.order.service.OrderService;
import com.supply.front.util.WrappedBeanCopier;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "订单相关")
@RestController
@RequestMapping("/front/order")
public class OrderController extends BaseController
{

	private OrderService mOrderService;

	@Autowired
	public OrderController(OrderService orderService)
	{
		this.mOrderService = orderService;
	}

	

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(httpMethod = "POST", value = "创建订单", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseResponse<Void> createOrder(@RequestBody @Valid CreateOrderDto createOrderDto, BindingResult result, HttpServletRequest request)
	{
		UserPo loginUser = JwtUtil.getLoginUserFromJwt(request);
		if (loginUser == null)
		{
			BaseResponse<Void> response = new BaseResponse<>();
			response.setCode(100);
			response.setMessage("请先登录");
			return response;
		}
		if (result.hasErrors())
		{
			throw new SupplyException(result.getFieldError().getDefaultMessage());
		}
		
		OrderPo order = WrappedBeanCopier.copyProperties(createOrderDto, OrderPo.class);
		order.setStoreId(loginUser.getStoreId());
		List<OrderDetailPo> details = WrappedBeanCopier.copyPropertiesOfList(createOrderDto.getDetails(), OrderDetailPo.class);
		mOrderService.createOrder(order, details);
		return getResponse();
	}
	
	@ApiOperation(httpMethod = "GET", value = "获取我的订单", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@GetMapping(value="/my_orders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseResponse<PageInfo<OrderDto>> findMyOrders(@RequestParam(value = "createTime", required=false)long createTime, @RequestParam("page") long page, @RequestParam("num") int num, HttpServletRequest request)
	{
		PageInfo<Void> pageInfo = new PageInfo<Void>();
		pageInfo.setCurrentPage(page);
		pageInfo.setItemNum(num);

		//List<OrderDto> list = new ArrayList<OrderDto>();
//		Map<OrderPo, List<OrderDetailPo>> map = mOrderService.findMyOrders(pageInfo, storeId);
		UserPo loginUser = JwtUtil.getLoginUserFromJwt(request);
		if (loginUser == null)
		{
			BaseResponse<PageInfo<OrderDto>> response = new BaseResponse<>();
			response.setCode(100);
			response.setMessage("请先登录");
			return response;
		}
		
		pageInfo.setCurrentPage(page);
		pageInfo.setItemNum(num);

		long storeId = loginUser.getStoreId();
		List<OrderPo> orders = mOrderService.findMyOrders(pageInfo, storeId, createTime);
		OrderSumPo orderSumPo = mOrderService.findMyOrderSum(storeId, createTime);
		
		PageInfo<OrderDto> result = new PageInfo<OrderDto>();
		result.setCurrentPage(page);
		result.setTotalPrice(orderSumPo.getTotalPrice());
		result.setTotalNum(orderSumPo.getTotalCount());
		result.setItemNum(num);
		result.setTotalPage(result.calcTotalPage());
		result.setList(WrappedBeanCopier.copyPropertiesOfList(orders, OrderDto.class));
		return getResponse(result);
		
//		for (OrderPo key: map.keySet())
//		{
//			OrderDto order = WrappedBeanCopier.copyProperties(key, OrderDto.class);
//			order.setDetails(WrappedBeanCopier.copyPropertiesOfList(map.get(key), OrderDetailDto.class));
//			list.add(order);
//		}

	}
	
}

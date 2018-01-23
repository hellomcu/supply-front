package com.supply.front.module.order.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.supply.base.repository.Repository;
import com.supply.entity.PageInfo;
import com.supply.entity.po.OrderDetailPo;
import com.supply.entity.po.OrderPo;
import com.supply.entity.po.OrderSumPo;

@Mapper
public interface OrderMapper extends Repository
{
	int save(OrderPo order);
	
	int saveDetails(@Param("orderId")long orderId, @Param("details")List<OrderDetailPo> details);
	
	List<OrderPo> findByStoreId(@Param("storeId")long storeId, @Param("startTime") long startTime, @Param("endTime") long endTime, @Param("page")PageInfo<Void> page);
	
	OrderSumPo count(@Param("storeId")long storeId, @Param("startTime") long startTime, @Param("endTime") long endTime);
}

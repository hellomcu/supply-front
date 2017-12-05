package com.supply.front.entity.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.supply.entity.base.BaseDto;


public class CreateOrderDetailDto extends BaseDto
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4485727181999144858L;

	private long productId;
	
	@NotBlank(message = "商品名称不能为空")
	private String productName;
	
	@Min(value = 1, message = "请至少添加一件商品")
	private int productNum;
	
	private BigDecimal unitPrice;
	
	@NotBlank(message = "价格单位不能为空")
	private String productUnit;
	
	public long getProductId()
	{
		return productId;
	}
	public void setProductId(long productId)
	{
		this.productId = productId;
	}
	public String getProductName()
	{
		return productName;
	}
	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	public int getProductNum()
	{
		return productNum;
	}
	public void setProductNum(int productNum)
	{
		this.productNum = productNum;
	}
	public BigDecimal getUnitPrice()
	{
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice)
	{
		this.unitPrice = unitPrice;
	}
	public String getProductUnit()
	{
		return productUnit;
	}
	public void setProductUnit(String productUnit)
	{
		this.productUnit = productUnit;
	}
	
}

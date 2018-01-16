package com.supply.front.entity.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.supply.entity.base.BaseDto;

public class CreateOrderDto extends BaseDto
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1602047974450700054L;
	
	
	@NotBlank(message = "请填写收货地址")
	private String receivingAddress;
	
	@NotBlank(message = "请填写收货人")
	private String receiver;
	
	@Length(min = 11, message = "请正确填写联系方式")
	private String contacts;
	
	private String orderRemark;
	
	@Size(min = 1, message = "请先添加商品")
	@Valid
	private List<CreateOrderProductDetailDto> details;

	public String getReceivingAddress()
	{
		return receivingAddress;
	}

	public void setReceivingAddress(String receivingAddress)
	{
		this.receivingAddress = receivingAddress;
	}

	public String getContacts()
	{
		return contacts;
	}

	public void setContacts(String contacts)
	{
		this.contacts = contacts;
	}

	public String getOrderRemark()
	{
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark)
	{
		this.orderRemark = orderRemark;
	}

	public List<CreateOrderProductDetailDto> getDetails()
	{
		return details;
	}

	public void setDetails(List<CreateOrderProductDetailDto> details)
	{
		this.details = details;
	}

	public String getReceiver()
	{
		return receiver;
	}

	public void setReceiver(String receiver)
	{
		this.receiver = receiver;
	}

}

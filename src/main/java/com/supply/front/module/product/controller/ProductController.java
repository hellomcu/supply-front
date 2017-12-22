package com.supply.front.module.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supply.base.controller.BaseController;
import com.supply.entity.PageInfo;
import com.supply.entity.base.BaseResponse;
import com.supply.entity.po.ProductPo;
import com.supply.front.entity.dto.ProductDto;
import com.supply.front.module.product.service.ProductService;
import com.supply.front.util.WrappedBeanCopier;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "产品相关")
@RestController
@RequestMapping("/front/product")
public class ProductController extends BaseController
{

	private ProductService mProductService;

	@Autowired
	public ProductController(ProductService productService)
	{
		this.mProductService = productService;
	}

	
	
	@ApiOperation(httpMethod = "GET", value = "获取所有产品", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@GetMapping(value="/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseResponse<PageInfo<ProductDto>> findAllProducts(@RequestParam("page") long page, @RequestParam("num") int num, @RequestParam(value="productName", required=false) String productName)
	{
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(page);
		pageInfo.setItemNum(num);
		PageInfo<ProductPo> orderPos = mProductService.findProducts(pageInfo, productName);
		PageInfo<ProductDto> result = new PageInfo<ProductDto>();
		result.setCurrentPage(orderPos.getCurrentPage());
		result.setTotalNum(orderPos.getTotalNum());
		result.setTotalPage(orderPos.getTotalPage());
		result.setItemNum(orderPos.getItemNum());
		result.setList(WrappedBeanCopier.copyPropertiesOfList(orderPos.getList(), ProductDto.class));
		return getResponse(result);
	}
	

}

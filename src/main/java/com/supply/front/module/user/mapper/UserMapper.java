package com.supply.front.module.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.supply.base.repository.Repository;
import com.supply.entity.po.UserPo;

@Mapper
public interface UserMapper extends Repository
{
	UserPo findOneByUsernameAndType(String username, int type);
}

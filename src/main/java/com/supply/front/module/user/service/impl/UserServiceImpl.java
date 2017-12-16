package com.supply.front.module.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supply.entity.po.UserPo;
import com.supply.exception.SupplyException;
import com.supply.front.module.user.mapper.UserMapper;
import com.supply.front.module.user.service.UserService;
import com.supply.util.Md5;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserPo userLogin(String username, String password, int userType)
	{

		UserPo loginUser = userMapper.findOneByUsernameAndType(username, userType);

		if (loginUser == null)
		{
			throw new SupplyException("登录失败，此用户不存在");
		}
		String pwdMd5 = Md5.getMD5Str(password);
		if (pwdMd5 == null || !pwdMd5.equals(loginUser.getPassword()))
		{
			throw new SupplyException("登录失败，此用户名或密码不正确");
		}
		loginUser.setUsername(username);
		loginUser.setPassword(null);
		return loginUser;
	}

	
}

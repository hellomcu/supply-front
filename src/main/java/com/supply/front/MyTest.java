package com.supply.front;

//import org.junit.Test;

import com.supply.front.auth.util.JwtUtil;

//import junit.framework.TestCase;

public class MyTest
{

	// @Test
	// public void testMd5()
	// {
	// System.out.println(Md5.getMD5Str("store123456"));
	// }
	//
	
	//@Test
	public void testJwt()
	{
		String jwt = JwtUtil.createJwt("战士");
		// System.out.println(jwt);
		System.out.println("result-->" + JwtUtil.parseJWT( jwt + " 1"));
	}
}

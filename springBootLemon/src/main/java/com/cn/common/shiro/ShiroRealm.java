package com.cn.common.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 
 * ShiroRealm:Shiro认证Realm
 * @Project Name:spring-boot-demo 
 * @File Name:ShiroRealm.java 
 * @Package Name:com.example.myproject.sysconf.shiro
 * @Creator:GUO CHUN
 * @Date:2017年12月12日下午12:39:57
 */
public class ShiroRealm extends AuthorizingRealm {
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		
		String userName = new String(token.getUsername());
		
		String password = new String(token.getPassword());
		
		return new SimpleAuthenticationInfo(userName, password, getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("===================>>开始认证！！！！！！！");
		return null;
	}

}

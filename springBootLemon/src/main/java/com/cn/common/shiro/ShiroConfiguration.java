package com.cn.common.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 
 * ShiroConfiguration:Shiro配置类
 * @Project Name:spring-boot-demo 
 * @File Name:ShiroConfiguration.java 
 * @Package Name:com.example.myproject.sysconf.shiro
 * @Creator:GUO CHUN
 * @Date:2017年12月12日下午12:38:11
 */
@Configuration
@Order(1)
public class ShiroConfiguration {
	
	//整合了redis的sessionDao
	@Autowired
	private ShiroSessionRedisDao shiroSessionRedisDao;

	/**
	 * 
	 * shiroFilter:(ShiroFilterFactoryBean 处理拦截资源文件问题).
	 * @param securityManager
	 * @return 
	 * @return :ShiroFilterFactoryBean 
	 * @Creator:GUO CHUN
	 * @Date:2017年12月2日 下午9:26:48
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		//过滤器Map
		Map<String, Filter> filtersMap = shiroFilterFactoryBean.getFilters();
		//自定义用户请求过滤器
		UsersFilter usersFilter = new UsersFilter();
		filtersMap.put("usersFilter", usersFilter);
		//设置过滤器
		shiroFilterFactoryBean.setFilters(filtersMap);
		
		//拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		
		//控制请求对应拦截器
		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
		filterChainDefinitionMap.put("/**", "usersFilter");//使用自定义过滤器
//		filterChainDefinitionMap.put("/**", "authc");//使用默认过滤器
		
		//设置拦截器
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilterFactoryBean;
	}
	
	/**
	 * 
	 * securityManager:(身份认证realm，需要自己实现，账号密码校验，权限等).
	 * @return 
	 * @return :SecurityManager 
	 * @Creator:GUO CHUN
	 * @Date:2017年12月2日 下午9:25:23
	 */
	@Bean
	public SecurityManager securityManager() {
		
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		
		//设置Realm
		ShiroRealm shiroRealm = new ShiroRealm();
		securityManager.setRealm(shiroRealm);
		
		//设置SessionManager
		securityManager.setSessionManager(sessionManager());
		
		return securityManager;
		
	}
	
	/**
	 * 
	 * sessionManager:(sessionManager整合redis的sessionDao).
	 * @return 
	 * @return :SessionManager 
	 * @Creator:GUO CHUN
	 * @Date:2017年12月8日 上午10:26:00
	 */
	@Bean
	public SessionManager sessionManager() {
		DefaultSessionManager sessionManager = new DefaultSessionManager();
		
		//设置ShiroSession超时时间
		sessionManager.setGlobalSessionTimeout(1800000);
		//删除过期session
		sessionManager.setDeleteInvalidSessions(true);
		
		//设置整合了redis的sessionDao
//		SessionDAO sessionDAO = new ShiroSessionRedisDao();
		sessionManager.setSessionDAO(shiroSessionRedisDao);
		
		return sessionManager;
	}
	
	/**
	 * 
	 * authorizationAttributeSourceAdvisor:(开启shiro控制aop支持).
	 * @param securityManager
	 * @return 
	 * @return :AuthorizationAttributeSourceAdvisor 
	 * @Creator:GUO CHUN
	 * @Date:2017年12月2日 下午9:34:28
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
	
}

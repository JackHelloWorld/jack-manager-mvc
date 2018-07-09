package com.jack.manager.common.config;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * 配置初始化
 * @author liuJack
 *
 */
@Component("Config")
public class Config implements ApplicationListener<ContextRefreshedEvent>{

	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		try {
			Properties prop =  new  Properties();    
			InputStream in = this.getClass().getResourceAsStream("/config.properties");
			prop.load(in);    
			Const.Page.currentPage=Integer.parseInt(prop.getProperty("currentPage"));
			Const.Page.pageSize=Integer.parseInt(prop.getProperty("pageSize"));
			Const.RSA.modulus=prop.getProperty("modulus");
			Const.RSA.privateExponent=prop.getProperty("privateExponent");
			Const.RSA.publicExponent=prop.getProperty("publicExponent");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

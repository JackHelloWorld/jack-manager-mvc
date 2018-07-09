package com.jack.manager.dao.wechat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.wechat.WeChatPayInfo;

@Repository
public interface WeChatPayInfoRepository extends JpaRepository<WeChatPayInfo,Integer>{

	/**
	 * 更加订单号获得订单数据
	 * @Title 
	 * @Desc 
	 * @param outTradeNo
	 * @return
	 */
	public WeChatPayInfo getTop1ByOutTradeNo(String outTradeNo);
	
}

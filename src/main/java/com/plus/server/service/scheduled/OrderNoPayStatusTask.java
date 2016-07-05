package com.plus.server.service.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderNoPayStatusTask {
	private static final Logger log = LoggerFactory.getLogger(OrderNoPayStatusTask.class);

	/**
	 * 每分钟检查未支付订单，若超过最大未支付等待时间，则将订单状态设置为取消
	 */
//	@Scheduled(cron = "0 * * * * ? ")
	@Transactional(rollbackFor = Exception.class)
	public void setStatus2CacelOfNoPayOrder() {
		log.info("未支付订单超时任务启动...");
		log.info("未支付订单超时任务执行完毕！");
	}
	
}

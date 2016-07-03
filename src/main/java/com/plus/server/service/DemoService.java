package com.plus.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.plus.server.dal.UserDAO;
import com.plus.server.model.User;

@Service
public class DemoService {
	private static final Logger log = LoggerFactory.getLogger(DemoService.class);

	@Autowired
	private UserDAO userDao;

	@Transactional
	public void addUser(User user) {
		log.info("新增用户，user=" + JSON.toJSONString(user));
		this.userDao.insert(user);
	}

}

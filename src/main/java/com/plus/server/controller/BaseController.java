package com.plus.server.controller;

import com.plus.server.model.Organization;
import com.plus.server.model.User;
import com.plus.server.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.plus.server.common.vo.UserVo;
import javax.servlet.http.*;

@RestController
public class BaseController {

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	protected HttpServletRequest httpRequest;
	@Autowired
	protected HttpSession httpSession;
	@Autowired
	protected HttpServletResponse httpResponse;

	@ResponseBody
	public User getCurrentUser() {
		User u = (User) httpRequest.getSession().getAttribute("user");
		return u;
	}

	@ResponseBody
	public UserRole getCurrentUserRole() {
		UserRole ur = (UserRole) httpRequest.getSession().getAttribute("userRole");
		return ur;
	}

	@ResponseBody
	public Organization getCurrentUserBrand() {
		Organization brand = (Organization) httpRequest.getSession().getAttribute("brand");
		return brand;
	}
	
}

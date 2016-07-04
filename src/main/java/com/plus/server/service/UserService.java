package com.plus.server.service;

import com.alibaba.fastjson.JSON;
import com.plus.server.dal.OrganizationDAO;
import com.plus.server.dal.UserDAO;
import com.plus.server.dal.UserRoleDAO;
import com.plus.server.model.Organization;
import com.plus.server.model.User;
import com.plus.server.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

/**
 * Created by jiangwulin on 16/7/3.
 */
@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(DemoService.class);

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private OrganizationDAO organizationDAO;

    @Transactional
    public void addUser(String name, String pwd, long roleId, long orgId) {
        User user = new User();
        user.setName(name);
        String password_salt = generateSalt(5);
        String password_hash = getPasswordHash(pwd, password_salt);
        user.setPasswordSalt(password_salt);
        user.setPasswordHash(password_hash);
        user.setOrgId(orgId);
        user.setValid(1);
        user.setGmtCreate(new Date());
        user.setGmtModify(new Date());
        log.info("新增用户，user=" + JSON.toJSONString(user));
        this.userDao.insert(user);

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(roleId);
        userRole.setValid(1);
        userRole.setGmtCreate(new Date());
        userRole.setGmtModify(new Date());
        log.info("新增用户角色，userRole=" + JSON.toJSONString(userRole));
        this.userRoleDAO.insert(userRole);
    }

    public User login(String name, String password) {
        User user = new User();
        user.setName(name);
        List<User> result = userDao.selectByModel(user);
        if(result != null && !result.isEmpty()) {
            user = result.get(0);
            String password_salt = user.getPasswordSalt();
            String password_hash = getPasswordHash(password, password_salt);
            if(password_hash.equals(user.getPasswordHash())){
                return user;
            }
        }
        return null;
    }

    private static String generateSalt(int size) {
        byte[] saltBytes = new byte[size];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(saltBytes);

        return Base64.getEncoder().encodeToString(saltBytes);
    }

    private static String getPasswordHash(String password, String salt) {
        String strResult = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update((password + salt).getBytes());
            byte[] byteBuffer = messageDigest.digest();
            StringBuffer strHexString = new StringBuffer();

            for (int i = 0; i < byteBuffer.length; i++) {
                String hex = Integer.toHexString(0xff & byteBuffer[i]);
                if (hex.length() == 1) {
                    strHexString.append('0');
                }
                strHexString.append(hex);
            }

            strResult = strHexString.toString();

        } catch (NoSuchAlgorithmException ex) {
            // TODO:log
        }
        return strResult.substring(0, 66);
    }


    public List<Map<String,String>> getUserList(String keyword) {
        List<Map<String,String>> result = new ArrayList<Map<String, String>>();
        User user = new User();
        user.setValid(1);
        List<User> list = this.userDao.selectByModel(user);
        UserRole userRole = new UserRole();
        userRole.setValid(1);
        List<UserRole> userRoles = this.userRoleDAO.selectByModel(userRole);
        Map<Long,Long> userRoleMap = new HashMap<Long, Long>();
        for(UserRole o : userRoles) {
            userRoleMap.put(o.getUserId(),o.getRoleId());
        }

        for (User u : list) {
            Map<String,String> map = new HashMap<String, String>();
            if(keyword == null || u.getId().toString().contains(keyword) || u.getName().contains(keyword)) {
                map.put("id", u.getId().toString());
                map.put("name", u.getName());
                map.put("org_id", u.getOrgId().toString());
                map.put("role_id", userRoleMap.get(u.getId()).toString());
                map.put("last_long_lat", u.getLastLongLat());
                result.add(map);
            }
        }
        return result;
    }

    public User getUser(long id){
        return userDao.selectByPrimaryKey(id);
    }
}

package com.plus.server.service;

import com.alibaba.fastjson.JSON;
import com.plus.server.dal.UserDAO;
import com.plus.server.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addUser(String name, String pwd) {
        User user = new User();
        user.setName(name);
        String password_salt = generateSalt(5);
        String password_hash = getPasswordHash(pwd, password_salt);
        user.setPasswordSalt(password_salt);
        user.setPasswordHash(password_hash);
        user.setValid(1);
        user.setGmtCreate(new Date());
        user.setGmtModify(new Date());
        log.info("新增用户，user=" + JSON.toJSONString(user));
        this.userDao.insert(user);
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


    public List<Map<String,String>> getUserList() {
        List<Map<String,String>> result = new ArrayList<Map<String, String>>();
        User user = new User();
        user.setValid(1);
        List<User> list = this.userDao.selectByModel(user);
        for (User u : list) {
            Map<String,String> map = new HashMap<String, String>();
            map.put("id",user.getId().toString());
            map.put("name",user.getName());
            map.put("org_id", user.getOrgId().toString());
            map.put("last_long_lat",user.getLastLongLat());
            result.add(map);
        }
        return result;
    }
}

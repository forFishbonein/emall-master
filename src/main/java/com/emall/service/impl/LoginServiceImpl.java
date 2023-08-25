package com.emall.service.impl;

import com.alibaba.fastjson.JSON;
import com.emall.common.R;
import com.emall.entity.User;
import com.emall.service.LoginService;
import com.emall.service.UserService;
import com.emall.utils.JWTUtils;
import com.emall.utils.ValidateCodeUtils;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@Primary
public class LoginServiceImpl implements LoginService {

    //盐
    private static final String slat = "emall!@#";

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public R<String> codeCheck(Map map) {

        //获取邮箱
        String userEmail = map.get("userEmail").toString();

        //获取密码
        String userPass = map.get("userPass").toString();
        if (StringUtils.isBlank(userEmail) || StringUtils.isBlank(userPass)){ //判断账号密码非空
            return R.error("账号或密码为空");
        }
        //密码进行md5加密
        String encryptedPassword = DigestUtils.md5Hex(userPass + slat);
        R<User> userInMysql = userService.getByUserEmail(userEmail);

        //如果用户不存在
        if (userInMysql == null){
            return R.error("用户不存在");
        }

        //进行密码比对
        if(userInMysql.getData().getUserPass().equals(encryptedPassword)){
            //如果用户存在，即登录成功，使用JWT生成token，返回token
            String token = JWTUtils.createToken(userInMysql.getData().getUserId());
            //将token放到redis中，过期时间为1天
            redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(userInMysql),1, TimeUnit.DAYS); //使用fastjson转换对象为json
            return R.success(token);
        }
        return R.error("密码错误或无效");
    }

    @Override
    public R<String> passwordCheck(Map map) {
        //获取邮箱
        String userEmail = map.get("userEmail").toString();

        //获取密码
        String userPass = map.get("userPass").toString();
        if (StringUtils.isBlank(userEmail) || StringUtils.isBlank(userPass)){ //判断账号密码非空
            return R.error("账号或密码为空");
        }

        //密码进行md5加密
        String encryptedPassword = DigestUtils.md5Hex(userPass + slat);
        R<User> userInMysql = userService.getByUserEmail(userEmail);

        //如果用户不存在
        if (userInMysql == null){
            return R.error("用户不存在");
        }

        //进行密码比对
        if(userInMysql.getData().getUserPass().equals(encryptedPassword)){
            //如果用户存在，即登录成功，使用JWT生成token，返回token
            String token = JWTUtils.createToken(userInMysql.getData().getUserId());
            //将token放到redis中，过期时间为1天
            redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(userInMysql),1, TimeUnit.DAYS); //使用fastjson转换对象为json
            return R.success(token);
        }

        return R.error("密码错误或无效");
    }

    @Override
    public R<String> register(User user) {

        //密码进行md5加密
        String encryptedPassword = DigestUtils.md5Hex(user.getUserPass() + slat);
        user.setUserPass(encryptedPassword); //密码重新改写一下为md5的
        userService.save(user); //直接保存user对象

        //token
        String token = JWTUtils.createToken(user.getUserId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),1, TimeUnit.DAYS);

        return R.success(token);
    }

    @Override
    public R logout(String token) {
        redisTemplate.delete("TOKEN_"+token);
        return R.success(null);
    }

    @Override
    public R<String> isRegistered(User user) {
        //查询邮箱是否注册
        String userEmail = user.getUserEmail();
        R<User> userInMysql = userService.getByUserEmail(userEmail);

        if (userInMysql.getData() == null) {
            return R.error("该邮箱未注册！");
        }

        R r = new R();
        r.setCode(1);
        r.setData(userInMysql);
        r.setMsg("该邮箱已存在");
        return r;
    }

    @Override
    public R<String> sendEmail(User user) {
        //获取用户邮箱
        String userEmail = user.getUserEmail();

        //用户邮箱不为空时，发送邮件
        if (StringUtils.isNotEmpty(userEmail)) {
            log.warn("已填写邮箱");

            //判断是否发送过验证码，若已经发送过验证码，则清除缓存的验证码
            if (redisTemplate.hasKey(userEmail)) {
                log.warn("5分钟内已发送过验证码");
                redisTemplate.delete(userEmail);
            }

            //邮件标题
            String subject = "邮件注册验证码";

            //生成验证码
            String code = ValidateCodeUtils.generateValidateCode(6).toString();
            log.warn("验证码已生成：" + code);

            //邮件内容
            String context = "验证码300s内有效，若重复发送验证码，请使用最新收到的验证码：" + code;

            //发送邮件
            userService.sendEmail(userEmail, subject, context);

            //将userEmail和code组成的key-value存入redis
            redisTemplate.opsForValue().set(userEmail, code, 5, TimeUnit.MINUTES);


            log.info("验证码发送成功");
            return R.success("验证码发送成功");

        }

        log.info("验证码发送失败");
        return R.error("验证码发送失败");

    }


    //检验token是否正确
    @Override
    public User checkToken(String token) {
        if(StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> map = JWTUtils.checkToken(token); //自定义的解析token的方法，解析传进来的token
        if (map == null){
            return null;
        }
        String userJson = (String) redisTemplate.opsForValue().get("TOKEN_" + token); //得到redis里面的token
        if (StringUtils.isBlank(userJson)){ //如果redis里面没有匹配的token
            return null;
        }
        User user = JSON.parseObject(userJson, User.class); //把userJson解析为user对象，得到了user对象
        return user;
    }
}

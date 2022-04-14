package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.Result;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @program: SpringBoot_01
 * @description: 短信发送
 * @author: wyh
 * @createDate: 2021-04-27 22:24
 **/
@RestController
@RequestMapping("/validate")
public class ValidateController {
    //短信平台相关参数
    //这个不用改
    private String apiUrl = "https://sms_developer.zhenzikj.com";
    //榛子云系统上获取
    private String appId = "111397";
    private String appSecret = "e540b074-b991-4bb8-b15e-cc0360812e60";

    @RequestMapping("/sendCode/{memPhone}")
    @CrossOrigin
    public Result<?> getCode(@PathVariable String memPhone){
        try {
            JSONObject json = null;
            //随机生成验证码
            String code = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            Map<String, Object> params = new HashMap<String, Object>();
            //前台输入的手机号
            params.put("number", memPhone);
            //这个模板id对应的是榛子云个人中心的模板id
            params.put("templateId", 8945);
            String[] templateParams = new String[2];
            templateParams[0] = code;
            templateParams[1] = "5分钟";
            params.put("templateParams", templateParams);
            String result = client.send(params);
//            System.out.println(result);
            json = JSONObject.parseObject(result);
            if (json.getIntValue("code")!=0){//发送短信失败
                return Result.error("1","msg fail to send");
            }
            return Result.success(code);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("1","msg fail to send");
        }
    }

}



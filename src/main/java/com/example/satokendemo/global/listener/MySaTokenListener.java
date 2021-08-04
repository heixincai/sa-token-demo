package com.example.satokendemo.global.listener;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 自定义侦听器的实现
 */
@Slf4j
@Component
public class MySaTokenListener implements SaTokenListener {

    /** 每次登录时触发 */
    @Override
    public void doLogin(String loginType, Object loginId, SaLoginModel loginModel) {
      log.info("登录：{},{},{}",loginType,loginId,loginModel.toString());
    }

    /** 每次注销时触发 */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        // ...
    }

    /** 每次被踢下线时触发 */
    @Override
    public void doLogoutByLoginId(String loginType, Object loginId, String tokenValue, String device) {
        // ...
    }

    /** 每次被顶下线时触发 */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue, String device) {
        // ...
    }

    /** 每次被封禁时触发 */
    @Override
    public void doDisable(String loginType, Object loginId, long disableTime) {
        // ...
    }

    /** 每次被解封时触发 */
    @Override
    public void doUntieDisable(String loginType, Object loginId) {
        // ...
    }

    /** 每次创建Session时触发 */
    @Override
    public void doCreateSession(String id) {
        // ...
    }

    /** 每次注销Session时触发 */
    @Override
    public void doLogoutSession(String id) {
        // ...
    }

}


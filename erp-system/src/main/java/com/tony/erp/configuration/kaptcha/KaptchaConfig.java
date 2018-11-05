package com.tony.erp.configuration.kaptcha;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 初始化随机验证码生成器
 */
@Component
public class KaptchaConfig {

    @Bean
    public ServletRegistrationBean<KaptchaServlet> kaptchaServletServletRegistrationBean() {
        ServletRegistrationBean<KaptchaServlet> registrationBean = new ServletRegistrationBean<>(new KaptchaServlet(), "/captcha/kaptcha.jpg");

        registrationBean.addInitParameter(Constants.KAPTCHA_SESSION_CONFIG_KEY,
                Constants.KAPTCHA_SESSION_KEY);
        //宽度
        registrationBean.addInitParameter(Constants.KAPTCHA_IMAGE_WIDTH, "200");
        //高度
        registrationBean.addInitParameter(Constants.KAPTCHA_IMAGE_HEIGHT, "100");
        //字体大小
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "60");
//        registrationBean.addInitParameter(Constants.KAPTCHA_BORDER_THICKNESS,"1"); //边框
        //无边框
        registrationBean.addInitParameter(Constants.KAPTCHA_BORDER, "no");
        //文字颜色
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
//        //长度
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
//        //字符间距
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "6");

        registrationBean.addInitParameter(Constants.KAPTCHA_NOISE_COLOR, "green");

        registrationBean.addInitParameter(Constants.KAPTCHA_BACKGROUND_CLR_FROM, "white");

        registrationBean.addInitParameter(Constants.KAPTCHA_BACKGROUND_CLR_TO, "white");

        return registrationBean;
    }
}

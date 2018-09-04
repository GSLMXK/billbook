package com.xk.billbook.admin.common.config;

import com.xk.billbook.admin.common.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${file.location}")
    private String location;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截规则：除了login，其他都拦截判断
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/User/login","/User/loginCheck","/error","/static/**");
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize("2MB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("10MB");
        return factory.createMultipartConfig();
    }
    /**
     * 访问外部文件配置，访问D盘下文件
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //配置server虚拟路径，handler为jsp中访问的目录，locations为files相对应的本地路径
        registry.addResourceHandler("/files/**").addResourceLocations("file:"+location);
    }
}
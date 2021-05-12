package com.daelim.transactions.configuration;

import com.daelim.transactions.interceptor.LoginInterceptor;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    //  file:///c:/fileUpload/images/
    @Value("${custom.path.upload-images}")
    private String ImagePath;

    private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxUploadSizePerFile(5*1024*1024);
        return multipartResolver;
    }


    /**
     * 파일 저장 경로를 프로젝트 내부가 아닌 외부로 지정
     * 내부 경로로 설정할 경우 웹 어플리케이션 재배포시 파일이 삭제되는 현상이 발생
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/profile/**")
                .addResourceLocations(ImagePath +"/profile/");
//        registry.addResourceHandler("/images/main/**")
//                .addResourceLocations(ImagePath);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/*/*.do");
    }

}



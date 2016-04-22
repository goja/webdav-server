package com.sentriot.proto;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebdavApplication {

    @Value("${document.root}")
    private String documentRoot;

    public static void main(final String[] args)
    {
        SpringApplication.run(WebdavApplication.class, args);
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer()
    {
        final TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setDocumentRoot(new File(documentRoot));
        return factory;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean()
    {
        return new ServletRegistrationBean(new WebdavServlet(), "/webdav/*");
    }
}

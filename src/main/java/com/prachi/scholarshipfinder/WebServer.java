package com.prachi.scholarshipfinder;

import com.prachi.scholarshipfinder.config.AppConfig;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class WebServer {

    public static void main(String[] args) throws Exception {

        System.out.println("=================================");
        System.out.println("Starting Scholarship Finder Server");
        System.out.println("=================================");

        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);

        tomcat.getConnector();

        Context context = tomcat.addContext(
                "",
                new File(".").getAbsolutePath()
        );

        AnnotationConfigWebApplicationContext applicationContext =
                new AnnotationConfigWebApplicationContext();

        applicationContext.register(AppConfig.class);

        DispatcherServlet dispatcherServlet =
                new DispatcherServlet(applicationContext);

        Wrapper servlet =
                Tomcat.addServlet(
                        context,
                        "dispatcher",
                        dispatcherServlet
                );

        servlet.setLoadOnStartup(1);

        servlet.addMapping("/");

        tomcat.start();

        System.out.println();
        System.out.println("=================================");
        System.out.println("SERVER STARTED SUCCESSFULLY");
        System.out.println("=================================");
        System.out.println("Open:");
        System.out.println("http://localhost:8080");
        System.out.println("=================================");

        tomcat.getServer().await();
    }
}
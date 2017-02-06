package com.malei.configuration;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
       ctx.register(AppConfig.class, SecurityConfiguration.class);

        ctx.setServletContext(container);

        container.addListener(new ContextLoaderListener(ctx));
        ctx.setServletContext(container);


        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        FilterRegistration.Dynamic filterOpenSession = container.addFilter("openEntityManagerInViewFilter", OpenSessionInViewFilter.class);
        filterOpenSession.setInitParameter("singleSession", "true");
        filterOpenSession.addMappingForServletNames(null, true, "dispatcher");

        FilterRegistration.Dynamic filterEncoding = container.addFilter("characterEncodingFilter", CharacterEncodingFilter.class);
        filterEncoding.setInitParameter("encoding", "UTF-8");
        filterEncoding.setInitParameter("forceEncoding", "true");
        filterEncoding.addMappingForServletNames(null, true, "dispatcher");




    }

}
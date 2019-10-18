package com.codenotfound.ws.endpoint;

import java.util.List;

import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

  @Bean
  public ServletRegistrationBean<Servlet> messageDispatcherServlet(ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    return new ServletRegistrationBean<>(servlet, "/codenotfound/ws/*"); // Used as part of the HTTP path
  }

  @Override
  public void addInterceptors(List<EndpointInterceptor> interceptors) {
    CustomValidationInterceptor validatingInterceptor = new CustomValidationInterceptor();
    validatingInterceptor.setValidateRequest(true);
    validatingInterceptor.setValidateResponse(true);
    validatingInterceptor.setXsdSchema((trans70XSD()));
    interceptors.add(validatingInterceptor);
  }

    @Bean
    public XsdSchema trans70XSD(){
        return new SimpleXsdSchema(new ClassPathResource("/xsd/trans70.xsd"));
    }  

  @Bean(name = "helloworld") // Used as part of the HTTP path
  public Wsdl11Definition defaultWsdl11Definition() {
    SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
    wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/helloworld.wsdl"));
    return wsdl11Definition;
  }

  @Bean(name = "trans70") // Used as part of the HTTP path
  public Wsdl11Definition defaultWsdl11Definition2() {
    SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
    wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/trans70.wsdl"));
    return wsdl11Definition;
  }
}
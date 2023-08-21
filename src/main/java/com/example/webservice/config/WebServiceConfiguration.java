package com.example.webservice.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/service/*");
	}
	
	@Bean(name = "users")
	DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema userSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("soturnoServiceSoapHttp");
		wsdl11Definition.setLocationUri("/service");
		wsdl11Definition.setTargetNamespace("rivaldo.dev.br/user-spring-soap");
		wsdl11Definition.setSchema(userSchema);
		return wsdl11Definition;
	}
	
	@Bean
	XsdSchema userSchema() {
		return new SimpleXsdSchema(new ClassPathResource("/xsd/user.xsd"));
	}
}

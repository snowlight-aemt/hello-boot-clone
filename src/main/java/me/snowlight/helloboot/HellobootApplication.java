package me.snowlight.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
public class HellobootApplication {
	public static void main(String[] args) {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				ServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
				WebServer webServer = servletWebServerFactory.getWebServer(servletContext -> {

					servletContext.addServlet("dispatcherServlet",
									new DispatcherServlet(this))
							.addMapping("/*");
				});

				webServer.start();
			}
		};

		applicationContext.register(HellobootApplication.class);
		applicationContext.refresh();
 	}
}

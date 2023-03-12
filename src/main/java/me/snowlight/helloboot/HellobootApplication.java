package me.snowlight.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

public class HellobootApplication {

	public static void main(String[] args) {
		ServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
		WebServer webServer = servletWebServerFactory.getWebServer();
		webServer.start();
	}

}

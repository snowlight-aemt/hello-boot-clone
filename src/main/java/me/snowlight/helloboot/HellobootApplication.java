package me.snowlight.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		ServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
		WebServer webServer = servletWebServerFactory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					String name = req.getParameter("name");

					resp.setStatus(200);
					resp.setHeader("Content-Type", "text/plain");
					resp.getWriter().println("Hello Servlet " + name);
				}
			}).addMapping("/hello");
		});

		webServer.start();
	}

}

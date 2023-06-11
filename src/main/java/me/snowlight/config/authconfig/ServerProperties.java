package me.snowlight.config.authconfig;

import me.snowlight.config.MyConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@MyConfigurationProperties
public class ServerProperties {
    private String contextPath;

    private int port;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

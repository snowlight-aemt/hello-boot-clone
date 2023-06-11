package me.snowlight.config.authconfig;

import me.snowlight.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@MyAutoConfiguration
public class PropertySourcesPlaceholderConfig {
    @Bean
    PropertySourcesPlaceholderConfigurer propertiesPropertySource() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

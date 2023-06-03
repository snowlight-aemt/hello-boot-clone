package me.snowlight.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {
    @Test
    void not_same_new_object_java() {
        MyConfig myConfig = new MyConfig();
        Bean1 bean1 = myConfig.bean1();
        Bean2 bean2 = myConfig.bean2();

        Assertions.assertThat(bean1.getCommon()).isNotSameAs(bean2.getCommon());
    }

    @Test
    void same_objects_proxied_on_java() {
        ProxyMyConfig proxyMyConfig = new ProxyMyConfig();
        Bean1 bean1 = proxyMyConfig.bean1();
        Bean2 bean2 = proxyMyConfig.bean2();

        Assertions.assertThat(bean1.getCommon()).isSameAs(bean2.getCommon());
    }

    @Test
    void same_new_objects_on_spring() {
        var ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        Assertions.assertThat(bean1.getCommon()).isSameAs(bean2.getCommon());
    }

    @Test
    void not_same_objects_on_spring_by_no_proxy_bean_method() {
        var ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfigNoProxyBean.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        Assertions.assertThat(bean1.getCommon()).isNotSameAs(bean2.getCommon());
    }

    @Configuration(proxyBeanMethods = false)
    static class MyConfigNoProxyBean {
        @Bean
        public Common common() {
            return new Common();
        }

        @Bean
        public Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2(common());
        }
    }

    @Configuration
    static class MyConfig {
        @Bean
        public Common common() {
            return new Common();
        }

        @Bean
        public Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Common { }

    static class Bean1 {
        private final Common common;

        public Common getCommon() {
            return common;
        }

        public Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        public Common getCommon() {
            return common;
        }

        public Bean2(Common common) {
            this.common = common;
        }
    }

    static class ProxyMyConfig extends MyConfig {
        private Common common = null;

        public Common common() {
            if (this.common == null)
                this.common = new Common();

            return this.common;
        }
    }
}

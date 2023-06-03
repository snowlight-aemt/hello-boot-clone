package me.snowlight.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class ConditionalTest {
    @Test
    void conditional() {
        new ApplicationContextRunner().withUserConfiguration(MyConfig1.class).run(context -> {
            Assertions.assertThat(context).hasSingleBean(Bean1.class);
            Assertions.assertThat(context).hasSingleBean(MyConfig1.class);
        });

        new ApplicationContextRunner().withUserConfiguration(MyConfig2.class).run(context -> {
            Assertions.assertThat(context).doesNotHaveBean(Bean1.class);
            Assertions.assertThat(context).doesNotHaveBean(MyConfig2.class);
        });
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    private @interface BooleanConditional {
        boolean value();
    };

    @Configuration
    @BooleanConditional(true)
    static class MyConfig1 {
        @Bean
        public Bean1 bean() {
            return new Bean1();
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class MyConfig2 {
        @Bean
        public Bean1 bean() {
            return new Bean1();
        }
    }

    static class Bean1 {
    }

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> attributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            return (Boolean) attributes.get("value");
        }
    }
}

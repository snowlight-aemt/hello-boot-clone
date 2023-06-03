package me.snowlight.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

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

    @Configuration
    @Conditional(TrueCondition.class)
    static class MyConfig1 {
        @Bean
        public Bean1 bean() {
            return new Bean1();
        }
    }

    @Configuration
    @Conditional(FalseCondition.class)
    static class MyConfig2 {
        @Bean
        public Bean1 bean() {
            return new Bean1();
        }
    }

    static class Bean1 {
    }

    static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FalseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }
}

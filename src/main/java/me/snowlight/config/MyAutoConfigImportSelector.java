package me.snowlight.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
            "me.snowlight.config.authconfig.DispatcherServletConfig",
            "me.snowlight.config.authconfig.TomcatWebServerConfig"
        };
    }
}

package com.javaapi.test.spring.spring.feature.javaconfig.conditional.conditionalAnnotaionTest;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by user on 16/6/5.
 */
public class MacOsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        return context.getEnvironment().getProperty("os.name").contains("Mac OS X");
    }
}

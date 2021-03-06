package com.javaapi.test.spring.spring.pattern.strategy.sample1.strategy.context;

import com.javaapi.test.spring.spring.pattern.strategy.sample1.strategy.IContextStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by user on 2018/8/24
 */
@Component
public class ContextStrategy {
    @Autowired
    private Map<String, IContextStrategy> contextStrategy;

    public Map<String, IContextStrategy> getContextStrategy() {
        return contextStrategy;
    }

    public void setContextStrategy(Map<String, IContextStrategy> contextStrategy) {
        this.contextStrategy = contextStrategy;
    }

    public IContextStrategy doStrategy(String type) {

        return this.contextStrategy.get(type);
    }


}

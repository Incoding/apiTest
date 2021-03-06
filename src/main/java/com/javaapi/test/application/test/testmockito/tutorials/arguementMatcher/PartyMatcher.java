package com.javaapi.test.application.test.testmockito.tutorials.arguementMatcher;

import org.mockito.ArgumentMatcher;

import com.google.common.base.Function;

public class PartyMatcher<T> extends ArgumentMatcher<T> {
    private Object value;
    private Function<T, Object> function;
 
    public PartyMatcher(Function<T, Object> getProperty, Object value) {
        this.value = value;
        this.function = getProperty;
    }
 
    public static <F> PartyMatcher<F> partyMatcher(Function<F, Object> getProperty, Object value) {
        return new PartyMatcher<F>(getProperty, value);
    }
 
    @Override
    public boolean matches(Object o) {
        return function.apply((T) o).equals(value);
    }
}
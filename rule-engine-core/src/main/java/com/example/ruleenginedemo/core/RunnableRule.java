package com.example.ruleenginedemo.core;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-20
 */
public class RunnableRule {

    Rule rule;
    Predicate<Map<String,Object>> predicate;
    Function<Map<String,Object>,Object> function;

    public RunnableRule(Rule rule) {
        this.rule = rule;
        this.predicate = rule.getWhen().buildPredicate();
        this.function = rule.getThen().buildFunction();
    }

    public boolean test(Map<String,Object> argMap){
        return predicate.test(argMap);
    }

    public Object apply(Map<String,Object> argMap){
        return function.apply(argMap);
    }
}

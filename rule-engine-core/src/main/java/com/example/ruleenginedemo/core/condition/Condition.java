package com.example.ruleenginedemo.core.condition;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-20
 */
public interface Condition {

    Predicate<Map<String,Object>> buildPredicate();

}

package com.example.ruleenginedemo.core.condition;


import com.example.ruleenginedemo.core.enums.LogicType;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-20
 */
public class AndCondition extends LogicCondition {
    @Override
    public LogicType getLogicType() {
        return LogicType.AND;
    }

    @Override
    public BinaryOperator<Predicate<Map<String, Object>>> getReducePredicate() {
        return Predicate::and;
    }
}

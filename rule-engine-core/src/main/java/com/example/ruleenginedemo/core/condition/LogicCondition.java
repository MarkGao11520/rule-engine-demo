package com.example.ruleenginedemo.core.condition;

import com.example.ruleenginedemo.core.enums.LogicType;
import com.example.ruleenginedemo.core.exception.DefaultException;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-20
 */
@Data
public abstract class LogicCondition implements Condition {
     protected List<Condition> childConditionList;

     private Predicate<Map<String, Object>> predicate;

     @Override
     public Predicate<Map<String, Object>> buildPredicate() {
          if(predicate == null){
               childConditionList.stream()
                       .map(Condition::buildPredicate)
                       .reduce(getReducePredicate())
                       .orElseThrow(()->new DefaultException("构建逻辑表达式，AND逻辑表达式无子条件"));
          }
          return predicate;
     }

     public abstract LogicType getLogicType();

     public abstract BinaryOperator<Predicate<Map<String,Object>>> getReducePredicate();
}

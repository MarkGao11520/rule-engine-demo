package com.example.ruleenginedemo.core;

import com.example.ruleenginedemo.core.value.Value;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-20
 */
@Data
public class RuleSet {
    private List<Rule> ruleList;
    private Value defaultValue;

    private Function<Map<String,Object>,Object> runnableFunction;
    private Function<Map<String,Object>,Object> defaultFunction;

    public Function<Map<String,Object>,Object> buildRunFunction() {
        if (runnableFunction == null) {
            defaultFunction = defaultValue.buildFunction();
            List<RunnableRule> runnableRuleList = ruleList.stream().map(RunnableRule::new).collect(Collectors.toList());
            runnableFunction = argMap -> {
                for (RunnableRule rule : runnableRuleList) {
                    if (rule.test(argMap)) {
                        return rule.apply(argMap);
                    }
                }
                return defaultFunction.apply(argMap);
            };
        }
        return runnableFunction;
    }

    public <T> T execute(Map<String,Object> argMap){
        return (T) buildRunFunction().apply(argMap);
    }
}

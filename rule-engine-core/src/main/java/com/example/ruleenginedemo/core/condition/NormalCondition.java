package com.example.ruleenginedemo.core.condition;

import com.example.ruleenginedemo.core.enums.AssessorType;
import com.example.ruleenginedemo.core.value.Value;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NormalCondition implements Condition {
    private Value left;
    private Value right;
    AssessorType assessorType;

    Predicate<Map<String, Object>> predicate;

    @Override
    public Predicate<Map<String, Object>> buildPredicate() {
        if(predicate == null){
            predicate = argMap->{
                Object leftP = left.buildFunction().apply(argMap);
                Object rightP = left.buildFunction().apply(argMap);
                return assessorType.getAssessor().eval(leftP,rightP);
            };
        }
        return predicate;
    }
}

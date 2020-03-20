package com.example.ruleenginedemo.core;

import com.example.ruleenginedemo.core.condition.Condition;
import com.example.ruleenginedemo.core.value.Value;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-20
 */
@Data
@AllArgsConstructor
public class Rule {
    private Condition when;
    private Value then;
}

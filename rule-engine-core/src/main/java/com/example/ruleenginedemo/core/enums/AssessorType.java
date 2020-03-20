package com.example.ruleenginedemo.core.enums;

import com.example.ruleenginedemo.core.assertor.Assessor;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-20
 */
@Getter
@AllArgsConstructor
public enum AssessorType {

    IN("IN",null);
    private String name;
    private Assessor assessor;
}

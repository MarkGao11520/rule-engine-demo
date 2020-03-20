package com.example.ruleenginedemo.core.assertor;


import com.example.ruleenginedemo.core.enums.AssessorType;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-20
 */
public interface Assessor {

    /**
     * 执行判断
     * @param left
     * @param right
     * @return
     */
    boolean eval(Object left, Object right);

    /**
     * 执行类型
     * @return
     */
    AssessorType getAssessorType();
}

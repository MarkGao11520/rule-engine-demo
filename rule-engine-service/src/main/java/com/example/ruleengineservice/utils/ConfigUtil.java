package com.example.ruleengineservice.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-02
 */
@Getter
@Component
@PropertySource("classpath:config.properties")
public class ConfigUtil {

    @Value("${name}")
    private String name;
}

package com.example.ruleenginedemo.core.action;

import com.example.ruleenginedemo.core.action.annotation.ActionBean;
import com.example.ruleenginedemo.core.action.annotation.ActionMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-20
 */
public class ActionMethodRepository {

    private static final Map<String, Method> NAME_TO_METHOD = new HashMap<>();

    static {
        ServiceLoader.load(Action.class).forEach(actionClass -> {
            ActionBean actionBean = actionClass.getClass().getAnnotation(ActionBean.class);
            if(actionBean ==null){
                return;
            }
            final String firstLevelName = actionBean.name();
            for (Method method : actionClass.getClass().getMethods()) {
                ActionMethod actionMethod = method.getAnnotation(ActionMethod.class);
                if(actionMethod == null){
                    continue;
                }
                final String secondaryName = actionMethod.name();
                NAME_TO_METHOD.put(buildMethodKey(firstLevelName,secondaryName),method);
            }
        });
    }

    public static Optional<Method> getMethodKey(String key){
        return Optional.ofNullable(NAME_TO_METHOD.get(key));
    }

    private static String buildMethodKey(String firstLevelName, String secondaryName){
        return firstLevelName + "-" + secondaryName;
    }

    public static Optional<String[]> splitMethodKey(String methodKey){
        if(!NAME_TO_METHOD.containsKey(methodKey)){
            return Optional.empty();
        }
        String[] res = methodKey.split("-",-1);
        if(res.length != 2){
            return Optional.empty();
        }
        return Optional.of(res);
    }
}

package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    /**
     * 把Map中的值注入到对应的JavaBean属性中。
     * @param value
     * @param bean
     */
    public static <T> T copyParamToBean( Map value , T bean ){
        try {
            System.out.println("注入之前：" + bean);
            /**
             * 把所有请求的参数都注入到user对象中
             */
            BeanUtils.populate(bean, value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将String类型的图书编号装换为int
     * @param reqStr
     * @param defaultVar
     * @return
     */
    public static int parseInt(String reqStr,int defaultVar){
        try {
            return Integer.parseInt(reqStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultVar;
    }

}

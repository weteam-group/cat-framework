/*
 * Copyright (c) 2011 Born-To-Learn Ltd. All rights reserved.
 */

package cn.lakex.framework.validation.validator;

import cn.lakex.framework.validation.annotation.EnumValue;
import org.assertj.core.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 枚举值校验处理器
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/08/14 10:36
 * @since 1.0.0
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

    private Class<? extends Enum<?>> enumClass;
    private String enumMethod;

    @Override
    public void initialize(EnumValue enumValue) {
        enumClass = enumValue.enumClass();
        enumMethod = enumValue.enumMethod();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) throws RuntimeException {
        if (value == null) {
            return Boolean.TRUE;
        }

        if (enumClass == null || enumMethod == null) {
            return Boolean.TRUE;
        }

        Class<?> valueClass = value.getClass();

        try {
            Method method = enumClass.getMethod(enumMethod, valueClass);

            // 校验返回类型必须是 Boolean 类型
            if (!Boolean.TYPE.equals(method.getReturnType()) && Boolean.class.equals(method.getReturnType())) {
                throw new RuntimeException(Strings.formatIfArgs("%s must return is not boolean type in the %s class.",
                        enumMethod, enumClass));
            }

            // 校验 enum 内的校验方法必须是 static 的
            if (!Modifier.isStatic(method.getModifiers())) {
                throw new RuntimeException(Strings.formatIfArgs("%s method is not static method in the %s class.",
                        enumMethod, enumClass));
            }

            // 接收校验结果
            Boolean result = (Boolean) method.invoke(null, value);
            return result != null && result;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(Strings.formatIfArgs("This %s(%s) method does not exist in the %s",
                    enumMethod, valueClass, enumClass), e);
        }
    }
}

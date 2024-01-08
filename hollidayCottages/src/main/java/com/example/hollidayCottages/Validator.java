package com.example.hollidayCottages;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import io.micrometer.common.util.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.lang.reflect.Field;

public class Validator {
    public static String validate(String data) throws ExceptionWithMessage {
        if (StringUtils.isEmpty(data)) throw new ExceptionWithMessage("Please fill in all the required fields 1");
        String trimmedData = data.trim();
        String unescapedData = trimmedData.replace("\\", "");
        if (StringUtils.isEmpty(data)) throw new ExceptionWithMessage("Please fill in all the required fields 2");
        return StringEscapeUtils.escapeHtml4(unescapedData);
    }
    public static void validateFields(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String fieldValue = (String) field.get(obj);
                String validatedValue = validate(fieldValue);
                field.set(obj, validatedValue);
            } catch (IllegalAccessException | ExceptionWithMessage e) {
                e.printStackTrace();
            }
        }
    }
}

package com.fatih.petking.infrastructure.commons;

import lombok.experimental.UtilityClass;

import java.util.stream.Stream;

@UtilityClass
public final class ObjectHelper {

    public static boolean isNotNull(Object object) {
        return object != null;
    }

    public static boolean isNull(Object object) {
        return !isNotNull(object);
    }

    public static boolean isNotPresent(Object object) {
        return isNull(object);
    }

    public static boolean isPresent(Object object) {
        return isNotNull(object);
    }

    public static boolean isAllNotNull(Object... objects) {
        return Stream
                .of(objects)
                .noneMatch(ObjectHelper::isNull);
    }

    public static boolean isAllNull(Object... objects) {
        return !isAllNotNull(objects);
    }

    public static boolean isAllNotPresent(Object... objects) {
        return isAllNull(objects);
    }

    public static boolean isAllPresent(Object... objects) {
        return isAllNotNull(objects);
    }

    public static String doubleToString(Double value, String defaultIfNull) {
        return isNotPresent(value) ? defaultIfNull : value.toString();
    }

    public static String integerToString(Integer value, String defaultIfNull) {
        return isNotPresent(value) ? defaultIfNull : value.toString();
    }
}

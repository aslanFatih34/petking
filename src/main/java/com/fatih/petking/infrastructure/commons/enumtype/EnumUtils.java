package com.fatih.petking.infrastructure.commons.enumtype;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class EnumUtils {

    private EnumUtils() {
    }

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name, T defaultEnumTypeIfNotFound) {
        try {
            return Enum.valueOf(enumType, name);
        } catch (Exception e) {
            log.warn("Cannot find any enum constant related to given name: {}", name);
            return defaultEnumTypeIfNotFound;
        }
    }

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {
        return valueOf(enumType, name, null);
    }

    public static <T extends Enum<T> & ValueEnum<V>, V> T getValueEnum(Class<T> enumType, V value, T defaultEnumTypeIfNotFound) {
        T t = null;
        T[] enumConstants = enumType.getEnumConstants();

        for (T enumConstant : enumConstants) {
            t = getEnumConstantValue(enumConstant, value);
            if (t != null) {
                break;
            }
        }
        return (t == null) ? defaultEnumTypeIfNotFound : t;
    }

    private static <T extends Enum<T> & ValueEnum<V>, V> T getEnumConstantValue(T enumConstant, V value) {
        T t = null;
        if (value instanceof String) {
            if (((String) enumConstant.getValue()).equalsIgnoreCase((String) value)) {
                t = enumConstant;
            }
        } else if (enumConstant.getValue().equals(value)) {
            t = enumConstant;
        }
        return t;
    }

    public static <T extends Enum<T> & ValueEnum<V>, V> T getValueEnum(Class<T> enumType, V value) {
        return getValueEnum(enumType, value, null);
    }

    public static <T extends Enum<T> & ValueEnum<V>, V> boolean isValueEnum(Class<T> enumType, V value) {
        return getValueEnum(enumType, value) != null;
    }

    public static <T extends Enum<T> & ValueEnum<V>, V> boolean isNotValueEnum(Class<T> enumType, V value) {
        return !isValueEnum(enumType, value);
    }
}

package com.etnetera.hr.data;

/**
 * I am not sure if I understood this field correctly, but I would use enum with values that could be used for this field.
 * If it was supposed to be something custom, the {@link String} would fit better.
 */
public enum HypeLevel {
    LOW,
    MEDIUM,
    HIGH,
    VERY_HIGH
}

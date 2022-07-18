package com.umldesigner.infrastructure;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Endpoints {
    public static final String SCHEMA = "/s";
    /**
     * Table endpoint which doesn't contain {@value #SCHEMA} before it
     */
    public static final String TABLE_RAW = "/table";
    /**
     * Item endpoint which doesn't contain {@value #SCHEMA} before it
     */
    public static final String ITEM_RAW = "/item";
    /**
     * Table endpoint which contains {@value #SCHEMA} before it
     */
    public static final String TABLE = SCHEMA + TABLE_RAW;
    /**
     * Item endpoint which contains {@value #SCHEMA} before it
     */
    public static final String ITEM = SCHEMA + ITEM_RAW;
}

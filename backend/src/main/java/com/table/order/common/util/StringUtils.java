package com.table.order.common.util;

public class StringUtils {

    public static boolean isNullOrEmpty(String venueId) {
        if (venueId == null) return true;
        else if (venueId.trim().isEmpty()) return true;

        return false;
    }
}

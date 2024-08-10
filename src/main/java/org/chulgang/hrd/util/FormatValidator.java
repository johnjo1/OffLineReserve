package org.chulgang.hrd.util;

public class FormatValidator {
    public static boolean isNoValue(String target) {
        return target == null || target.isBlank();
    }
}

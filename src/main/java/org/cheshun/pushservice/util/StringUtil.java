package org.cheshun.pushservice.util;

import org.nutz.json.Json;

public class StringUtil {

    public static boolean noNull(String ... strings) {
        for (String string : strings) {
            if (string == null || string.equals(""))
                return false;
        }
        return true;
    }
}

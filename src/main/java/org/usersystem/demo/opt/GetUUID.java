package org.usersystem.demo.opt;


import java.util.Date;
import java.util.UUID;

public class GetUUID {
    private static Date date = new Date();

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").subSequence(1, 5).toString() + date.getTime();
    }
}

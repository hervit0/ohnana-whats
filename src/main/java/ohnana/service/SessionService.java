package ohnana.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;

public class SessionService {
    public static int getSillyId() {
        return new Random().nextInt(12);
    }

    public static String getTimeText() {
        String time = ZonedDateTime.now(ZoneId.of("Europe/London")).toString();
        return String.format("It's now %s", time);
    }
}

package io.github.protocol.pulsar;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {

    public static Random random = new Random();

    public static String randomString() {
        return UUID.randomUUID().toString();
    }

    public static int randomInt() {
        return random.nextInt();
    }

    public static long randomLong(){
        return random.nextLong();
    }

}

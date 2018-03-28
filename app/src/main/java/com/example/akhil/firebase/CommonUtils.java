package com.example.akhil.firebase;

import java.util.List;
import java.util.Random;

/**
 * Created by akhil on 28/3/18.
 */

public class CommonUtils {

    public static String randomString(int length) {
        final String OTPCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random RANDOM = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(OTPCharacters.charAt(RANDOM.nextInt(OTPCharacters.length())));
        }
        return sb.toString();
    }


    public static String randomImage() {
        String[] imagelist = new String[]{"http://cdn.playbuzz.com/cdn/dc684763-c546-43fa-80b8-91c5627d00b7/0e1d574c-3cc1-4008-8c80-98e900f2b76e.jpg",
                "https://www.apple.com/apple-events/september-2015/meta/og.png?201709291526",
                "http://cdn.playbuzz.com/cdn/dc684763-c546-43fa-80b8-91c5627d00b7/0e1d574c-3cc1-4008-8c80-98e900f2b76e.jpg",
                "http://orientindia.com/admin//130/evt_photo/9_img_blank_722012_9531632.JPG",
                "http://cdn.playbuzz.com/cdn/dc684763-c546-43fa-80b8-91c5627d00b7/0e1d574c-3cc1-4008-8c80-98e900f2b76e.jpg",
                "https://www.apple.com/apple-events/september-2015/meta/og.png?201709291526",
                "http://cdn.playbuzz.com/cdn/dc684763-c546-43fa-80b8-91c5627d00b7/0e1d574c-3cc1-4008-8c80-98e900f2b76e.jpg",
                "https://www.marshalsecurity.ca/wp-content/uploads/2014/02/event2.png",
                "https://financesonline.com/uploads/2017/10/ev.jpg"};

   Random random = new Random();
    return  imagelist[random.nextInt(imagelist.length)];

    }
}

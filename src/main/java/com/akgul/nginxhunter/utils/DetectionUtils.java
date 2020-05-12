package com.akgul.nginxhunter.utils;

import org.springframework.util.StringUtils;

public class DetectionUtils {
    public static String getNormalizedUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }

        if (url.contains("http://") || url.contains("https://")) {
            return url;
        }

        return "http://" + url;
    }
}

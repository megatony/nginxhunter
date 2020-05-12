package com.akgul.nginxhunter.utils;

import org.junit.Assert;
import org.junit.Test;

public class NginxDetectionUtilsTest {

    @Test
    public void shouldGetNormalizedUrl() {
        String rawUrl = "blog.detectify.com";

        Assert.assertEquals("http://blog.detectify.com", DetectionUtils.getNormalizedUrl(rawUrl));
    }

    @Test
    public void shouldNotAddHttpWhenUrlHaveHttp() {
        String rawUrl = "http://blog.detectify.com";

        Assert.assertEquals("http://blog.detectify.com", DetectionUtils.getNormalizedUrl(rawUrl));
    }

    @Test
    public void shouldNotAddHttpWhenUrlHaveHttps() {
        String rawUrl = "https://blog.detectify.com";

        Assert.assertEquals("https://blog.detectify.com", DetectionUtils.getNormalizedUrl(rawUrl));
    }
}

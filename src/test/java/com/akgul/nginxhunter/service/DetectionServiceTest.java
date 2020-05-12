package com.akgul.nginxhunter.service;

import com.akgul.nginxhunter.controller.DetectionController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class DetectionServiceTest {

    @InjectMocks
    DetectionService service;

    @Mock
    DetectionController controller;

    @Test
    public void shouldReturnTrueWhenServerIsNginxServer() throws IOException {
        String domainName = "http://blog.detectify.com";
        Mockito.when(controller.getServerType(domainName)).thenReturn("nginx");

        Assert.assertEquals(true, service.isServerTypeMatching(domainName, "nginx"));
    }

    @Test
    public void shouldReturnTrueWhenServerIsNginxServerWithVersion() throws IOException {
        String domainName = "http://blog.detectify.com";
        Mockito.when(controller.getServerType(domainName)).thenReturn("nginx/1.2.3.4");

        Assert.assertEquals(true, service.isServerTypeMatching(domainName, "nginx"));
    }

    @Test
    public void shouldReturnFalseWhenDomainIsEmpty() throws IOException {
        Assert.assertEquals(false, service.isServerTypeMatching("", "nginx"));
    }

    @Test
    public void shouldReturnFalseWhenServerTypeIsEmpty() throws IOException {
        String domainName = "";
        Mockito.when(controller.getServerType(domainName)).thenReturn("");

        Assert.assertEquals(false, service.isServerTypeMatching(domainName, "nginx"));
    }
}

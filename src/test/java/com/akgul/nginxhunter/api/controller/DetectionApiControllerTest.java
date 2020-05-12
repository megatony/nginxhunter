package com.akgul.nginxhunter.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DetectionApiControllerTest {
    @LocalServerPort
    private int port;

    private HttpHeaders headers = new HttpHeaders();
    private TestRestTemplate restTemplate = new TestRestTemplate();

    private String createApiUrl(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void shouldGetNginxDomainNames() {
        HttpEntity<?> httpEntity = new HttpEntity<>("[\"example.com\", \"blog.detectify.com\"]", headers);
        ResponseEntity<Map> response = restTemplate.exchange(createApiUrl("/detectionapi/detectdomains/nginx"),
                HttpMethod.POST, httpEntity, Map.class);
        Assert.isTrue((response.getBody()).keySet().iterator().next().equals("blog.detectify.com"), "Message is not valid");
        Assert.isTrue(response.getBody().keySet().size() == 1, "Size not valid");
    }

    @Test
    public void shouldGetDifferentDomainNames() {
        HttpEntity<?> httpEntity = new HttpEntity<>("[\"example.com\", \"https://d1.awsstatic.com\"]", headers);
        ResponseEntity<Map<String, List<String >>> response = restTemplate.exchange(createApiUrl("/detectionapi/detectdomains/AmazonS3"),
                HttpMethod.POST, httpEntity, new ParameterizedTypeReference<Map<String, List<String >>>() {});
        Assert.isTrue((response.getBody()).keySet().iterator().next().equals("https://d1.awsstatic.com"), "Message is not valid");
        Assert.isTrue(response.getBody().keySet().size() == 1, "Size not valid");
    }

}

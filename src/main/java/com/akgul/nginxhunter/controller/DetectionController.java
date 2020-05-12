package com.akgul.nginxhunter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DetectionController {
    private Logger LOGGER = LoggerFactory.getLogger(DetectionController.class);

    private static final String SERVER_HEADER_NAME = "Server";

    public String getServerType(String domainName) throws IOException {
        if (StringUtils.isEmpty(domainName)) {
            LOGGER.error("Cannot get server type. Domain name is null or empty.");
            return "";
        }

        LOGGER.info("Connection started for " + domainName);

        HttpURLConnection connection = openConnectionWithUrl(domainName);

        String serverType = connection.getHeaderField(SERVER_HEADER_NAME);

        LOGGER.info("Connection ended for " + domainName + " server type is " + serverType);

        return serverType;
    }

    protected HttpURLConnection openConnectionWithUrl(String domainName) throws IOException {
        URL url = new URL(domainName);
        return (HttpURLConnection) url.openConnection();
    }

    public List<String> getAllIPAddresses(String domainName) throws MalformedURLException, UnknownHostException {
        if (StringUtils.isEmpty(domainName)) {
            LOGGER.error("Getting IP address is failed. Domain name is null or empty.");
            return new ArrayList<>();
        }

        List<String> ipAddresses = new ArrayList<>();

        URL url = new URL(domainName);
        InetAddress[] addresses = InetAddress.getAllByName(url.getHost());

        for (InetAddress inetAddress : addresses) {
            ipAddresses.add(inetAddress.getHostAddress());
        }

        return ipAddresses;
    }
}

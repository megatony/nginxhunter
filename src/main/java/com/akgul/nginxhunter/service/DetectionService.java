package com.akgul.nginxhunter.service;

import com.akgul.nginxhunter.controller.DetectionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Locale;

@Service
public class DetectionService {

    @Autowired
    private DetectionController serverTypeDetectionController;

    public boolean isServerTypeMatching(String domainName, String checkingServerType) throws IOException {
        if (StringUtils.isEmpty(domainName)) {
            return false;
        }

        String serverType = serverTypeDetectionController.getServerType(domainName);

        if (StringUtils.isEmpty(serverType)) {
            return false;
        }

        return serverType.toLowerCase(Locale.ENGLISH).contains(checkingServerType);
    }

    public List<String> getNginxApiAddresses(String domainName) throws MalformedURLException, UnknownHostException {
        return serverTypeDetectionController.getAllIPAddresses(domainName);
    }
}

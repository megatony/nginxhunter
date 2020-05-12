package com.akgul.nginxhunter.api.controller;

import com.akgul.nginxhunter.service.DetectionService;
import com.akgul.nginxhunter.utils.DetectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/detectionapi")
public class DetectionApiController {
    private Logger LOGGER = LoggerFactory.getLogger(DetectionApiController.class);

    @Autowired
    private DetectionService detectionService;

    @RequestMapping(value = "/detectdomains/{checkingServerType}", method = RequestMethod.POST)
    public Map<String, List<String>> detectDomains(@RequestBody String domainNames, @PathVariable String checkingServerType) throws IOException {
        HashMap<String, List<String>> result = new HashMap<>();

        if (StringUtils.isEmpty(domainNames)) {
            String errorMessage = "Domain names are empty.";
            result.put(errorMessage, null);
            LOGGER.error(errorMessage);
            return result;
        }

        JsonParser jsonParser = JsonParserFactory.getJsonParser();

        for (Object domainNameObject : jsonParser.parseList(domainNames)) {
            String domainName = DetectionUtils.getNormalizedUrl(domainNameObject.toString());
            LOGGER.info("Domain name " + domainName + " is controlling.");

            if (detectionService.isServerTypeMatching(domainName, checkingServerType.toLowerCase(Locale.ENGLISH))) {
                LOGGER.info("Domain name " + domainName + " has a " + checkingServerType + " server.");
                result.put(domainNameObject.toString(), detectionService.getNginxApiAddresses(domainName));
            }
        }
        return result;
    }

}

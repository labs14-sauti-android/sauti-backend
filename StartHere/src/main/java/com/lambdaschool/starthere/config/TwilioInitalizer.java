package com.lambdaschool.starthere.config;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitalizer {

    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitalizer.class);


    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioInitalizer(TwilioConfig twilioConfig) {

        this.twilioConfig = twilioConfig;
        Twilio.init(
                twilioConfig.getAccountSid(),
                twilioConfig.getAuthToken()
        );
        LOGGER.info("Twilio initialized ... with account sid {}");
    }
}

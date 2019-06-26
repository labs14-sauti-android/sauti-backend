package com.lambdaschool.starthere.controllers;


import com.lambdaschool.starthere.models.SmsRequest;
import com.lambdaschool.starthere.services.TwilioSmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/sms")
public class TwilioController {

        @Autowired
        private final TwilioSmsSender twilioSmsSender;

        @Autowired
        public TwilioController(TwilioSmsSender twilioSmsSender) {
            this.twilioSmsSender = twilioSmsSender;
        }

        @PostMapping

        public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
            twilioSmsSender.sendSms(smsRequest);
        }
}


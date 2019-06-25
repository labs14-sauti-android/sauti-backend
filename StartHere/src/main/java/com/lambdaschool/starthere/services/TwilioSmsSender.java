package com.lambdaschool.starthere.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsSender {

    private final SmsSender SmsSender;

    @Autowired
    public TwilioSmsSender(@Qualifier("twilio")TwilioSmsSender twilioSmsSender) {
        this.SmsSender = SmsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        SmsSender.sendSms((smsRequest));
    }
}

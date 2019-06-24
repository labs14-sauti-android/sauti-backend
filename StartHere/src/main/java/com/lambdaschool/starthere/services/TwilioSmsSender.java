package com.lambdaschool.starthere.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsSender {

    private final TwilioSmsSender twilioSmsSender;

    @Autowired
    public TwilioSmsSender(TwilioSmsSender twilioSmsSender) {
        this.twilioSmsSender = twilioSmsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        twilioSmsSender.sendSms((smsRequest));
    }
}

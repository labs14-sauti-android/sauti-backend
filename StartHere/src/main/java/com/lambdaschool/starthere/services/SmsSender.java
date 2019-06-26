package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.SmsRequest;

public interface SmsSender {

    public void sendSms(SmsRequest smsRequest);
}

package com.lambdaschool.starthere.services;


import com.lambdaschool.starthere.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SmsSenderImpl implements SmsSender {

    private final TwilioConfig twilioConfig;

    @Autowired
    public SmsSenderImpl(TwilioConfig twilioConfig)
    {
        this.twilioConfig = twilioConfig;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber("");
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();

        }else{
            throw new IllegalArgumentException(
                    "Phone number [" + smsRequest.getPhoneNumber() + "] is not a valid number.");
        }


    }





    private boolean isPhoneNumberValid(String phoneNumber) {
        return true;
    }
}



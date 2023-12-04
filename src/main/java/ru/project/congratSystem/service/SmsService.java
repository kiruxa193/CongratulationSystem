package ru.project.congratSystem.service;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String fromPhoneNumber;

    public void sendSms(String toPhoneNumber, String message) {
        Twilio.init(accountSid, authToken);
        Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(fromPhoneNumber),
                message
        ).create();
    }
}


//public class Example {
//    // Find your Account Sid and Token at twilio.com/console
//    public static final String ACCOUNT_SID = "ACcd62ef787b2efb672dec6aafdf6f48d7";
//    public static final String AUTH_TOKEN = "5eb1b4d8bf26a23cdc8f99b9072b5d42";
//
//    public static void main(String[] args) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(
//                        new com.twilio.type.PhoneNumber("+79385199591"),
//                        new com.twilio.type.PhoneNumber("+14846794367"),
//                        "Your message")
//                .create();
//
//        System.out.println(message.getSid());
//    }
//}

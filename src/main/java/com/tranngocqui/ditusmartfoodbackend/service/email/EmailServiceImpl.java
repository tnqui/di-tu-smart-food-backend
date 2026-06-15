package com.tranngocqui.ditusmartfoodbackend.service.email;

import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
public class EmailServiceImpl implements EmailService {
    private final SesClient sesClient;
    private final boolean isActive;
    private final String sender;

    public EmailServiceImpl(SesClient sesClient, @Value("${mail.sender}") String sender, @Value("${aws.active}") boolean isActive) {
        this.sesClient = sesClient;
        this.sender = sender;
        this.isActive = isActive;
    }

    @Override
    @Async
    public void sendEmail(String to, String subject, String body) {
        if (isActive) {
            SendEmailRequest request = SendEmailRequest.builder()
                    .destination(Destination.builder()
                            .toAddresses(to)
                            .build())
                    .message(Message.builder()
                            .subject(Content.builder()
                                    .charset("UTF-8")
                                    .data(subject)
                                    .build())
                            .body(Body.builder()
                                    .html(Content.builder()
                                            .data(body)
                                            .charset("UTF-8")
                                            .build())
                                    .build())
                            .build())
                    .source(sender)
                    .build();
            sesClient.sendEmail(request);
        } else
            throw new AppException(ErrorCode.EMAIL_SERVICE_DISABLED);

    }


}

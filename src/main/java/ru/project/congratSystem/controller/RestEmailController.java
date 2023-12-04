package ru.project.congratSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.project.congratSystem.service.EmailSendService;
import ru.project.congratSystem.service.ScheduledSendEmailService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/emails")
public class RestEmailController {

    private final EmailSendService emailSendService;
    private final ScheduledSendEmailService scheduledSendEmailService;

    @Autowired
    public RestEmailController(EmailSendService emailSendService, ScheduledSendEmailService
            scheduledSendEmailService) {
        this.emailSendService = emailSendService;
        this.scheduledSendEmailService = scheduledSendEmailService;
    }


    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendEmail(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("message") String text,
            @RequestParam("date") String date
    ) {
        if (date == null) {


            try {
                emailSendService.sendMessage(to, subject, text);
                System.out.println("Письмо ушло");
                Map<String, String> response = new HashMap<>();
                response.put("message", "Письмо с поздравлением успешно отправлено");

                return ResponseEntity.ok(response);

            } catch (Exception e) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Не удалось отправить поздравление.");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

            }
        } else {
            try {
                scheduledSendEmailService.sendScheduledMessage(to, subject, text, Long.valueOf(date));
                System.out.println("Письмо ушло");
                Map<String, String> response = new HashMap<>();
                response.put("message", "Письмо с поздравлением успешно отправлено");

                return ResponseEntity.ok(response);

            } catch (Exception e) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Не удалось отправить поздравление.");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
    }
}

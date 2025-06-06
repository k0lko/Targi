package org.targi.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.targi.model.RegistrationForm;
import org.targi.service.PdfService;
import org.targi.service.RegistrationFormService;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class RegistrationFormController {
    private final RegistrationFormService formService;
    private final PdfService pdfService;

    @PostMapping
    public ResponseEntity<?> submitForm(@RequestBody RegistrationForm form) {
        RegistrationForm saved = formService.save(form);
        try {
            pdfService.generateAndSendPdf(saved);
        } catch (MessagingException e) {
            return ResponseEntity.internalServerError().body("PDF/email error: " + e.getMessage());
        }
        return ResponseEntity.ok("Zgłoszenie przyjęte");
    }
}
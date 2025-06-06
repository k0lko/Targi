package org.targi.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.targi.model.RegistrationForm;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Service
@RequiredArgsConstructor
public class PdfService {
    private final JavaMailSender mailSender;

    public void generateAndSendPdf(RegistrationForm form) throws MessagingException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Formularz zgłoszeniowy na targi Your Future 2025"));
            document.add(new Paragraph("Firma: " + form.getCompanyName()));
            document.add(new Paragraph("Branża: " + form.getIndustry()));
            document.add(new Paragraph("Strona WWW: " + form.getWebsite()));
            document.add(new Paragraph("Rodzaj stoiska: " + form.getBoothType()));
            document.add(new Paragraph("Krzesło: " + (form.isChair() ? "Tak" : "Nie")));
            document.add(new Paragraph("Panel: " + (form.isPanel() ? "Tak" : "Nie")));
            document.add(new Paragraph("Catering: " + form.getCateringCount()));
            document.add(new Paragraph("Kontakt: " + form.getContactName() + ", " + form.getEmail()));
            document.add(new Paragraph("Faktura: " + form.getInvoiceCompany() + ", NIP: " + form.getInvoiceNIP()));
            document.add(new Paragraph("Zgody: " + (form.isDataConsent() && form.isConditionsConsent() && form.isParticipationConsent() ? "Tak" : "Nie")));
            document.close();
        } catch (Exception e) {
            throw new MessagingException("Błąd generowania PDF", e);
        }

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(form.getEmail());
        helper.setSubject("Potwierdzenie zgłoszenia na targi Your Future 2025");
        helper.setText("W załączniku znajduje się potwierdzenie zgłoszenia.");

        InputStreamSource attachment = new ByteArrayResource(out.toByteArray());
        helper.addAttachment("Zgloszenie.pdf", attachment);

        mailSender.send(message);
    }
}

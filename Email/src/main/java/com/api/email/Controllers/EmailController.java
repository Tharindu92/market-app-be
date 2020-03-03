package com.api.email.Controllers;

import com.api.email.Models.Email;
import com.api.email.Services.EmailService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.cert.CertificateException;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/send")
    public String createPayment(@RequestBody Email email) throws DocumentException, MessagingException, IOException, CertificateException {
        System.out.println(email);
        if(email.isAttached())
            emailService.sendEmailWithAttachment(email);
        else
            emailService.sendEmail(email);
        return "Success";
    }
}

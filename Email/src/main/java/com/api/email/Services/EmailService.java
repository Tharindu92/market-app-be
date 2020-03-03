package com.api.email.Services;

import com.api.email.Models.Email;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.security.Security;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.pdf.src}")
    private String source;

    @Value("${email.pdf.dst}")
    private String destination;

    @Value("${email.pef.cert}")
    private String certificate;

    public void sendEmail(Email email) {

        SimpleMailMessage msg = new SimpleMailMessage();
        System.out.println(email.getSendTo());
        msg.setTo(email.getSendTo());
        msg.setSubject(email.getSubject());
        msg.setText(email.getText());

        javaMailSender.send(msg);

    }

    public void sendEmailWithAttachment(Email email) throws MessagingException, IOException, DocumentException, CertificateException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(email.getSendTo());

        helper.setSubject(email.getSubject());

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText(email.getText(), true);

        // hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));
        String filename = source;
        System.out.println(filename);
        createPdf(email.getText(), filename);

        helper.addAttachment(filename,new File(destination));

        javaMailSender.send(msg);
        System.gc();

    }

    private void createPdf(String text, String filename) throws IOException, DocumentException, CertificateException {
        Document document = new Document();
        FileOutputStream fileOriginal = new FileOutputStream(filename);
        PdfWriter.getInstance(document, fileOriginal);
        Certificate[] certlist = null;
        int[] permissions = null;
//        certlist[0] = certificate;
//        permissions[0] = 0;
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk(text, font);

        document.add(chunk);
        document.close();
        fileOriginal.close();
        fileOriginal = new FileOutputStream(destination);
        PdfReader pdfReader = new PdfReader(filename);
        PdfStamper pdfStamper
                = new PdfStamper(pdfReader, fileOriginal);
        File cert = new File(certificate);
//        FileInputStream inputStream = new FileInputStream(cert);
//        int content;
//        while ((content = inputStream.read()) != -1) {
//            // convert to char and display it
//            System.out.print((char) content);
//        }
        certlist = tryParsePKIPathChain(cert);
//        pdfStamper.setEncryption(
//                "userpass".getBytes(),
//                "ownerpass".getBytes(),
//                PdfWriter.ALLOW_PRINTING,
//                PdfWriter.ENCRYPTION_AES_256
//        );
        permissions = new int[certlist.length];
        for(int i = 0; i < certlist.length; i++){
            permissions[i] = 0;
        }
        Security.addProvider(new BouncyCastleProvider());
        pdfStamper.setEncryption(certlist,permissions , PdfWriter.ENCRYPTION_AES_256);
//        fileOriginal.close();
        pdfStamper.close();
        return ;
    }

    private Certificate[] tryParsePKIPathChain(File chainFile)
            throws IOException, FileNotFoundException, CertificateException {

        Certificate[] internalCertificateChain = null;
        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        FileInputStream inputStream = new FileInputStream(chainFile);
        int content;
        StringBuilder sb = new StringBuilder();
        while ((content = inputStream.read()) != -1) {
            // convert to char and display it
            sb.append((char) content);
        }

//        CertPath certPath = cf.generateCertPath(new ByteArrayInputStream(sb.toString().getBytes()));

//        List<Certificate> certList = (List<Certificate>) certPath.getCertificates();
        List<Certificate> certList = new ArrayList<>();
        certList.add(cf.generateCertificate(new ByteArrayInputStream(sb.toString().getBytes())));
        internalCertificateChain = certList.toArray(new Certificate[]{});


        return internalCertificateChain;
    }
}

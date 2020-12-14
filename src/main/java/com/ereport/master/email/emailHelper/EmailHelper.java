package com.ereport.master.email.emailHelper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;


@Component
public class EmailHelper
{
    public void sendEmail(String receiver, MultipartFile file)
    {
        try
        {
//            SMTP настройки
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
//          Адрес почты и пароль отправителя
            Session session = Session.getInstance(props, new javax.mail.Authenticator()
            {
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication("stroymart2020@gmail.com", "Stroymart2432");
                }
            });
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("stroymart2020@gmail.com", false));

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiver));
            msg.setSubject("StroyMart.kz");
            msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("StroyMart.kz demo pdf",
                    "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);


            File convFile = new File(file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();


            MimeBodyPart attachPart = new MimeBodyPart();
            attachPart.attachFile(convFile);
            multipart.addBodyPart(attachPart);
            msg.setContent(multipart);
            Transport.send(msg);
        }
        catch (Exception exe)
        {
            exe.printStackTrace();
        }
    }
}

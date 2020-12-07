package com.ereport.master.email.emailController;



import com.ereport.master.email.emailHelper.EmailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EmailController
{

    @Autowired
    private EmailHelper emailHelper;

    @PostMapping(value = "/sendemail")
    public String sendEmail(
            @RequestParam("receiver") String receiver,
            @RequestParam("file") MultipartFile file
    )
    {
        emailHelper.sendEmail(receiver,file);
        return "Email sent successfully";
    }



}


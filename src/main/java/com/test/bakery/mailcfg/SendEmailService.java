package com.test.bakery.mailcfg;

import com.test.bakery.exceptions.ResourceNotFoundException;
import com.test.bakery.model.Status;
import com.test.bakery.model.VerificationToken;
import com.test.bakery.repository.UserrRepository;
import com.test.bakery.repository.VerificationTokenRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final UserrRepository userrRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    public SendEmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine, UserrRepository userrRepository, VerificationTokenRepository verificationTokenRepository) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.userrRepository = userrRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public void sendEmail(String to, String topic) throws MessagingException {
        String token = createToken(to);
        Context context = new Context();
        context.setVariable("token", "http://localhost:3000/registration-complete?token=" + token);
        String process = templateEngine.process("tst", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        helper.setTo(to);
        helper.setText(process, true);
        helper.setSubject(topic);
        helper.setFrom("krasnenkow56@gmail.com");
        javaMailSender.send(mimeMessage);
    }
    public String createToken(String email)
    {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(userrRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User with email " + email + " not found")));
        verificationTokenRepository.save(verificationToken);
        return verificationToken.getToken();
    }


    /* создается токен
    при регистрации юзера токен отправляется на почту в виде ссылки на страницу с подтверждением
    длительность токена 24 часа
    после истечения токена ????????????
    во фронте пишется чекайте почту и подтверждайте акк
    при переходе по ссылке enabled = true
    перекидывает на главную страницу и все збс
    */
}

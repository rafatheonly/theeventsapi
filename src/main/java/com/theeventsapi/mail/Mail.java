package com.theeventsapi.mail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.List;

@Component
public class Mail {

	 @Autowired
	 private JavaMailSender mail;
	 
	 public void notificaUsuarioCriado(String convidado) {
	        enviar(
	                "theevents@gmail.com",
	                Arrays.asList("michelcluz@gmail.com", convidado),
	                "The Events!",
	                "Olá, <br/><br/> " + "Você foi adicionado ao The Events!" +
	                        "Eh nóis!");
	    }
	 
	 public void enviar(String remetente, List<String> destinatarios, String assunto, String mensagem) {
	        MimeMessage message = mail.createMimeMessage();
	        try {
	            MimeMessageHelper helper = new MimeMessageHelper(message);
	            helper.setFrom(remetente);
	            helper.setTo(destinatarios.toArray(new String[destinatarios.size()]));
	            helper.setSubject(assunto);
	            helper.setText(mensagem, true);

	            mail.send(message);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            throw new RuntimeException("Não foi possível enviar e-mail");
	        }
	    }
}

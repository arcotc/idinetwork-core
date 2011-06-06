package uk.co.idinetwork.core.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EmailUtil {
	private static final Log log = LogFactory.getLog(EmailUtil.class);
	
	public static boolean send(String toEmail, String niceName, String subject, String body) {
		boolean result = false;
		
		log.debug("Submitting contact form");
		
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

	    try {
	    	log.debug("Preparing message");
	    	Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("arcotc@googlemail.com", "Your IDINetwork Website"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, niceName));
            msg.setSubject(subject);
            msg.setText(body);

	    	log.debug("Sending message");
            Transport.send(msg);
            
            result = true;
        } 
	    catch (UnsupportedEncodingException e) {
	    	log.error(String.format("Exception: %s", e.getMessage()), e);
	    }
	    catch (AddressException e) {
	    	log.error(String.format("Exception: %s", e.getMessage()), e);
        } 
	    catch (MessagingException e) {
	    	log.error(String.format("Exception: %s", e.getMessage()), e);
        }
	    
	    return result;
	}
}

package com.orthochiqui.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;

import com.orthochiqui.service.impl.ClienteServiceImpl;

/**
 * Mail's clients
 * @author Jose
 */
public class MailChiqui {
	
	@Autowired
	ClienteServiceImpl clienteService;
	
	private static final String REMETENTE = "orthochiquiemail@gmail.com";
	private static final String PWD = "PWD_HERE";
	private static final String HOST = "smtp.gmail.com";
	private static final String SSL_SOCKET = "javax.net.ssl.SSLSocketFactory";
	private static final String PORT = "587";
//	private static final String PORT = "465";
	
	public static boolean sendEmail(String destinatarios) {		
		Properties props = System.getProperties();
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.auth", "true");
		
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.socketFactory.port", PORT);
//		props.put("mail.smtp.socketFactory.class", SSL_SOCKET);
//		props.put("mail.smtp.ssl.trust", HOST);
		
		Session session = Session.getInstance(props, 
				new Authenticator() {
			protected PasswordAuthentication getPW() {
				return new PasswordAuthentication(REMETENTE, PWD);
			}
		});
		session.setDebug(true);
		try {
			Message m = new MimeMessage(session);
			MailChiqui mi = new MailChiqui();
			
			// email01@gmail.com, email02@gmail.com, ...
			Address[] a = InternetAddress
					.parse(destinatarios);
			m.setRecipients(Message.RecipientType.TO, a);
			m.setSubject("BACK UP - DENTISTAS [ ORTHO CHIQUI ]");
			m.setText("<strong>BACK UP SEMANAL DOS PRONTUARIOS. DENTISTAS [ ORTHO CHIQUI ]</strong>");
			m.setContent(mi.tratarAnexo());
			
			Transport.send(m); // :-D
		} catch(MessagingException e) {			
			throw new RuntimeException(e); // :..-(
		} catch (FileNotFoundException fe) {
			fe.printStackTrace(); // :..-(
		} catch (IOException ie) {
			ie.printStackTrace(); // :..-(
		} 
		return true;
	}

	private Multipart tratarAnexo() throws FileNotFoundException, IOException, MessagingException {
		Multipart mp = new MimeMultipart();
		BodyPart bp = new MimeBodyPart();
		IpirangaUtil iu = new IpirangaUtil();
		DataSource ds = new FileDataSource(iu.tratarArquivoCSV(clienteService.montarArquivo()));
		bp.setDataHandler(new DataHandler(ds));
		mp.addBodyPart(bp);
		return mp;
	}
	
}

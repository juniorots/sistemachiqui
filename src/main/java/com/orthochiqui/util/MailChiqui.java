package com.orthochiqui.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

/**
 * Mail's clients
 * @author Jose
 */
public class MailChiqui {
	private final String REMETENTE = "orthochiquiemail@gmail.com";
	private final String PWD = "novaSenha33443322";
	private final String HOST = "smtp.gmail.com";
	private final String PORT = "465";
	
	public boolean sendEmail(String destinatarios) {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtps.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", PORT);
		
		Session session = Session.getInstance(props, 
				new Authenticator() {
			protected PasswordAuthentication getPW() {
				return new PasswordAuthentication(REMETENTE, PWD);
			}
		});
		session.setDebug(true);
		try {
			Message m = new MimeMessage(session);
			Address[] a = InternetAddress
					.parse(destinatarios);
			m.setRecipients(Message.RecipientType.TO, a);
			m.setSubject("BACK UP - DENTISTAS [ ORTHO CHIQUI ]");
			m.setText("<strong>BACK UP SEMANAL DOS PRONTUARIOS. DENTISTAS [ ORTHO CHIQUI ]</strong>");
			m.setContent(tratarAnexo());
			
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
		List<String[]> list = new ArrayList<>();
		list.add(new String[] {"PRONTUARIO", "NOME"});
		list.add(new String[] {"A-01", "PACIENTE 01"});
		list.add(new String[] {"A-02", "PACIENTE 02"});
		DataSource ds = new FileDataSource(iu.tratarArquivoCSV(list));
		bp.setDataHandler(new DataHandler(ds));
		mp.addBodyPart(bp);
		return mp;
	}
}

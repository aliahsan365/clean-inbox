package eshob.test.webdriver.suite.operations.emails;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class DeleteInbox {


	private Properties setProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		return props;
	}

	public void deleteAllMsg() {
		Properties props = setProperties();
		try {
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("smtp.gmail.com", EmailAutentification.Email, EmailAutentification.Password);
			Folder inbox = store.getFolder("inbox");
			inbox.open(Folder.READ_WRITE);
			Message[] messages = inbox.getMessages();
			// delete all msg in folder inbox;
			System.out.println("deleting all msg...");
			for (int i = 0; i < messages.length; ++i) {
				Message message = messages[i];
				System.out.println("---------------------------------");
				System.out.println("Email Number " + (i + 1));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				message.setFlag(Flags.Flag.DELETED, true);
			}
			inbox.close(true);
			store.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}

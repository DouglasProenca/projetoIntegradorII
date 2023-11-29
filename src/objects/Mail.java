package objects;

import model.User;
import view.MainScreen;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
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
import javax.swing.JOptionPane;

public class Mail {

	private Properties props;

	private Properties getProps() {
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // isso fez funcionar
		props.put("mail.smtp.port", "465");
		return props;
	}

	private Session getSession() {
		Session s = Session.getDefaultInstance(getProps(), new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(User.getInstance().getMail(), User.getInstance().getMailPassword());// email
																														// e
																														// senha
																														// usuÃ¡rio
			}
		});
		return s;
	}

	private MimeMessage getMessage(String emailDestinatario, String assunto, Multipart mp) throws MessagingException {
		MimeMessage message = new MimeMessage(getSession());
		message.setFrom(new InternetAddress(User.getInstance().getMail()));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDestinatario));
		message.setSubject(assunto);
		message.setSentDate(new Date());
		message.setContent(mp);
		return message;
	}

	public boolean enviarGmail(String emailDestinatario, String assunto, String msg, String filename) {
		try {

			// cria a primeira parte da mensagem
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(msg);

			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);

			if (!filename.equals("")) {
				// cria a segunda parte da mensage
				MimeBodyPart mbp2 = new MimeBodyPart();

				// anexa o arquivo na mensagem
				FileDataSource fds = new FileDataSource(filename);
				mbp2.setDataHandler(new DataHandler(fds));
				mbp2.setFileName(fds.getName());
				mp.addBodyPart(mbp2);
			}

			// send message
			Transport.send(getMessage(emailDestinatario, assunto, mp));
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), e.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
			return (false);
		}

		return (true);
	}
}
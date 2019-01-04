package environment;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendAttachmentInEmail {
	public static void main(String[] args) {
		SendAttachmentInEmail email = new SendAttachmentInEmail();
		email.sendhtmlemail();
	}
   public boolean sendhtmlemail(){
      // Recipient's email ID needs to be mentioned.
      String to = "airteel@gmail.com";
      String cc="ganesh.baskaran@kadambatechnologies.com";
      // Sender's email ID needs to be mentioned
      String from = "test404mail@gmail.com";
      String attachment="D:/gopi/git/matextesting/Reports/matex.html";
      final String username = "test404mail@gmail.com";//change accordingly
      final String password = "guhanarun1";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "25");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));
         message.setRecipients(Message.RecipientType.CC,
                 InternetAddress.parse(cc));

         // Set Subject: header field
         message.setSubject("Matex Smoke Test Result");

         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("Find attachment for Test Result");

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "Report.html";
         DataSource source = new FileDataSource(attachment);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");
         
         return true;
  
      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
   public void sendPDFReportByGMail(String from, String pass, String to, String subject, String body) {
       Properties props = System.getProperties();
       String host = "smtp.gmail.com";
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.host", host);
       props.put("mail.smtp.user", from);
       props.put("mail.smtp.password", pass);
       props.put("mail.smtp.port", "587");
       props.put("mail.smtp.auth", "true");

       Session session = Session.getDefaultInstance(props);
       MimeMessage message = new MimeMessage(session);

       try {
       	//Set from address
           message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
          //Set subject
           message.setSubject(subject);
           message.setText(body);
         
           BodyPart objMessageBodyPart = new MimeBodyPart();
           
           objMessageBodyPart.setText("Please Find The Attached Report File!");
           
           Multipart multipart = new MimeMultipart();

           multipart.addBodyPart(objMessageBodyPart);

           objMessageBodyPart = new MimeBodyPart();

           //Set path to the pdf report file
           String filename = System.getProperty("user.dir")+"\\Report.pdf"; 
           //Create data source to attach the file in mail
           DataSource source = new FileDataSource(filename);
           
           objMessageBodyPart.setDataHandler(new DataHandler(source));

           objMessageBodyPart.setFileName(filename);

           multipart.addBodyPart(objMessageBodyPart);

           message.setContent(multipart);
           Transport transport = session.getTransport("smtp");
           transport.connect(host, from, pass);
           transport.sendMessage(message, message.getAllRecipients());
           transport.close();
       }
       catch (AddressException ae) {
           ae.printStackTrace();
       }
       catch (MessagingException me) {
           me.printStackTrace();
       }
   }
}
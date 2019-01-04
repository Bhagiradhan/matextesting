package environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class ReadEmail {

	public static void main(String[] args) throws Exception {

		
		String data =read();
		System.out.println(data.contains("Divy3124"));
		

	}

	public static String read() throws Exception {
		String datas = "";
		Properties props = new Properties();

		props.load(new FileInputStream(new File("./src\\test\\resources\\property\\smtp.properties")));

		Session session = Session.getDefaultInstance(props, null);

		Store store = session.getStore("imaps");
		//store.connect("smtp.gmail.com", "gopinath.n@kadambatechnologies.com", "KILLER@007");
		store.connect("smtp.gmail.com", "ganesh.baskaran@kadambatechnologies.com", "Kadamba@123");

		Folder inbox = store.getFolder("inbox");
		inbox.open(Folder.READ_ONLY);
		int messageCount = inbox.getMessageCount();

		System.out.println("Total Messages:- " + messageCount);

		Message[] messages = inbox.getMessages();
		System.out.println("------------------------------");

		System.out.println("Mail Subject:- " + messages[messages.length - 1].getSubject());
		DataHandler data = messages[messages.length - 1].getDataHandler();
		if (data.getContentType().equals("TEXT/HTML; charset=us-ascii")) {
			datas = data.getContent().toString();
		} else {
			// System.out.println("else");
			datas = "else";

		}

		inbox.close(true);
		store.close();
		return datas;

	}

}
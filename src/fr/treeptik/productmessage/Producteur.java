package fr.treeptik.productmessage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class Producteur {

	private static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws Exception {

		// objet qui permet les recheches jndi grace au fichier conf à la racine
		InitialContext context = new InitialContext();
		// Permet de recherches dans le jndi spécifié
		Queue queue = (Queue) context.lookup("queue/test");

		// Connection jms
		QueueConnectionFactory factory = (QueueConnectionFactory) context
				.lookup("ConnectionFactory");
		// par defaut ici pas securisé , pas de user et password
		QueueConnection connection = factory.createQueueConnection();
		QueueSession session = connection.createQueueSession(false,
				QueueSession.AUTO_ACKNOWLEDGE);
		
		// ici on a creer un sender avec session pour l'envoi	
		QueueSender sender = session.createSender(queue);
		
		

		

		// on envoi un message 
		File file = new File("listCommandes.xml");
		FileReader fileReader = new FileReader(file);

		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while (bufferedReader.ready()) {
			str = str.append(bufferedReader.readLine());
		}
		
		bufferedReader.close();
		
		TextMessage message = session.createTextMessage(str.toString());
		sender.send(message);

		session.close();
		connection.close();
		System.out.println(str);
	}

}

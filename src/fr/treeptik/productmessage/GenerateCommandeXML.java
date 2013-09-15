package fr.treeptik.productmessage;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class GenerateCommandeXML {

	// generer de xml a partir de java
	
	public static void main(String[] args) throws JAXBException, Exception {

		JAXBContext context = JAXBContext.newInstance("fr.treeptik.productmessage");
	
		 ListCommandes listCommandes = new ListCommandes();;


		Date date = new Date();
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		
		gregorianCalendar.setTime(date);

		DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

		XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory
				.newXMLGregorianCalendar(gregorianCalendar);
		
		Commande commande = new Commande();
		commande.setNumero(1);
		commande.setDescription("produit1");
		commande.setDateCom(xmlGregorianCalendar);
		
		listCommandes.getCommande().add(commande);
		
		Commande commande1 = new Commande();
		commande1.setNumero(1);
		commande1.setDescription("produit1");
		commande1.setDateCom(xmlGregorianCalendar);
   

		listCommandes.getCommande().add(commande1);
		
		// j'ecris un fichier
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(listCommandes, new File("listCommandes.xml"));
		
		
		
		
	}

}

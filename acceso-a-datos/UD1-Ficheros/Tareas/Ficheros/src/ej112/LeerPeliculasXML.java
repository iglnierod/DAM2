package ej112;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class LeerPeliculasXML {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            File archivoXML = new File("C:\\Users\\dam2_alu08\\IdeaProjects\\Ficheros\\src\\ej112\\peliculas.xml");
            Document doc = dBuilder.parse(archivoXML);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("pelicula");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String id = eElement.getAttribute("id");
                    System.out.println("\n" + nNode.getNodeName().toUpperCase() + " #" + id + ":");
                    System.out.println("Título: "+ eElement.getElementsByTagName("titulo").item(0).getTextContent());
                    System.out.println("Año: "+ eElement.getElementsByTagName("ano").item(0).getTextContent());
                    System.out.println("Precio: "+ eElement.getElementsByTagName("precio").item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}

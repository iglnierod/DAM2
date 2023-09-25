package ej113;

import org.w3c.dom.*;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class EscribirPeliculasXML {
    public static void main(String[] args) {
        File copiaPeliculas = new File("C:\\Users\\dam2_alu08\\IdeaProjects\\Ficheros\\src\\ej113\\copiaPeliculas.xml");
        try {
            copiaPeliculas.createNewFile();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "nombre", null);
            Element raiz = document.getDocumentElement();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

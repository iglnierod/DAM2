package ej113;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class EscribirPeliculasXML {
    public static void main(String[] args) {
        File sourceFile = new File("C:\\Users\\dam2_alu08\\IdeaProjects\\Ficheros\\src\\ej113\\peliculas.xml");

//        System.out.println(copiaPeliculas.exists());
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(sourceFile);

            //System.out.println(document);
            transform(document);

//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(document);
//            StreamResult result = new StreamResult(copiaPeliculas);
//            transformer.transform(source,result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void transform(Document document) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();

            DOMSource source = new DOMSource(document);
            File destinationFile = new File("C:\\Users\\dam2_alu08\\IdeaProjects\\Ficheros\\src\\ej113\\copiaPeliculas.xml");
            StreamResult result = new StreamResult(destinationFile);

            transformer.transform(source, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}

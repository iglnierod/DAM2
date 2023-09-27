package ej114;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.SQLOutput;
import java.util.HashMap;

public class ManejoUsuarios {
    private HashMap<String, String> users = new HashMap<>();

    public ManejoUsuarios() {
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            File archivoXML = new File("C:\\Users\\dam2_alu08\\IdeaProjects\\Ficheros\\src\\ej114\\usuarios.xml");
            Document doc = dBuilder.parse(archivoXML);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("usuario");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String key = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String value = eElement.getElementsByTagName("contraseña").item(0).getTextContent();
                    this.users.put(key, value);
                }
            }
        } catch (Exception e) {
            System.err.println("No se ha cargado correctamente la información de los usuarios");
        }
    }

    public boolean comprobarUsuario(String usuario, String pwd) {
        // Comprobar nombre de usuario
        if (!users.containsKey(usuario) || !pwd.equals(users.get(usuario))) {
            System.out.println("ERROR: El usuario o la contraseña no es correcto.");
            return false;
        }
/*
        // Comprobar contraseña del usuario
        if (!pwd.equals(users.get(usuario))) {
            System.out.println("ERROR: La contraseña no es correcta.");
            return false;
        }*/

        System.out.println("Sesión iniciada como: " + usuario);
        return true;
    }
}

package ej208;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ej208.playlist.Playlist;
import ej208.playlist.Playlists;
import ej208.song.Song;
import ej208.song.Songs;
import ej208.user.User;
import ej208.user.Users;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class FileHandler {
    public static void loadData(File jsonFile, Songs songs, Users users) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(jsonFile)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            JsonArray songsArray = jsonObject.getAsJsonArray("canciones");
            for (int i = 0; i < songsArray.size(); i++) {
                JsonObject song = songsArray.get(i).getAsJsonObject();

                String title = song.get("titulo").getAsString();
                String artist = song.get("artista").getAsString();
                int length = song.get("duracion").getAsInt();
                int year = song.get("anio").getAsInt();

                Song newSong = new Song(title, artist, length, year);
                songs.add(newSong);
            }

            JsonArray usersArray = jsonObject.getAsJsonArray("usuarios");
            for (int i = 0; i < usersArray.size(); i++) {
                JsonObject user = usersArray.get(i).getAsJsonObject();

                String username = user.get("nombre_usuario").getAsString();
                String name = user.get("nombre").getAsString();
                String email = user.get("correo").getAsString();

                User newUser = new User(username, name, email);
                users.add(newUser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportDataToXML(Songs songs, Users users, Playlists playlists) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document doc = implementation.createDocument(null, "root", null);

            Element root = doc.getDocumentElement();

            Element songsElement = doc.createElement("canciones");
            root.appendChild(songsElement);
            for (Song s : songs.getSongsCollection()) {
                songsElement.appendChild(getSongElement(doc, s));
            }

            Element usersElement = doc.createElement("usuarios");
            root.appendChild(usersElement);
            for (User u : users.getUsersCollection()) {
                usersElement.appendChild(getUserElement(doc, u));
            }

            Element playlistsElement = doc.createElement("playlists");
            root.appendChild(playlistsElement);
            for (Playlist p : playlists.getPlaylistsCollection()) {
                playlistsElement.appendChild(getPlaylistElement(doc, p));
            }

            File outputFile = new File(App.DIR_SQL, "data.xml");
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(outputFile);
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private static Element getSongElement(Document doc, Song song) {
        Element songElement = doc.createElement("cancion");
        songElement.setAttribute("id", String.valueOf(song.getId()));
        songElement.setAttribute("titulo", song.getTitle());
        songElement.setAttribute("artista", song.getArtist());
        songElement.setAttribute("duracion", String.valueOf(song.getLength()));
        songElement.setAttribute("anio", String.valueOf(song.getYear()));
        return songElement;
    }

    private static Element getUserElement(Document doc, User user) {
        Element userElement = doc.createElement("usuario");
        userElement.setAttribute("id", String.valueOf(user.getId()));
        userElement.setAttribute("nombre-de-usuario", user.getUsername());
        userElement.setAttribute("nombre", user.getUser());
        userElement.setAttribute("email", user.getEmail());
        return userElement;
    }

    private static Element getPlaylistElement(Document doc, Playlist playlist) {
        Element playlsitElement = doc.createElement("playlist");
        playlsitElement.setAttribute("id", String.valueOf(playlist.getId()));
        playlsitElement.setAttribute("nombre", playlist.getName());
        playlsitElement.setAttribute("usuario", String.valueOf(playlist.getUser()));
        playlsitElement.setAttribute("canciones", String.valueOf(playlist.getSongs()));
        return playlsitElement;
    }
}

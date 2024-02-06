package ej208;

import com.google.gson.*;

import java.io.*;

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
                System.out.println(newSong);
                songs.add(newSong);
            }

            JsonArray usersArray = jsonObject.getAsJsonArray("usuarios");
            for (int i = 0; i < usersArray.size(); i++) {
                JsonObject user = usersArray.get(i).getAsJsonObject();

                String username = user.get("nombre_usuario").getAsString();
                String name = user.get("nombre").getAsString();
                String email = user.get("correo").getAsString();

                User newUser = new User(username, name, email);
                System.out.println(newUser);
                users.add(newUser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

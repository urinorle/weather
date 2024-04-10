package pruebaapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class OpenMeteoClient {
    public static void cliente (double lon, double lat)  {
        try {
            // Coordenadas de la ubicación que deseas consultar
            double latitude = lat;
            double longitude = lon;

            // URL de la API de OpenMeteo
            URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&current_weather=true");

            // Abrir conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Leer la respuesta
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Convertir la respuesta JSON en un objeto JsonObject usando Gson
            Gson gson = new Gson();
            JsonObject weatherData = gson.fromJson(response.toString(), JsonObject.class);

            // Obtener y mostrar los datos meteorológicos
            System.out.println("Weather Data:");
            System.out.println("Timezone: " + weatherData.get("timezone"));
            System.out.println("Temperature: " + weatherData.getAsJsonObject("current_weather").get("temperature") + " °C");
            System.out.println("Wind Speed: " + weatherData.getAsJsonObject("current_weather").get("windspeed") + " km/h");
            System.out.println("Wind Direction: " + weatherData.getAsJsonObject("current_weather").get("winddirection") + " degrees");

            // Cerrar la conexión
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
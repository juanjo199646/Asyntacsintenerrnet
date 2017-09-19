package com.i053113.asyntacsintenerrnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by JUANJO FAJARDO on 19/09/2017.
 */


// PAGINA PARA USAR POST - USUARIO . FOTOS https://jsonplaceholder.typicode.com/posts
public class HttpManager {

    // crear una clase llamado getData y recibe como parametro un String
    public static String getData(String  uri) throws IOException {
        BufferedReader reader =null;
        //Clase url de java para darle formato a las rutas
        URL url = new  URL(uri);
        // Clase que me permite  hacer la conexion a internet
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // declaramos variables para abrir un Bufer de recoleccion de datos
        StringBuilder stringBuilder = new StringBuilder();
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        //variable de tipo line
        String line;

        while ((line = reader.readLine()) != null ){
            stringBuilder.append(line + "\n");
        }

        return "";
    }
}

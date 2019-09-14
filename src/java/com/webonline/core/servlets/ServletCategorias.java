package com.webonline.core.servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.webonline.core.model.Categoria;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;


public class ServletCategorias extends HttpServlet {

    public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
        RequestDispatcher despachador = null;
        HttpSession sesion = peticion.getSession();
      //  AccessToken token = (AccessToken) sesion.getAttribute("token");
        URL url = new URL("http://ec2-18-222-247-158.us-east-2.compute.amazonaws.com:9200/api/v1/categorias");
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Accept", "application/json");

      //  conexion.setRequestProperty("Authorization", "Bearer ".concat(token.getAccessToken()));

        if (conexion.getResponseCode() != 200) {
            throw new RuntimeException("Failed: HTTP 1.1 Error code ".concat(String.valueOf(conexion.getResponseCode())));
        } else {
            InputStreamReader in = new InputStreamReader(conexion.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String salida;
            Gson gson = new Gson();
            Type tipoCategoria = new TypeToken<List<Categoria>>() {
            }.getType();
            List<Categoria> listaCategoria = null;

            while ((salida = br.readLine()) != null) {
                listaCategoria = gson.fromJson(salida, tipoCategoria);

            }
            conexion.disconnect();
            peticion.setAttribute("categorias", listaCategoria);
            despachador = peticion.getRequestDispatcher("categorias.jsp");

        }
        despachador.forward(peticion, respuesta);
    }

    public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
        doPost(peticion, respuesta);
    }

}

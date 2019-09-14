package com.webonline.core.servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.webonline.core.model.TipoEmpaque;
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

public class ServletTiposEmpaque extends HttpServlet {
    
    public void doPost(HttpServletRequest peticion,HttpServletResponse respuesta) throws ServletException,IOException{
        RequestDispatcher despachador = null;
         URL url = new URL("http://localhost:9200/api/v1/tiposempaque");
         HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
         conexion.setRequestMethod("GET");
         conexion.setRequestProperty("Accept", "application/json");

         if(conexion.getResponseCode() != 200){
             throw new RuntimeException("Failed: HTTP 1.1 Error code ".concat(String.valueOf(conexion.getResponseCode())));
         }else{
             InputStreamReader in = new InputStreamReader(conexion.getInputStream());
             BufferedReader br = new BufferedReader(in);
             String salida;
             Gson gson = new  Gson();
             Type typeTipoEmpaque = new TypeToken<List<TipoEmpaque>>(){}.getType();
             List<TipoEmpaque> listaTiposEmpaque = null;
             
             
             while( (salida = br.readLine()) != null){
                 listaTiposEmpaque = gson.fromJson(salida, typeTipoEmpaque);
                
                 
             }
             conexion.disconnect();
             peticion.setAttribute("tiposEmpaque", listaTiposEmpaque);
             despachador = peticion.getRequestDispatcher("tiposEmpaque.jsp");
             
         }
         despachador.forward(peticion, respuesta);
    }
    
    public void doGet(HttpServletRequest peticion,HttpServletResponse respuesta) throws ServletException,IOException{
        doPost(peticion,respuesta);
    }
    
}

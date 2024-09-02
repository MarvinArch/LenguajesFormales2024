/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.analizador.practica1lenguaje2024;

import GeneradorTokens.ClasificarTokens;
import com.analizador.practica1lenguaje2024.tokens.TokenModel;
import java.util.ArrayList;
import GeneradorTokens.SeparaEspacios;
import Visual.VentanaPrincipal;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.lang.model.util.Elements;
import otros.SeparaLineas;

/**
 *
 * @author David
 */
public class Practica1Lenguaje2024 {

    public static void main(String[] args) {
        /*SeparaLineas arregloLineas= new SeparaLineas();
        
        
        ArrayList<String> recepcion =arregloLineas.separaLineas(prueba);
        SeparaEspacios arregloPalabras= new SeparaEspacios(recepcion);
        ClasificarTokens tookenFinal= new ClasificarTokens();
        ArrayList<TokenModel> recepcion2=arregloPalabras.BuscarEspacios();
        ArrayList<TokenModel> recepcion3=tookenFinal.clasificacionTokens(recepcion2);
        System.out.println("en el arreglo existen "+ recepcion3.size());
        for (int i = 0; i < recepcion3.size(); i++) {
            
            System.out.println("palabra "+ i + recepcion3.get(i).toString());
        }*/
        VentanaPrincipal lanzador = new VentanaPrincipal();
        lanzador.setVisible(true);
        lanzador.addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {
                // Obtener las nuevas dimensiones de la ventana
                int width = lanzador.getWidth();
                int height = lanzador.getHeight();
                
                lanzador.modificarTamaños(width, height);
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                // Puedes manejar el movimiento aquí si lo deseas
            }

            @Override
            public void componentShown(ComponentEvent e) {
                // Manejar cuando la ventana es mostrada, si es necesario
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                // Manejar cuando la ventana es ocultada, si es necesario
            }
        });
    }
}

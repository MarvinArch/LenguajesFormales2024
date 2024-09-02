/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otros;

import java.util.ArrayList;

/**
 *
 * @author David
 */
public class SeparaLineas {
    public ArrayList<String> separaLineas(String texto){
        ArrayList<String> lineasarreglo = new ArrayList<>();
        char[] buscaLinea= texto.toCharArray();
        String linea="";
        for (int i = 0; i < buscaLinea.length; i++) {
            if (buscaLinea[i]==10 ) {
                lineasarreglo.add(linea);
                linea="";
            }else{
                linea+=buscaLinea[i];
            }
        }
        lineasarreglo.add(linea);
        return lineasarreglo;
    }
}

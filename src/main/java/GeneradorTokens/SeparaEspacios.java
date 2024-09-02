/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GeneradorTokens;

import com.analizador.practica1lenguaje2024.tokens.TokenModel;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class SeparaEspacios {
    
    private ArrayList<String> arregloLineas;

    public SeparaEspacios(ArrayList<String> arregloLineas) {
        this.arregloLineas = arregloLineas;
    }
    
    public ArrayList<TokenModel> BuscarEspacios() {
        ArrayList<TokenModel> separado = new ArrayList<>();
        for (int i = 0; i < arregloLineas.size(); i++) {
            String palabra = "";
            char[] linea = arregloLineas.get(i).toCharArray();
            int columna = 1;
            for (int j = 0; j < linea.length; j++) {
                //Comentario
                if (linea[j] == 39) {
                    separado.add(new TokenModel(columna, i + 1, "comentario", "#B3B3B3", arregloLineas.get(i)));
                    j = linea.length;
                    //cadena
                } else if (linea[j] == 34) {
                    separado.add(new TokenModel(columna, i + 1, "", "", palabra));
                    columna = j + 1;
                    palabra = "";
                    String cadena = "" + linea[j];
                    int agrega = 0;
                    for (int k = j + 1; k < linea.length; k++) {
                        agrega++;
                        cadena += linea[k];
                        if (linea[k] == 34) {
                            separado.add(new TokenModel(columna, i + 1, "Cadena", "#E51400", cadena));
                            palabra = "";
                            j += agrega;
                            agrega = 0;
                            columna = j + 2;
                            k = linea.length;
                        }
                    }
                    //busca espacio
                } else {
                    palabra += linea[j];
                    if (linea[j] == 32 || j == linea.length-1 ) {
                        separado.add(new TokenModel(columna, i + 1, "", "", palabra));
                        columna = j + 2;
                        palabra = "";
                    }
                    
                }
            }
            separado.add(new TokenModel(columna, i + 1, "", "", palabra));
            palabra = "";

        }
        separado = elimiarEspacios(separado);
        return separado;
    }

    private ArrayList<TokenModel> elimiarEspacios(ArrayList<TokenModel> quitarEspacios){
        ArrayList<TokenModel> eliminado= new ArrayList<>();
        int espacios=0;
        for (int i = 0; i < quitarEspacios.size(); i++) {
            if (quitarEspacios.get(i).getLexema().equalsIgnoreCase(" ")) {
                espacios++;
            }
            if (espacios==4) {
                quitarEspacios.get(i).setTipo("tabulacion");
                espacios=0;
            }
        }
        
        for (int i = 0; i < quitarEspacios.size(); i++) {
            if (quitarEspacios.get(i).getLexema().equalsIgnoreCase(" ") && 
                    !quitarEspacios.get(i).getTipo().equals("tabulacion")) {
                //quitarEspacios.remove(i);
            }else{
                eliminado.add(quitarEspacios.get(i));
            }
        }
        return eliminado;
    }
    
}

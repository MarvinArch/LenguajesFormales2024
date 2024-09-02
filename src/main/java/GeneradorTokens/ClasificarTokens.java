/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GeneradorTokens;

import com.analizador.practica1lenguaje2024.tokens.TokenModel;
import java.util.ArrayList;
import otros.Operadores;
import otros.Reservados;

/**
 *
 * @author David
 */
public class ClasificarTokens {
    
    public ArrayList<TokenModel> clasificacionTokens(ArrayList<TokenModel> listaInicial) {
        /*
        Estado 1 Identificadores
        Estado 2 = Numero Enteros
        Estado 3 = Decimales
        Estado 10 = finalizacion Identificadores*/
        ArrayList<TokenModel> listaFinal = new ArrayList<>();
        BuscaReservados reservados= new BuscaReservados();
        Operadores operadorTemp= new Operadores();
        int estado = 0;
        String lexema = "";
        for (int i = 0; i < listaInicial.size(); i++) {
            if (listaInicial.get(i).getTipo() == "") {
                char[] clasificar = listaInicial.get(i).getLexema().toCharArray();
                for (int j = 0; j < clasificar.length; j++) {
                    //estado Inicial
                    if (estado == 0) {
                        if ((clasificar[j] > 96 && clasificar[j] < 123) || (clasificar[j] > 64 && clasificar[j] < 91)) {
                            estado = 1;
                            lexema += clasificar[j];
                        }else if(clasificar[j]==40 || clasificar[j]==41 || clasificar[j]==123 || clasificar[j]==125 
                                || clasificar[j]==91 || clasificar[j]==93 || clasificar[j]==44 ||
                                clasificar[j]==46){
                            listaFinal.add(clasificacionSignosSimbolos(clasificar[j], listaInicial.get(i).getColumna()+j, listaInicial.get(i).getFila()));
                            estado=0;
                        }else if (clasificar[j]>47 && clasificar[j]<58) {
                            estado=2;
                            lexema += clasificar[j];
                        }else if (operadorTemp.isAdjunto(clasificar[j])) {
                            System.out.println("encontro un operador");
                        }
                        //Estado de Identificadores
                    } else if (estado == 1) {
                        if ((clasificar[j] > 96 && clasificar[j] < 123) || (clasificar[j] > 64 && clasificar[j] < 91)
                                || (clasificar[j] > 47 && clasificar[j] < 58) || clasificar[j] == 95) {
                            estado = 1;
                            lexema += clasificar[j];
                            if(j==clasificar.length-1){
                            estado=10;
                            j--;}
                        } else if(clasificar[j] == 46 || clasificar[j]==40 || clasificar[j]==41){
                            estado=10;
                            j--;
                        }else {
                            if (lexema.equals("Console")) {
                                lexema += clasificar[j];
                                estado=1;
                            }else{
                                estado = 10;
                                j--;
                            }
                        }
                    //Estado para Enteros
                    }else if(estado==2){
                        if ((clasificar[j]>47 && clasificar[j]<58)|| clasificar[j]==32) {
                            lexema += clasificar[j];
                            if (j==clasificar.length-1 || clasificar[j]==32) {
                                TokenModel temp=new TokenModel(listaInicial.get(i).getColumna(), listaInicial.get(i).getFila(),
                                "Entero", "#1BA1E2", lexema);
                                listaFinal.add(reservados.Reservado(temp));
                                lexema = "";
                                estado=0;
                            }
                        }else if (clasificar[j]==46){
                            estado=3;
                            lexema += clasificar[j];
                        }else{
                            if (operadorTemp.isAdjunto(clasificar[j]) || clasificar[j]==44 || clasificar[j]==41) {
                              j--;
                              estado=0;
                              TokenModel temp=new TokenModel(listaInicial.get(i).getColumna(), listaInicial.get(i).getFila(),
                                "Entero", "#1BA1E2", lexema);
                                listaFinal.add(reservados.Reservado(temp));
                                lexema = "";
                                estado=0;
                                listaInicial.get(i).setColumna(listaInicial.get(i).getColumna()+j+1);
                            }else{
                                System.out.println("error"+clasificar[j]+listaInicial.get(i).getLexema()+j);
                            }
                        }
                    // Estado para decimales    
                    }else if(estado==3){
                        if ((clasificar[j]>47 && clasificar[j]<58)|| clasificar[j]==32) {
                            lexema += clasificar[j];
                            if (j==clasificar.length-1 || clasificar[j]==32) {
                                TokenModel temp=new TokenModel(listaInicial.get(i).getColumna(), listaInicial.get(i).getFila(),
                                "Decimal", "#FFFF88", lexema);
                                listaFinal.add(reservados.Reservado(temp));
                                lexema = "";
                            }
                        }else{
                            if (operadorTemp.isAdjunto(clasificar[j])) {
                              j--;
                              estado=0;
                              TokenModel temp=new TokenModel(listaInicial.get(i).getColumna(), listaInicial.get(i).getFila(),
                                "Entero", "#1BA1E2", lexema);
                                listaFinal.add(reservados.Reservado(temp));
                                lexema = "";
                                listaInicial.get(i).setColumna(listaInicial.get(i).getColumna()+j+1);
                            }else{
                                System.out.println("error decimal");
                            }
                        }
                        // Finalizar y almacenar token Identificador
                    } else if (estado == 10) {
                        estado = 0;
                        TokenModel temp=new TokenModel(listaInicial.get(i).getColumna(), listaInicial.get(i).getFila(),
                                "Identificador", "#FFD300", lexema);
                        if (j<clasificar.length-2 || clasificar[j]==40 || clasificar[j]==41) {
                            j--;
                        }
                        
                        listaFinal.add(reservados.Reservado(temp));
                        lexema = "";

                    }
                    /*if (estado==1 && j==clasificar.length-1) {
                    listaFinal.add(new TokenModel(listaInicial.get(i).getColumna(), listaInicial.get(i).getFila(),
                            "Identificador","#FFD300",lexema));
                    estado=0;
                }*/

                }
                lexema = "";
            }else{
                listaFinal.add(listaInicial.get(i));
            }
        }
        
        return eliminarNoclasificados(listaFinal);
    }
    
    public TokenModel clasificacionSignosSimbolos(char j, int col, int fil){
        TokenModel respuesta= new TokenModel(col,fil,""+j);
        if (j==40 || j==41) {
            respuesta.setColor("#9AD8DB");
            respuesta.setTipo("Parentesis");
        }else if ( j==123 || j==125) {
            respuesta.setColor("#DBD29A");
            respuesta.setTipo("Llaves");
        }else if (j==91 || j==93) {
            respuesta.setColor("#DBA49A");
            respuesta.setTipo("Corchetes");
        }else if (j==44) {
            respuesta.setColor("#B79ADB");
            respuesta.setTipo("Coma");
        }else if (j==46) {
            respuesta.setColor("#9ADBA6");
            respuesta.setTipo("Punto");
        }
        return respuesta;
    }
    
    private ArrayList<TokenModel> eliminarNoclasificados(ArrayList<TokenModel> fianl1){
        Reservados reservaTemp = new Reservados();
        ArrayList<TokenModel> finalFinal = new ArrayList<>();
        for (int i = 0; i < fianl1.size(); i++) {
            if (fianl1.get(i).getColor()!="") {
                finalFinal.add(fianl1.get(i));
            }
        }
        for (int i = 0; i < finalFinal.size(); i++) {
            if (reservaTemp.getReservadas().containsKey(finalFinal.get(i).getLexema())) {
                finalFinal.get(i).setTipo("Reservado");
                finalFinal.get(i).setColor("#60A917");
            }
        }
        return finalFinal;
    }
}

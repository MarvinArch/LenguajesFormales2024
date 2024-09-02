/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.analizador.practica1lenguaje2024.tokens;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 *
 * @author David
 */
@Data
@AllArgsConstructor
public class TokenModel {
    private int columna;
    private int fila;
    private String tipo;
    private String color;
    private String lexema;  

    public TokenModel(int columna, int fila, String lexema) {
        this.columna = columna;
        this.fila = fila;
        this.lexema = lexema;
    }

    @Override
    public String toString() {
        return "TokenModel{" + "columna=" + columna + ", fila=" + fila + ", tipo=" + tipo + ", color=" + color + ", lexema=" + lexema ;
    }
    
    
}

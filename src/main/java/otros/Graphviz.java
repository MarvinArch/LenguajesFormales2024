/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author David
 */
public class Graphviz {
    private ProcessBuilder proceso;
        
    public Graphviz() {
    }

    public void Dibujar(String direccionDot, String direccionPng) {
        
        try {
            /* CREAMOS PROCESOS DEL SISTEMA OPERATIVO */
            

            /* CONSTRUIMOS EL SIGUIENTE COMANDO PARA GENERAR EL AUTOMATA PLANTEADO */
            proceso = new ProcessBuilder("dot", "-Tpng", "-o", direccionPng, direccionDot);
            proceso.redirectErrorStream(true);

            /* EJECUTA EL PROCESO*/
            proceso.start();

        } catch (Exception e) {
            e.printStackTrace();
            //Dibujar(direccionDot, direccionPng);
        }
    }
    
    public void crearGrafico(String lexema, String direccionDot, String tipo){
        char[] caracteres= lexema.toCharArray();
        
        String cuerpo="digraph finite_state_machine {\n" +
                "	rankdir=LR;\n"
                +"node [shape = doublecircle]; S" +(caracteres.length) +";\n"
                +"node [shape = circle];\n"
                +"S0->S1 [label = \""+caracteres[0]+"\"]; \n";
        
        for (int i = 1; i < caracteres.length; i++) {
            cuerpo+="S"+i+"-> S"+(i+1)+" [label= \""+caracteres[i]+"\"]\n";
        }
        cuerpo+="}";
        guardarDot(cuerpo, direccionDot, tipo);

    }
    
    public void Identificador(String direccionDot){
        String cuerpo="digraph finite_state_machine {\n" +
            "	rankdir=LR;\n" +
            "	size=\"8,5\"\n" +
            "\n" +
            "	node [shape = doublecircle]; S1 S2 S3;\n" +
            "	node [shape = circle];\n" +
            "\n" +
        "	S0 -> S1 [ label = \"L\" ];\n" +
        "	S0 -> S2 [label= \"-\"];\n" +
        "	S1 -> S1 [label= \"L\"];\n" +
        "	S1 -> S2 [label= \"-\"];\n" +
        "	S1 -> S3 [label= \"D\"];\n" +
        "	S2 -> S1 [label= \"L\"];\n" +
        "	S2 -> S2 [label= \"-\"];\n" +
        "	S2 -> S3 [label= \"D\"];\n" +
        "	S3 -> S1 [label= \"L\"];\n" +
        "      	S3 -> S2 [label= \"-\"];\n" +
        "	S3 -> S3 [label= \"D\"];\n}";
        guardarDot(cuerpo, direccionDot,"Identificador");
    }
    
    public void Enteros(String direccionDot) {
        String cuerpo = "digraph finite_state_machine {\n"
                + "	rankdir=LR;\n"
                + "	size=\"8,5\"\n"
                + "\n"
                + "	node [shape = doublecircle]; S1 ;\n"
                + "	node [shape = circle];\n"
                + "\n"
                + "	S0 -> S1 [ label = \"D\" ];\n"
                + "	\n"
                + "	S1 -> S1 [label= \"D\"];\n"
                + "}";
        guardarDot(cuerpo, direccionDot, "Entero");
        
    }
    
    public void Decimal(String direccionDot){
        String cuerpo = "digraph finite_state_machine {\n"
                + "	rankdir=LR;\n"
                + "	size=\"8,5\"\n"
                + "\n"
                + "	node [shape = doublecircle]; S2 ;\n"
                + "	node [shape = circle];\n"
                + "\n"
                + "	S0 -> S1 [ label = \"D\" ];\n"
                + "	\n"
                + "	S1 -> S1 [label= \"D\"];\n"
                + "	S1 -> S2 [label= \".\"]\n"
                + "	S2 -> S2 [label= \"D\"];\n"
                + "}";
        guardarDot(cuerpo, direccionDot, "Decimal");
    }
    
    private void guardarDot(String cuerpo, String direccionDot, String tipo){
        try {
            
            File file = new File(direccionDot+tipo+".dot");
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(cuerpo);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

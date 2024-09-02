/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import otros.NumerarLineas;

/**
 *
 * @author David
 */
public class PanelEditable extends JScrollPane{
    JTextArea areaEditable;
    NumerarLineas numeros;
    public PanelEditable() {
        areaEditable = new JTextArea();
        this.add(areaEditable);
        areaEditable.setSize(this.getSize().width-50,this.getSize().height-200);
        numeros = new NumerarLineas(areaEditable);
        this.setViewportView(areaEditable);
        this.setRowHeaderView(numeros);
        
    }
    
    public int[] contadorLinea(String texto, int posicion){
        char espacio[]= texto.toCharArray();
        int posiciones[]=new int[2];
        posiciones[0]=0;
        posiciones[1]=0;
        int temp=0;
        for (int i = 0; i <posicion; i++) {
            if (espacio[i]=='\n') {
                posiciones[0]++;
                temp=i;
            }
        }
        posiciones[1]=posicion-temp;
        if(posiciones[0]==0) posiciones[1]++;
        return posiciones;
    }

    public JTextArea getAreaEditable() {
        return areaEditable;
    }
    
    public void editarTextArea(ArrayList<String> textoRecib){
        String textoIngresado="";
        for (int i = 0; i < textoRecib.size(); i++) {
            textoIngresado+=textoRecib.get(i)+"\n";
        }
        areaEditable.setText(textoIngresado);
    }
    
    
}

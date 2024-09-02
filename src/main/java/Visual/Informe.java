/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import com.analizador.practica1lenguaje2024.tokens.TokenModel;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author David
 */
public class Informe extends JFrame{
    ArrayList<TokenModel> listaTokens;

    public Informe(ArrayList<TokenModel> listaTokens) {
        this.listaTokens = listaTokens;
        this.setTitle("INFORME");
        initComponets();
    }
    
    private void initComponets(){
        this.setSize(720, 720);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JScrollPane fondo = new JScrollPane();
        fondo.setLayout(null);
        fondo.setBounds(5, 5, 700, 700);
        this.add(fondo);
        
        JLabel[][] cuadro = new JLabel[listaTokens.size()+1][5];
        cuadro[0][0] = new JLabel("Token", SwingConstants.CENTER);
        cuadro[0][0].setBounds(5,5,140,50);
        cuadro[0][0].setBorder(BorderFactory.createLineBorder(Color.black, 2));
        fondo.add(cuadro[0][0]);
        
        cuadro[0][1] = new JLabel("Lexema", SwingConstants.CENTER);
        cuadro[0][1].setBounds(145,5,140,50);
        cuadro[0][1].setBorder(BorderFactory.createLineBorder(Color.black, 2));
        fondo.add(cuadro[0][1]);
        
        cuadro[0][2] = new JLabel("Linea", SwingConstants.CENTER);
        cuadro[0][2].setBounds(285,5,140,50);
        cuadro[0][2].setBorder(BorderFactory.createLineBorder(Color.black, 2));
        fondo.add(cuadro[0][2]);
        
        cuadro[0][3] = new JLabel("Columna", SwingConstants.CENTER);
        cuadro[0][3].setBounds(425,5,140,50);
        cuadro[0][3].setBorder(BorderFactory.createLineBorder(Color.black, 2));
        fondo.add(cuadro[0][3]);
        
        cuadro[0][3] = new JLabel("Color", SwingConstants.CENTER);
        cuadro[0][3].setBounds(565,5,140,50);
        cuadro[0][3].setBorder(BorderFactory.createLineBorder(Color.black, 2));
        fondo.add(cuadro[0][3]);
        int posY=55;
        for (int i = 0; i < listaTokens.size(); i++) {
            cuadro[i+1][0] = new JLabel(listaTokens.get(i).getTipo(), SwingConstants.CENTER);
            cuadro[i+1][0].setBounds(5, posY, 140, 30);
            cuadro[i+1][0].setBorder(BorderFactory.createLineBorder(Color.black, 2));
            fondo.add(cuadro[i+1][0]);

            cuadro[i+1][1] = new JLabel(listaTokens.get(i).getLexema(), SwingConstants.CENTER);
            cuadro[i+1][1].setBounds(145, posY, 140, 30);
            cuadro[i+1][1].setBorder(BorderFactory.createLineBorder(Color.black, 2));
            fondo.add(cuadro[i+1][1]);

            cuadro[i+1][2] = new JLabel(listaTokens.get(i).getFila()+"", SwingConstants.CENTER);
            cuadro[i+1][2].setBounds(285, posY, 140, 30);
            cuadro[i+1][2].setBorder(BorderFactory.createLineBorder(Color.black, 2));
            fondo.add(cuadro[i+1][2]);

            cuadro[i+1][3] = new JLabel(listaTokens.get(i).getColumna()+"", SwingConstants.CENTER);
            cuadro[i+1][3].setBounds(425, posY, 140, 30);
            cuadro[i+1][3].setBorder(BorderFactory.createLineBorder(Color.black, 2));
            fondo.add(cuadro[i+1][3]);

            cuadro[i+1][3] = new JLabel(listaTokens.get(i).getColor(), SwingConstants.CENTER);
            cuadro[i+1][3].setBounds(565, posY, 140, 30);
            cuadro[i+1][3].setBorder(BorderFactory.createLineBorder(Color.black, 2));
            fondo.add(cuadro[i+1][3]);
            posY+=30;
        }
        
    }
}

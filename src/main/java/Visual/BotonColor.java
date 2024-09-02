/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import com.analizador.practica1lenguaje2024.tokens.TokenModel;
import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import otros.Graphviz;

/**
 *
 * @author David
 */
public class BotonColor extends JButton{
    private TokenModel token;

    public BotonColor() {
    }

    public void AsignarToken(TokenModel token){
        this.token=token;
        this.addActionListener(accion->{
            File file;
            try {
                file = new File(BotonColor.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
                String basePath = file.getParent();
                basePath= basePath.replace("target", "");
                System.out.println(basePath);
                Graphviz grafico = new Graphviz();
                if (token.getTipo().equalsIgnoreCase("Identificador")) {
                    grafico.Identificador(basePath);
                }else if (token.getTipo().equalsIgnoreCase("entero")){
                    grafico.Enteros(basePath);
                }else if (token.getTipo().equalsIgnoreCase("decimal")){
                    grafico.Decimal(basePath);
                }else{
                    grafico.crearGrafico(token.getLexema(), basePath, token.getTipo());
                }
                
                
                grafico.Dibujar(basePath+token.getTipo()+".dot", basePath+token.getTipo()+".png");
                GenerarGraficos ventana= new GenerarGraficos();
                ventana.dibujarGrafico(token.getTipo(), basePath, token.getLexema());
                ventana.setVisible(true);
            } catch (URISyntaxException ex) {
                Logger.getLogger(BotonColor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }

    public TokenModel getToken() {
        return token;
    }
    
}

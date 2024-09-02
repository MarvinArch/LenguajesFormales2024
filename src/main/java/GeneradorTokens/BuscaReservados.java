/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GeneradorTokens;

import com.analizador.practica1lenguaje2024.tokens.TokenModel;
import java.util.ArrayList;
import lombok.NoArgsConstructor;
import otros.Reservados;

/**
 *
 * @author David
 */
@NoArgsConstructor
public class BuscaReservados {
    ArrayList<TokenModel> reservados;

    public BuscaReservados(ArrayList<TokenModel> reservados) {
        this.reservados = reservados;
    }
    
    public ArrayList<TokenModel> clasificacionReservados(){
        
        
        
        
        return reservados;
    }
    
    public TokenModel Reservado(TokenModel buscar){
        if (buscar.getLexema().equalsIgnoreCase("True") || buscar.getLexema().equalsIgnoreCase("True")) {
            buscar.setTipo("Booleano");
            buscar.setColor("#FA6800");
        }
        return buscar;
    }
    
}

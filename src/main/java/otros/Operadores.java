/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otros;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 *
 * @author David
 */
@Data
public class Operadores {
    private Map<Character,String > operadores;
    
    private void llenarReservadas(){
        operadores =new HashMap<>();
        operadores.put('+', "Suma#FF33FF");
        operadores.put('-', "Resta#C19AB");
        operadores.put('*',"Multiplicacion#D80073");
        operadores.put('/', "Division#B4D941");
        operadores.put('^', "Exponente#FCD0B4");
        operadores.put( '%',"Modulo#D9AB41");
    }
    
    private void agregarOtrosSimbolos(){
        operadores.put('=', "Igual");
        operadores.put('<', "menor");
        operadores.put('>',"Mayor");
    }
    
    public boolean isAdjunto(char buscar){
        llenarReservadas();
        agregarOtrosSimbolos();
        if (operadores.containsKey(buscar)) {
            return true;
        }else{
            
            return false;
        }
        
    }
}

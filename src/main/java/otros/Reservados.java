/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
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
public class Reservados {

    private Map<String, String> reservadas;

    public Reservados() {
        llenarReservadas();
    }
    
    private void llenarReservadas(){
        reservadas= new HashMap<>();
        String[] reservadasLista={"Module","End","Sub","Main","Dim","As","Integer","String","Boolean",
        "Double","Char","Console.WriteLine","Console.ReadLine","If","ElseIf","Else","Then","While",
        "Do","Loop","For","To","Next","Function","Return","Const"};
        
        for (int i = 0; i < reservadasLista.length; i++) {
            reservadas.put(reservadasLista[i], "Reservada");
        }
    }
}


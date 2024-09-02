/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import GeneradorTokens.ClasificarTokens;
import GeneradorTokens.SeparaEspacios;
import com.analizador.practica1lenguaje2024.tokens.TokenModel;
import java.awt.Button;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import otros.CargarArchivo;
import otros.Guardar;
import otros.SeparaLineas;

/**
 *
 * @author David
 */
public class VentanaPrincipal extends JFrame{

    PanelEditable panel1;
    JPanel panel2;
    JLabel lineas;
    JPanel panelDerecho;
    JPanel panelDerechoInterior;
    public VentanaPrincipal() {
        initcomponent();
        posisionCursor();
        crearBarraMenu();
        //createAndShowGUI();
        this.setTitle("Analizador Lexico");
        
    }
    
    public void initcomponent(){
        this.setSize(1120, 720);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(null);
        //panel textarea
        panel1= new PanelEditable();
        panel1.setBounds(10, 10, (int)(this.getSize().width*0.6), this.getSize().height-100);
        this.add(panel1);
        //panel indicador columnafila
        lineas = new JLabel();
        panel2 = new JPanel();
        panel2.setBounds((int)(this.getSize().width*0.6)-200, this.getSize().height-100, 200, 50);
        this.add(panel2);
        panel2.add(lineas);
        lineas.setText("Linea:  Columna:");
        //panel cuadros
        panelDerecho= new JPanel();
        panelDerecho.setBounds((int)(this.getSize().width*0.6)+20, 10, (int)(this.getSize().width*0.4)-30, this.getSize().height-80);
        this.add(panelDerecho); 
        analizador();
    }
    
    public void modificarTamaños(int width, int height){
        panel1.setSize((int)(width*0.6), height-100);
        panel2.setLocation((int)(this.getSize().width*0.6)-200, this.getSize().height-90);
        panelDerecho.setBounds((int)(this.getSize().width*0.6)+20, 10, (int)(this.getSize().width*0.4)-30, this.getSize().height-80);
    }
    
    public void posisionCursor(){
        panel1.getAreaEditable().addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                JTextArea editArea = (JTextArea) e.getSource();
                int linea = 1;
                int columna = 1;

                try {
                    int caretpos = editArea.getCaretPosition();
                    int[] posFC=panel1.contadorLinea(editArea.getText(), caretpos);
                    linea = posFC[0];
                    columna=posFC[1];
                    

                    // Ya que las líneas las cuenta desde la 0
                    linea += 1;
                } catch (Exception ex) {
                }
                //modificarLabelConteo(linea, columna);
                lineas.setText("Linea: "+linea +" Columna:"+columna);
            }
        });
    }
    
    private void crearBarraMenu(){
        JMenuBar menu= new JMenuBar();
        JMenuItem cargar= new JMenuItem();
        cargar.setText("Cargar Archivo");
        cargar.addActionListener((carg) -> {
            //cargar Archivo
            CargarArchivo cargatemp = new CargarArchivo();
            ArrayList<String> texto = (ArrayList<String>) cargatemp.cargarMiArchivo(this);
            panel1.editarTextArea(texto);
        });
        JMenuItem guardar= new JMenuItem();
        guardar.setText("Guardar Archivo");
        this.add(menu);
        JMenu jMenu1 = new javax.swing.JMenu();
        jMenu1.setText("Archivo");
        jMenu1.add(cargar);
        jMenu1.add(guardar);
        JMenu jMenu2 = new javax.swing.JMenu();
        jMenu2.setText("otros");
        menu.add(jMenu1);
        menu.add(jMenu2);
        setJMenuBar(menu);
        guardar.addActionListener(guar->{
            Guardar temp= new Guardar();
            temp.guardarNuevoArchivo(this, panel1.getAreaEditable().getText());
        });
    }
    
    public void analizador(){
        panelDerechoInterior=new JPanel();
        panelDerechoInterior.setBounds(5, 70, panelDerecho.getSize().width-10, panelDerecho.getSize().height-70);
        panelDerecho.add(panelDerechoInterior);
        panelDerecho.setLayout(null);
        Button analizar = new Button();
        analizar.setLabel("Analizar");
        analizar.setBounds(10, 10, 100, 50);
        panelDerecho.add(analizar);
        
        Button informe = new Button();
        informe.setBounds(250, 10, 100, 50);
        informe.setLabel("Informe");
        panelDerecho.add(informe);
        
        JTextField filasNum = new JTextField();
        filasNum.setBounds((int)(panelDerecho.getSize().width/2), 10, 50, 30);
        filasNum.setVisible(false);
        panelDerecho.add(filasNum);
        JTextField columnasNum = new JTextField();
        columnasNum.setBounds((int)(panelDerecho.getSize().width/2), 50, 50, 30);
        columnasNum.setVisible(false);
        panelDerecho.add(columnasNum);
        
        Button empezar = new Button();
        empezar.setLabel("Iniciar");
        empezar.setBounds((int)(panelDerecho.getSize().width/2)+75, 30, 100, 50);
        panelDerecho.add(empezar);
        empezar.setVisible(false);
        
        JLabel filas = new JLabel();
        filas.setText("cantidad de Filas");
        filas.setBounds(10, 10, 200, 30);
        filas.setVisible(false);
        panelDerecho.add(filas);
        JLabel columnas = new JLabel();
        columnas.setText("cantidad de Columnas");
        columnas.setBounds(10, 40, 300, 50);
        columnas.setVisible(false);
        panelDerecho.add(columnas);
        analizar.addActionListener((lexico) -> {
            if (panel1.getAreaEditable().getText() != null) {
                analizar.setVisible(false);
                filas.setVisible(true);
                columnas.setVisible(true);
                filasNum.setVisible(true);
                columnasNum.setVisible(true);
                empezar.setVisible(true);
                informe.setVisible(false);
            }
            
        });
        
        empezar.addActionListener((cuadricula) -> {
            try{
                //comienza analizador de lineas
                SeparaLineas arregloLineas= new SeparaLineas();
                ArrayList<String> recepcion =arregloLineas.separaLineas(panel1.getAreaEditable().getText());
                SeparaEspacios arregloPalabras= new SeparaEspacios(recepcion);
                ClasificarTokens tookenFinal= new ClasificarTokens();
                ArrayList<TokenModel> recepcion2=arregloPalabras.BuscarEspacios();
                ArrayList<TokenModel> recepcion3=tookenFinal.clasificacionTokens(recepcion2);
               // FInalizado el analisis devuelve un arreglo de tokens 
                int x = Integer.parseInt(filasNum.getText());
                int y = Integer.parseInt(columnasNum.getText());
                generaCuadricula(x, y, recepcion3);
                analizar.setVisible(true);
                filas.setVisible(false);
                columnas.setVisible(false);
                filasNum.setVisible(false);
                columnasNum.setVisible(false);
                empezar.setVisible(false);
                informe.setVisible(true);
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "Valores ingresados incorrectos");
            }
        });
        
        informe.addActionListener(info->{
            //comienza analizador de lineas
                SeparaLineas arregloLineas= new SeparaLineas();
                ArrayList<String> recepcion =arregloLineas.separaLineas(panel1.getAreaEditable().getText());
                SeparaEspacios arregloPalabras= new SeparaEspacios(recepcion);
                ClasificarTokens tookenFinal= new ClasificarTokens();
                ArrayList<TokenModel> recepcion2=arregloPalabras.BuscarEspacios();
                ArrayList<TokenModel> recepcion3=tookenFinal.clasificacionTokens(recepcion2);
            Informe infTemp= new Informe(recepcion3);
            infTemp.setVisible(true);
        });
    }
    
    public void generaCuadricula(int x, int y, ArrayList<TokenModel> toks){
        
        panelDerechoInterior.removeAll();
        panelDerechoInterior.setLayout(null);
        int ancho=(int)(panelDerechoInterior.getSize().width/x);
        int alto = (int)(panelDerechoInterior.getSize().height/y);
        int posX = 0;
        int posY = 0;
        int contado=0;
        BotonColor[][] cuadricula = new BotonColor[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cuadricula[i][j]= new BotonColor();
                cuadricula[i][j].setBounds(posX, posY, ancho, alto);
                panelDerechoInterior.add(cuadricula[i][j]);
                if (contado<toks.size()) {
                    cuadricula[i][j].AsignarToken(toks.get(contado));
                    Color color = Color.decode(toks.get(contado).getColor());
                    cuadricula[i][j].setBackground(color);
                    
                }
                posX+=ancho;
                contado++;
            }
            posX=0;
            posY+=alto;
        }
        panelDerechoInterior.updateUI();
    }
    
    
    /*
    private void createAndShowGUI() {
       
        // Crear el panel deslizante
        JPanel slidingPanel = new JPanel();
        slidingPanel.setBackground(Color.LIGHT_GRAY);
        slidingPanel.setBounds(0, this.getHeight(), this.getWidth(), (int)(this.getHeight() / 2));
        slidingPanel.setLayout(new BorderLayout());

        // Crear el botón de cerrar
        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(e -> slideDown(slidingPanel, this));
        slidingPanel.add(closeButton, BorderLayout.NORTH);

        this.add(slidingPanel, JLayeredPane.PALETTE_LAYER);

        this.setVisible(true);

        // Iniciar el deslizamiento hacia arriba
        slideUp(slidingPanel, this);
    }

    private void slideUp(JPanel panel, JFrame frame) {
        Timer timer = new Timer(10, new ActionListener() {
            int y = frame.getHeight(); // Posición inicial del panel (debajo de la ventana)

            @Override
            public void actionPerformed(ActionEvent e) {
                if (y > frame.getHeight() / 2) {
                    y -= 10; // Velocidad del deslizamiento
                    panel.setBounds(0, y, frame.getWidth(), frame.getHeight() / 2);
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    private void slideDown(JPanel panel, JFrame frame) {
        Timer timer = new Timer(10, new ActionListener() {
            int y = panel.getY(); // Posición inicial del panel

            @Override
            public void actionPerformed(ActionEvent e) {
                if (y < frame.getHeight()) {
                    y += 10; // Velocidad del deslizamiento
                    panel.setBounds(0, y, frame.getWidth(), frame.getHeight() / 2);
                } else {
                    ((Timer) e.getSource()).stop();
                    panel.setVisible(false);
                }
            }
        });
        timer.start();
    }*/
}

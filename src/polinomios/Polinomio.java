package polinomios;

import java.awt.Font;
import javax.swing.JLabel;

public class Polinomio {
    
    private Nodo cabeza;
    
    public Polinomio() {
        cabeza = null;
    }
    
    public void insertar(Nodo n, Nodo antecesor) {
        if (n != null) {
            if (antecesor != null) {
                n.siguiente = antecesor.siguiente;
                antecesor.siguiente = n;
            } else {
                n.siguiente = cabeza;
                cabeza = n;
            }
        }
    }
    
    public void agregar(Nodo n) {
        if (n != null) {
            if (cabeza == null) {
                cabeza = n;
            } else {
                Nodo apuntador = cabeza;
                Nodo antecesor = null;
                int encontrado = 0;
                while (apuntador != null && encontrado == 0) {
                    if (apuntador.getExponente() > n.getExponente()) {
                        encontrado = 2;
                    } else {
                        antecesor = apuntador;
                        apuntador = apuntador.siguiente;
                    }
                }
                if (encontrado == 2) {
                    insertar(n, antecesor);
                }
                
            }
        }
    }
    
    public String[] mostrar() {
        String[] lineas = new String[2];
        String espacio = " ";
        lineas[0] = "";
        lineas[1] = "";
        Nodo apuntador = cabeza;
        while (apuntador != null) {
            String texto = String.valueOf(apuntador.getCoeficiente()) + " X";
            if (apuntador.getCoeficiente() >= 0) {
                texto = "+" + texto;
            }
            
            lineas[0] += String.format("%0" + texto.length() + "d").replace("0", espacio);
            lineas[1] += texto;
            
            texto = String.valueOf(apuntador.getExponente());
            lineas[0] += texto;
            lineas[1] += String.format("%0" + texto.length() + "d").replace("0", espacio);
            
            apuntador = apuntador.siguiente;
        }
        
        return lineas;
    }
    
    public void mostrar(JLabel lbl) {
        String[] lineas = mostrar();
        String espacio = "&nbsp;";
        lineas[0] = lineas[0].replace(" ", espacio);
        lineas[1] = lineas[1].replace(" ", espacio);
        lbl.setFont(new Font("Courier New", Font.PLAIN, 12));
        lbl.setText("<html>" + lineas[0] + "<br>" + lineas[1] + "</html>");
    }
    
}

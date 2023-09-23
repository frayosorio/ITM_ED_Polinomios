package polinomios;

import java.awt.Font;
import javax.swing.JLabel;

public class Polinomio {
    
    private Nodo cabeza;
    
    public Nodo getCabeza() {
        return cabeza;
    }
    
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
                    if (apuntador.getExponente() == n.getExponente()) {
                        encontrado = 1;
                    } else if (apuntador.getExponente() > n.getExponente()) {
                        encontrado = 2;
                    } else {
                        antecesor = apuntador;
                        apuntador = apuntador.siguiente;
                    }
                }
                if (encontrado == 1) {
                    double coeficiente = apuntador.getCoeficiente() + n.getCoeficiente();
                    if (coeficiente == 0) {
                        if (antecesor == null) {
                            cabeza = apuntador.siguiente;
                        } else {
                            antecesor.siguiente = apuntador.siguiente;
                        }
                        apuntador = null;
                    } else {
                        apuntador.actualizar(coeficiente, apuntador.getExponente());
                    }
                } else {
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
            
            lineas[0] += String.format("%0" + texto.length() + "d", 0).replace("0", espacio);
            lineas[1] += texto;
            
            texto = String.valueOf(apuntador.getExponente());
            lineas[0] += texto;
            lineas[1] += String.format("%0" + texto.length() + "d", 0).replace("0", espacio);
            
            apuntador = apuntador.siguiente;
        }
        
        return lineas;
    }
    
    public void mostrar(JLabel lbl) {
        String[] lineas = mostrar();
        String espacio = "&nbsp;";
        lineas[0] = lineas[0].replace(" ", espacio);
        lineas[1] = lineas[1].replace(" ", espacio);
        lbl.setFont(new Font("Courier New", Font.PLAIN, 14));
        lbl.setText("<html>" + lineas[0] + "<br>" + lineas[1] + "</html>");
    }
    
    public Nodo getNodoMayorExponente() {
        if (cabeza != null) {
            Nodo apuntador = cabeza;
            while (apuntador.siguiente != null) {
                apuntador = apuntador.siguiente;
            }
            return apuntador;
        }
        return null;
    }
    
    public Polinomio getCopia() {
        Polinomio p = new Polinomio();
        Nodo apuntador = cabeza;
        while (apuntador != null) {
            Nodo n = new Nodo(apuntador.getCoeficiente(), apuntador.getExponente());
            p.agregar(n);
            apuntador = apuntador.siguiente;
        }  
        return p;
    }

    //************ Métodos Estáticos
    public static Polinomio sumar(Polinomio p1, Polinomio p2) {
        Polinomio pR = new Polinomio();
        
        Nodo apuntador1 = p1.getCabeza();
        Nodo apuntador2 = p2.getCabeza();
        
        while (!(apuntador1 == null && apuntador2 == null)) {
            Nodo n = new Nodo();
            if (apuntador1 != null && apuntador2 != null && apuntador1.getExponente() == apuntador2.getExponente()) {
                double coeficiente = apuntador1.getCoeficiente() + apuntador2.getCoeficiente();
                if (coeficiente != 0) {
                    n.actualizar(coeficiente, apuntador1.getExponente());
                } else {
                    n = null;
                }
                apuntador1 = apuntador1.siguiente;
                apuntador2 = apuntador2.siguiente;
            } else if (apuntador2 == null || (apuntador1 != null && apuntador1.getExponente() < apuntador2.getExponente())) {
                n.actualizar(apuntador1.getCoeficiente(), apuntador1.getExponente());
                apuntador1 = apuntador1.siguiente;
            } else {
                n.actualizar(apuntador2.getCoeficiente(), apuntador2.getExponente());
                apuntador2 = apuntador2.siguiente;
            }
            
            pR.agregar(n);
        }
        
        return pR;
    }
    
    public static Polinomio restar(Polinomio p1, Polinomio p2) {
        Polinomio pR = new Polinomio();
        
        Nodo apuntador1 = p1.getCabeza();
        Nodo apuntador2 = p2.getCabeza();
        
        while (!(apuntador1 == null && apuntador2 == null)) {
            Nodo n = new Nodo();
            if (apuntador1 != null && apuntador2 != null && apuntador1.getExponente() == apuntador2.getExponente()) {
                double coeficiente = apuntador1.getCoeficiente() - apuntador2.getCoeficiente();
                if (coeficiente != 0) {
                    n.actualizar(coeficiente, apuntador1.getExponente());
                } else {
                    n = null;
                }
                apuntador1 = apuntador1.siguiente;
                apuntador2 = apuntador2.siguiente;
            } else if (apuntador2 == null || (apuntador1 != null && apuntador1.getExponente() < apuntador2.getExponente())) {
                n.actualizar(apuntador1.getCoeficiente(), apuntador1.getExponente());
                apuntador1 = apuntador1.siguiente;
            } else {
                n.actualizar(-apuntador2.getCoeficiente(), apuntador2.getExponente());
                apuntador2 = apuntador2.siguiente;
            }
            
            pR.agregar(n);
        }
        
        return pR;
    }
    
    public static Polinomio multiplicar(Polinomio p1, Polinomio p2) {
        Polinomio pR = new Polinomio();
        
        Nodo apuntador1 = p1.getCabeza();
        Nodo apuntador2 = p2.getCabeza();
        while (apuntador1 != null) {
            while (apuntador2 != null) {
                Nodo n = new Nodo(apuntador1.getCoeficiente() * apuntador2.getCoeficiente(),
                        apuntador1.getExponente() + apuntador2.getExponente());
                
                pR.agregar(n);
                apuntador2 = apuntador2.siguiente;
            }
            apuntador2 = p2.getCabeza();
            apuntador1 = apuntador1.siguiente;
        }
        
        return pR;
    }
    
    public static boolean esDivisible(Nodo n1, Nodo n2) {
        return n1.getCoeficiente() % n2.getCoeficiente() == 0 && n1.getExponente() >= n2.getExponente();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */

import java.util.ArrayList ;
    import java.util.List ;

public class Menu {
    
        private final String tipo;
        private final List<Plato> platos;

        public Menu(String tipo) {
            this.tipo = tipo;
            this.platos = new ArrayList<>();
        }

        public void agregarPlato(Plato plato) {
            platos.add(plato);
        }

        public void mostrarMenu() {
            System.out.println("Menú de " + tipo + ":");
            for (Plato p : platos) {
                System.out.println(p);
            }
        }

        public String getTipo() {
            return tipo;
        }

        public List<Plato> getPlatos() {
            return platos;
        }
    }
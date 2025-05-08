/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */

import java.io.*;

public class Persistencia {
    
    public static void guardarMenu(Menu menu) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(menu.getTipo() + ".txt"))) {
            for (Plato plato : menu.getPlatos()) {
                bw.write(plato.getNombre() + "," + plato.getPrecio());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el menú: " + e.getMessage());
        }
    }

    public static Menu cargarMenu(String tipo) {
        Menu menu = new Menu(tipo);
        try (BufferedReader br = new BufferedReader(new FileReader(tipo + ".txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                menu.agregarPlato(new Plato(partes[0], Double.parseDouble(partes[1])));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el menú: " + e.getMessage());
        }
        return menu;
    }
}

    

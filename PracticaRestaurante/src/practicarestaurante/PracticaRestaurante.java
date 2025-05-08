/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicarestaurante;

/**
 *
 * @author user
 */

import java.util.Scanner;

public class PracticaRestaurante {



  public class Main {
    public static void main(String[] args) {
        Menu desayuno = Persistencia.cargarMenu("Desayuno");
        Menu almuerzo = Persistencia.cargarMenu("Almuerzo");
        Menu cena = Persistencia.cargarMenu("Cena");

        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menú del Empleado ---");
            System.out.println("1. Ver menú de desayuno");
            System.out.println("2. Ver menú de almuerzo");
            System.out.println("3. Ver menú de cena");
            System.out.println("4. Agregar plato");
            System.out.println("5. Guardar menús");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> desayuno.mostrarMenu();
                case 2 -> almuerzo.mostrarMenu();
                case 3 -> cena.mostrarMenu();
                case 4 -> {
                    System.out.print("¿A qué menú desea agregar (Desayuno/Almuerzo/Cena)?: ");
                    String tipo = sc.nextLine();
                    System.out.print("Nombre del plato: ");
                    String nombre = sc.nextLine();
                    System.out.print("Precio del plato: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();

                    Plato nuevo = new Plato(nombre, precio);
                    switch (tipo.toLowerCase()) {
                        case "desayuno" -> desayuno.agregarPlato(nuevo);
                        case "almuerzo" -> almuerzo.agregarPlato(nuevo);
                        case "cena" -> cena.agregarPlato(nuevo);
                        default -> System.out.println("Tipo de menú no válido.");
                    }
                }
                case 5 -> {
                    Persistencia.guardarMenu(desayuno);
                    Persistencia.guardarMenu(almuerzo);
                    Persistencia.guardarMenu(cena);
                    System.out.println("Menús guardados.");
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }
}


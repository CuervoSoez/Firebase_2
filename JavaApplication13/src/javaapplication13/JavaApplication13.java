/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author user
 */
public class JavaApplication13 {

     public static void main(String[] args) throws Exception {
        FirebaseService firebaseService = new FirebaseService();

        Item item = new Item(100L, "Producto actualizado", 150.0);

        firebaseService.save(item);
        firebaseService.read();
        firebaseService.update(item);
        firebaseService.read(); // Verifica actualización
        firebaseService.delete();
    }
}
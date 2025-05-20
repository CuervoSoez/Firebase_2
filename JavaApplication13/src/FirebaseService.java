/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;


public class FirebaseService {
public class FireService {
    private FirebaseDatabase firebaseDatabase;

    private void initFirebase() throws FileNotFoundException {
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                .setDatabaseUrl("https://prueba2-97bb1.firebaseio")
                .setServiceAccount(new FileInputStream(new File("C:\\Users\\user\\Documents\\pruebafirebase.json")))
                .build();

            FirebaseApp.initializeApp(firebaseOptions);
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        System.out.println("Conexión exitosa...");
    }

    public void save(Item item) throws FileNotFoundException {
        if (item != null) {
            initFirebase();
            DatabaseReference ref = firebaseDatabase.getReference("item");

            CountDownLatch latch = new CountDownLatch(1);
            ref.setValue(item, (error, ref1) -> {
                System.out.println("Item guardado.");
                latch.countDown();
            });

            awaitLatch(latch);
        }
    }

    public void read() throws FileNotFoundException {
        initFirebase();
        DatabaseReference ref = firebaseDatabase.getReference("item");

        CountDownLatch latch = new CountDownLatch(1);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                Item item = snapshot.getValue(Item.class);
                if (item != null) {
                    System.out.println("Leído:");
                    System.out.println("ID: " + item.getId());
                    System.out.println("Nombre: " + item.getName());
                    System.out.println("Precio: " + item.getPrice());
                } else {
                    System.out.println("No se encontró el item.");
                }
                latch.countDown();
            }

            public void onCancelled(DatabaseError error) {
                System.out.println("Error al leer: " + error.getMessage());
                latch.countDown();
            }
        });

        awaitLatch(latch);
    }

    public void update(Item item) throws FileNotFoundException {
        if (item != null) {
            initFirebase();
            DatabaseReference ref = firebaseDatabase.getReference("item");

            CountDownLatch latch = new CountDownLatch(1);
            ref.updateChildrenAsync(item.toMap()).addListener(() -> {
                System.out.println("Item actualizado.");
                latch.countDown();
            }, Runnable::run);

            awaitLatch(latch);
        }
    }

    public void delete() throws FileNotFoundException {
        initFirebase();
        DatabaseReference ref = firebaseDatabase.getReference("item");

        CountDownLatch latch = new CountDownLatch(1);
        ref.removeValue((error, ref1) -> {
            System.out.println(error == null ? "Item eliminado." : "Error: " + error.getMessage());
            latch.countDown();
        });

        awaitLatch(latch);
    }

    private void awaitLatch(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
}

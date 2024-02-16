package exercice2;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            // Flux d'entr�e pour recevoir l'objet
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // R�ception de l'objet voiture du serveur
            voiture voiture = (voiture) in.readObject();

            // Utilisation de l'objet re�u
            System.out.println("Carburant de la voiture c�t� client : " + voiture.getCarburant());

            in.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

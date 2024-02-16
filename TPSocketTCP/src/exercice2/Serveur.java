package exercice2;

import java.io.*;
import java.net.*;

public class Serveur {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Attente de connexion...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connecté.");

            // Flux de sortie pour envoyer l'objet
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            // Création d'un objet voiture et envoi au client
            voiture voiture = new voiture("Sedan", "Toyota");
            voiture.setCarburant(50);

            out.writeObject(voiture);

            out.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

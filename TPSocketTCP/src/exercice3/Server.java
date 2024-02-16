package exercice3;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            System.out.println("Serveur en attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nouvelle connexion �tablie.");

                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

                Personne personne = (Personne) ois.readObject();
                int clientId =111; // G�n�rer un identifiant unique pour le client (� impl�menter selon les besoins).

                System.out.println("Re�u des donn�es de la part du client " + clientId + ": " + personne.getNom() + ", " + personne.getAge());

                oos.writeInt(clientId);
                oos.flush();

                ois.close();
                oos.close();
                clientSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
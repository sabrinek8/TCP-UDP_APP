package exercice1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketServeur {
    public static void main(String argv[]) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);

        // Demander à l'utilisateur de saisir le port d'écoute
        System.out.print("Port d'écoute : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            // Gérer l'exception si l'utilisateur ne saisit pas un entier
            System.err.println("Le paramètre n'est pas un entier.");
            System.err.println("Usage : java ServeurUDP port-serveur");
            System.exit(-1);
        }

        try {
            // Créer un serveur socket en écoutant sur le port spécifié
            ServerSocket serverSocket = new ServerSocket(port);

            // Attendre qu'un client se connecte
            Socket socket = serverSocket.accept();

            // Configurer les flux de sortie et d'entrée pour la communication avec le client
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Lire une chaîne de caractères envoyée par le client
            String chaine = (String) input.readObject();
            System.out.println(" reçu : " + chaine);

            // Afficher les informations sur l'adresse et le port du client
            System.out.println(" ça vient de : " + socket.getInetAddress() +
                    ":" + socket.getPort());

            // Envoyer une confirmation au client
            output.writeObject(new String("bien reçu"));
        } catch (Exception e) {
            // Gérer les exceptions possibles
            System.err.println("Erreur : " + e);
        }
    }
}


package exercice1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);

        // Demander à l'utilisateur de saisir le nom du serveur et le port
        System.out.print("Nom du serveur : ");
        host = keyb.next();
        System.out.print("Port du serveur : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            // Gérer l'exception si le port n'est pas un entier
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }

        try {
            // Résoudre l'adresse IP du serveur à partir du nom fourni par l'utilisateur
            InetAddress adr = InetAddress.getByName(host);

            // Créer un socket client pour se connecter au serveur
            Socket socket = new Socket(adr, port);

            // Configurer les flux de sortie et d'entrée pour la communication avec le serveur
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Envoyer une chaîne de caractères au serveur
            output.writeObject(new String("ma première socket"));

            // Lire la réponse du serveur
            String chaine = (String) input.readObject();
            System.out.println(" reçu du serveur : " + chaine);
        } catch (Exception e) {
            // Gérer les exceptions possibles
            System.err.println("Erreur : " + e);
        }
    }
}

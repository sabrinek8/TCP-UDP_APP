package exercice1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketServeur {
    public static void main(String argv[]) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);

        // Demander � l'utilisateur de saisir le port d'�coute
        System.out.print("Port d'�coute : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            // G�rer l'exception si l'utilisateur ne saisit pas un entier
            System.err.println("Le param�tre n'est pas un entier.");
            System.err.println("Usage : java ServeurUDP port-serveur");
            System.exit(-1);
        }

        try {
            // Cr�er un serveur socket en �coutant sur le port sp�cifi�
            ServerSocket serverSocket = new ServerSocket(port);

            // Attendre qu'un client se connecte
            Socket socket = serverSocket.accept();

            // Configurer les flux de sortie et d'entr�e pour la communication avec le client
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Lire une cha�ne de caract�res envoy�e par le client
            String chaine = (String) input.readObject();
            System.out.println(" re�u : " + chaine);

            // Afficher les informations sur l'adresse et le port du client
            System.out.println(" �a vient de : " + socket.getInetAddress() +
                    ":" + socket.getPort());

            // Envoyer une confirmation au client
            output.writeObject(new String("bien re�u"));
        } catch (Exception e) {
            // G�rer les exceptions possibles
            System.err.println("Erreur : " + e);
        }
    }
}


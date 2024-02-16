package exercice1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);

        // Demander � l'utilisateur de saisir le nom du serveur et le port
        System.out.print("Nom du serveur : ");
        host = keyb.next();
        System.out.print("Port du serveur : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            // G�rer l'exception si le port n'est pas un entier
            System.err.println("Le second param�tre n'est pas un entier.");
            System.exit(-1);
        }

        try {
            // R�soudre l'adresse IP du serveur � partir du nom fourni par l'utilisateur
            InetAddress adr = InetAddress.getByName(host);

            // Cr�er un socket client pour se connecter au serveur
            Socket socket = new Socket(adr, port);

            // Configurer les flux de sortie et d'entr�e pour la communication avec le serveur
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Envoyer une cha�ne de caract�res au serveur
            output.writeObject(new String("ma premi�re socket"));

            // Lire la r�ponse du serveur
            String chaine = (String) input.readObject();
            System.out.println(" re�u du serveur : " + chaine);
        } catch (Exception e) {
            // G�rer les exceptions possibles
            System.err.println("Erreur : " + e);
        }
    }
}

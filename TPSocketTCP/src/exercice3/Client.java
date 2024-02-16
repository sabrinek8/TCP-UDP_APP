package exercice3;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6789);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Personne personne = new Personne(22, "Sabrine Kammoun");

            oos.writeObject(personne);
            oos.flush();

            int clientId = ois.readInt();
            System.out.println("Identifiant du client reçu du serveur : " + clientId);

            oos.close();
            ois.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

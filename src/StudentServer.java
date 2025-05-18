import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class StudentServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5400);
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println("Server startet på port 5000");

        while (true) {
            Socket socket = server.accept();
            pool.submit(() -> handleClient(socket));
        }
    }

    private static void handleClient(Socket socket) {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            Student s = (Student) in.readObject();
            StudentDAO.insertStudent(s);
            System.out.println("Lagret student: " + s.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


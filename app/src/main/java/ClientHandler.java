import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler extends Thread {
 
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    public ClientHandler(Socket socket) throws IOException {
    
        
        this.socket = socket;

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
         
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));

    
    }

    public void run() {
           String buffer;
        try {
         
            while ((buffer= this.reader.readLine()) != null) {
                System.out.println("Received: " + buffer);
                writer.write("Echo: " + buffer + "\r\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

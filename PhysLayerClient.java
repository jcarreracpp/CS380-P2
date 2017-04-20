
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
 *
 * @author Jorge
 */
public class PhysLayerClient {
        public static void main(String[] args) throws Exception {
                    try (Socket socket = new Socket("codebank.xyz", 38002)) {
                    byte a = 0;
                    int b = 0;
                    float c = 0.0f;
                        
                    InputStream is = socket.getInputStream();
                    OutputStream os = socket.getOutputStream();
                    
                    
                    System.out.print("Baseline established: ");
                    for(int i = 0; i < 64; i++){
                        c = c + is.read();
                        //b = b + a;
                    }
                    c = (float)(c/64);
                    System.out.printf("%.2f" + "\n", c);
                    //System.out.print(String.format("%x", a) + " ");
                    }
        }
    
}

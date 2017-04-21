
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
                    byte[] b = new byte[32];
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
                    
                    System.out.print("Bytes recieved: " + "\n");
                    for(int j = 0; j < 32; j++){
                        a = (byte) is.read();
                        b[j] = a;
                        String s1 = String.format("%8s", Integer.toBinaryString(b[j] & 0xFF)).replace(' ', '0');
                        System.out.println(s1 + " ");
                        //System.out.print(String.format("%x", a) + " ");
                    }
                    System.out.println();
                    
                    System.out.print("Bytes translat: ");
                    for(int k = 0; k < 32; k++){
                        if(b[k] < c ){
                            System.out.print("0  ");
                        }else{
                            System.out.print("1  ");
                        }
                    }
                    System.out.println();
                    //System.out.print(String.format("%x", a) + " ");
                    }
        }
    
}

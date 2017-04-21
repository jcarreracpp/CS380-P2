
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
                    byte a;
                    byte[] b = new byte[320];
                    byte[] converted = new byte[320];
                    float c = 0.0f;
                    int previousNum = 0;
                        
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
                    for(int j = 0; j < 320; j++){
                        a = (byte) is.read();
                        b[j] = a;
                        String s1 = String.format("%8s", Integer.toBinaryString(b[j] & 0xFF)).replace(' ', '0');
                        System.out.println(s1 + " ");
                        //System.out.print(String.format("%x", a) + " ");
                    }
                    System.out.println();
                    
                    System.out.print("Bytes translat: ");
                    for(int k = 0; k < 320; k++){
                        for(int l = 7; l > 0; l--){
                            if(previousNum == 0){
                                if((((b[l] >> l) & 1) == 1)){
                                    b[l] |= (1 << l);
                                    previousNum = 1;
                                }
                            }else{
                                if((((b[l] >> l) & 1) == 1)){
                                    b[l] |= (0 << l);
                                    previousNum = 1;
                                }
                            }
                        }
                    }
                    
                    System.out.println();
                    
                    for(int m = 0; m < 320; m++){
                        a = (byte)00000000;
                        if(b[m] > c){
                        }
                    }
                    
                    System.out.print("Good? ");
                    os.write(b);
                    System.out.print(is.read());
                    //System.out.print(String.format("%x", a) + " ");
                    }
        }
    
}

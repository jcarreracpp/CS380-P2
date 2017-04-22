
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
                    byte d;
                    byte[] b = new byte[320];
                    byte[] converted = new byte[320];
                    byte[] fiveB = new byte[64];
                    byte[] result = new byte[32];
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
                    
                    System.out.print("INTAKE" + "\n");
                    for(int j = 0; j < 320; j++){
                        a = (byte) is.read();
                        b[j] = a;
                        String s1 = String.format("%8s", Integer.toBinaryString(b[j] & 0xFF)).replace(' ', '0');
                        System.out.println(s1 + " ");
                        //System.out.print(String.format("%x", a) + " ");
                    }
                    System.out.println();
                    
//                    System.out.print("NRZI");
//                    for(int k = 0; k < 320; k++){
//                        previousNum = 0;
//                        for(int l = 7; l > 0; l--){
//                            if(previousNum == 0){
//                                if((((b[k] >> l) & 1) == 1)){
//                                    converted[k] |= (1 << l);
//                                    previousNum = 1;
//                                }else if ((((b[k] >> l) & 1) == 0)){
//                                    converted[k] |= (0 << l);
//                                    previousNum = 0;
//                                }
//                            }else{
//                                if((((b[k] >> l) & 1) == 1)){
//                                    converted[k] |= (0 << l);
//                                    previousNum = 0;
//                                }else if ((((b[k] >> l) & 1) == 0)){
//                                    converted[k] |= (1 << l);
//                                    previousNum = 1;
//                                }
//                            }
//                        }
//                    }
//                    
//                    System.out.println();
                    System.out.print("REVERSE NRZI");
                    for(int k = 0; k < 320; k++){
                        previousNum = 0;
                        for(int l = 7; l >= 0; l--){
                            if(previousNum == 0){
                                if((((b[k] >> l) & 1) == 1)){
                                    converted[k] |= (1 << l);
                                    previousNum = 1;
                                }else if ((((b[k] >> l) & 1) == 0)){
                                    converted[k] |= (0 << l);
                                    previousNum = 0;
                                }
                            }else{
                                if((((b[k] >> l) & 1) == 1)){
                                    converted[k] |= (0 << l);
                                    previousNum = 1;
                                }else if ((((b[k] >> l) & 1) == 0)){
                                    converted[k] |= (1 << l);
                                    previousNum = 0;
                                }
                            }
                        }
                    }
                    
                    System.out.println();
                    
                    
                                       System.out.print("CONVERTED: " + "\n");
                    for(int j = 0; j < 320; j++){
                        String s1 = String.format("%8s", Integer.toBinaryString(converted[j] & 0xFF)).replace(' ', '0');
                        System.out.println(s1 + " ");
                        //System.out.print(String.format("%x", a) + " ");
                    }
                    System.out.println();
                    
                    for(int m = 0; m < 320; m += 5){
                        a = 0;
                        
                        for(int n = 0; n <= 4; n++){
                            if(converted[m + n] >= c){
                                a |= (1 << (4 - n));
                            }else{
                                a &= ~(0 << (4 - n));
                            }
                        }
                        fiveB[m/5] = (byte)a;
                    }
                    
                    System.out.println("\nFIVE CONVERTEDS:");
                    for(int o = 0; o < 64; o++){

                                                String s1 = String.format("%8s", Integer.toBinaryString(fiveB[o] & 0xFF)).replace(' ', '0');
                        System.out.println(s1 + " ");
                    }
                    

                    System.out.print("Good? ");
                    os.write(fiveB);
                    System.out.print(is.read());
                    //System.out.print(String.format("%x", a) + " ");
                    }
        }
    
}

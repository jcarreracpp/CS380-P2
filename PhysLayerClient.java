
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
                    byte[] A = new byte[64];
                    byte[] C = new byte[64];
                    byte[] result = new byte[32];
                    int[] decode = new int[320];
                    double c = 0.0;
                    int previousNum = 0;
                        
                    InputStream is = socket.getInputStream();
                    OutputStream os = socket.getOutputStream();
                    
                    

//                    for(int i = 0; i < 64; i++){
//                        A[i] = (byte)is.read();
//                    }
//                    
//                    System.out.print("REVERSE NRZI");
//                    for(int k = 0; k < 64; k++){
//                        previousNum = 0;
//                        for(int l = 7; l >= 0; l--){
//                            if(previousNum == 0){
//                                if((((A[k] >> l) & 1) == 1)){
//                                    C[k] |= (1 << l);
//                                    previousNum = 1;
//                                }else if ((((A[k] >> l) & 1) == 0)){
//                                    C[k] &= ~(1 << l);
//                                    previousNum = 0;
//                                }
//                            }else{
//                                if((((A[k] >> l) & 1) == 1)){
//                                    C[k] &= ~(1 << l);
//                                    previousNum = 1;
//                                }else if ((((A[k] >> l) & 1) == 0)){
//                                    C[k] |= (1 << l);
//                                    previousNum = 0;
//                                }
//                            }
//                        }
//                    }
//                    for(int i = 0; i <64; i++){
//                        c = c + ((int)C[i] & 0xFF);
//                    }
//                    
//                    System.out.println();
                    for(int i = 0; i < 64; i++){
                        //c = c + ((int)is.read() & 0xFF);
                        c = c + is.read();
                    }
                    
                    c = (double)(c/64);
                    
                    System.out.printf("Baseline Established: %.2f" + "\n", c);
                    
                    System.out.print("INTAKE" + "\n");
                    for(int j = 0; j < 320; j++){
                        a = (byte)is.read();
                        b[j] = a;
                        //String s1 = String.format("%8s", Integer.toBinaryString(((int)b[j]) & 0xFF)).replace(' ', '0');
                        System.out.println((((int)b[j]) & 0xFF) + " ");
                        //System.out.print(String.format("%x", a) + " ");
                    }
                    System.out.println();
                    for(int m = 0; m < 320; m = m + 5){
                        a = 0;
                        
                        for(int n = 0; n < 5; n++){
                            if((((int)b[(m + n)]) & 0xFF) >= c){
                                a |= (byte) (1 << (4 - n));
                            }else{
                                a &= (byte) ~(1 << (4 - n));
                            }
                        }
                        fiveB[m/5] = (byte)a;
                    }                    
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

                    ReverseNRZI(fiveB, 64, 5, A);
//                    for(int k = 0; k < 320; k++){
//                        previousNum = 0;
//                        for(int l = 7; l >= 0; l--){
//                            if(previousNum == 0){
//                                if((((b[k] >> l) & 1) == 1)){
//                                    converted[k] |= (1 << l);
//                                    previousNum = 1;
//                                }else if ((((b[k] >> l) & 1) == 0)){
//                                    converted[k] &= ~(1 << l);
//                                    previousNum = 0;
//                                }
//                            }else{
//                                if((((b[k] >> l) & 1) == 1)){
//                                    converted[k] &= ~(1 << l);
//                                    previousNum = 1;
//                                }else if ((((b[k] >> l) & 1) == 0)){
//                                    converted[k] |= (1 << l);
//                                    previousNum = 0;
//                                }
//                            }
//                        }
//                    }
//                    
//                    System.out.println();
                    
                    
//                    System.out.print("CONVERTED: " + "\n");
//                    for(int j = 0; j < 320; j++){
//                        String s2 = String.format("%8s", Integer.toBinaryString(((int)converted[j]) & 0xFF)).replace(' ', '0');
//                        System.out.println(s2 + " ");
//                        //System.out.print(String.format("%x", a) + " ");
//                    }
//                    System.out.println();
                    
//                    for(int m = 0; m < 320; m = m + 5){
//                        a = 0;
//                        
//                        for(int n = 0; n < 5; n++){
//                            if((((int)converted[(m + n)]) & 0xFF) >= c){
//                                a |= (byte) (1 << (4 - n));
//                            }else{
//                                a &= (byte) ~(1 << (4 - n));
//                            }
//                        }
//                        fiveB[m/5] = (byte)a;
//                    }
                    
                    
                    System.out.println("\nA CONVERTEDS:");
                    for(int o = 0; o < 64; o++){

                        String s3 = String.format("%8s", Integer.toBinaryString(((int)A[o]) & 0xFF)).replace(' ', '0');
                        System.out.println(s3 + " ");
                    }
                    

                    System.out.print("Good? ");
                    os.write(fiveB);
                    System.out.print(is.read());

                    //System.out.print(String.format("%x", a) + " ");
                    }
        }
        
        public static void ReverseNRZI(byte[] in, int size, int widthSize, byte[] out){
            int previousNum = 0;
            System.out.println("REVERSE NRZI");
            
                    for(int k = 0; k < size; k++){

                        for(int p = (widthSize-1); p >= 0; p--){
                            if(previousNum == 0){
                                if       ((((in[k] >> p) & 1) == 1)){
                                    out[k] |= (1 << p);
                                    previousNum = 1;
                                }else if ((((in[k] >> p) & 1) == 0)){
                                    out[k] &= ~(1 << p);
                                    previousNum = 0;
                                }
                            }else{
                                if       ((((in[k] >> p) & 1) == 1)){
                                    out[k] &= ~(1 << p);
                                    previousNum = 1;
                                }else if ((((in[k] >> p) & 1) == 0)){
                                    out[k] |= (1 << p);
                                    previousNum = 0;
                                }
                            }
                        }
                    }
            //return out;
        }
        public static void DecodeNRZI(byte[] in, int size, int widthSize, int[] out){
                        int previousNum = 0;
            System.out.println("REVERSE NRZI");
            
                    for(int k = 0; k < size; k++){

                        for(int p = (widthSize-1); p >= 0; p--){
                            if(previousNum == 0){
                                if       ((((in[k] >> p) & 1) == 1)){
                                    out[k] |= (1 << p);
                                    previousNum = 1;
                                }else if ((((in[k] >> p) & 1) == 0)){
                                    out[k] &= ~(1 << p);
                                    previousNum = 0;
                                }
                            }else{
                                if       ((((in[k] >> p) & 1) == 1)){
                                    out[k] &= ~(1 << p);
                                    previousNum = 1;
                                }else if ((((in[k] >> p) & 1) == 0)){
                                    out[k] |= (1 << p);
                                    previousNum = 0;
                                }
                            }
                        }
                    }
        }
}

//import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kevin
 */
public class ReadFileEx {

    String s = "scaya makan";

    public void readMyMap() {

        int y = 0;
        try {
            //File file = new File("map.txt");
            FileReader fileReader = new FileReader("map.txt");
            BufferedReader br = new BufferedReader(fileReader);
            while (null != (s = br.readLine())) {
                System.out.println(" " + s);
                y++;
                if (y == 5) {
                    //string
                    StringTokenizer tokenizer = new StringTokenizer("Hi My name is Rain!", "m");
                    while (tokenizer.hasMoreTokens()) {
                        System.out.println("n: " + tokenizer.countTokens());
                        System.out.println("" + tokenizer.nextToken());
                    }
                    StringTokenizer st = new StringTokenizer(s, ",");
                    int n = st.countTokens();
//                    st.
                    System.out.println("n: " + n);

                }
            }

        } catch (Exception ex) {
            Logger.getLogger(ReadFileEx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void funString() {
        s = "saya makan nasi sampe";
        StringTokenizer st = new StringTokenizer(s, "a");
        int a = st.countTokens();
        System.out.println("a; " + a);
        while(st.hasMoreTokens()){
            s = st.nextToken();
            System.out.println("s: " + s);

        }
        System.out.println("a: " + a);
    }

    public ReadFileEx() {
        //readMyMap();
        funString();
    }

    public static void main(String[] args) {
        ReadFileEx ex = new ReadFileEx();
    }
}

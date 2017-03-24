import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 24.03.2017.
 */
public class loc {
    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(80);

        while (true) {
            Socket s = ss.accept();
            Runnable action = new Runnable() {
                @Override
                public void run() {
                    handle(s);
                }
            };
            new Thread(action);
        }
    }

    static void handle(Socket s)  {
        try {
            try {
               // Thread.sleep(10000);
                BufferedReader rdr = new BufferedReader(new InputStreamReader(s.getInputStream(), "US-ASCII"));
                Pattern p= Pattern.compile("GET \\s +(\\S+)");

                while (true) {
                    String line = rdr.readLine();
                    if (line == null|| line.isEmpty())
                        break;
                    Matcher m = p.matcher(line);
                    if (m.find()){
                        String resource =m.group(1);
                        System.out.println(resource);
                    }

                }
                OutputStream os = s.getOutputStream();
                os.write("Hello World".getBytes("US-ASCII"));
            } finally {
                s.close();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}

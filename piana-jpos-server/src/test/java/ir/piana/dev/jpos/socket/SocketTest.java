package ir.piana.dev.jpos.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Mohammad Rahmati, 6/7/2017 2:57 PM
 */
public class SocketTest {
    public static void main(String[] args)
            throws IOException {
        Socket pos;
        pos = new Socket("localhost", 9000);
        OutputStream pOS = pos.getOutputStream();
        InputStream pIS = pos.getInputStream();
        pOS.write("1192".getBytes());
        System.out.println();
        pos.close();
    }
}

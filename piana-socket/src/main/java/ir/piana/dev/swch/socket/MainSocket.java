package ir.piana.dev.swch.socket;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.BitSet;

/**
 * Created by SYSTEM on 8/6/2017.
 */
public class MainSocket {
    public static void main(String[] args) {
        Socket MyClient = null;
        try {
            MyClient = new Socket("localhost", 6001);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        DataInputStream input = null;
        DataOutputStream output = null;
        try {
//            input = new DataInputStream(MyClient.getInputStream());
            output = new DataOutputStream(MyClient.getOutputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(new byte[] {48, 48, 49, 51});
            baos.write(new byte[] {2, 0});
//            output.write("0008".getBytes());
            baos.write(new byte[] {32, 0, 0, 0, 0, 0, 0, 0});
            baos.write(new byte[] {49, 0, 0});
            byte[] bytes = new byte[8];
            bytes = baos.toByteArray();
            output.write(bytes);
            byte[] inBytes = new byte[1024];
            int readed = input.read(inBytes);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}

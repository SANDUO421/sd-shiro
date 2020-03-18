package com.yulin.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketTest {

    public static void main(String[] args) {
        try(Socket socket=new Socket("localhost",12345);BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            while (true){
                String s = bufferedReader.readLine();
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

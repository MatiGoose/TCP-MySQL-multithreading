package com.pack;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;




public class server
{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // server is listening on port 5056
        ServerSocket ServerSocket = new ServerSocket(1111);
        Class.forName("com.mysql.jdbc.Driver");
        // running infinite loop for getting
        // client request
        while (true)
        {
            Socket recievedSocket = null;
            try
            {
                // socket object to receive incoming client requests
                recievedSocket = ServerSocket.accept();

                System.out.println("A new client is connected : " + recievedSocket);

                // obtaining input and out streams
                ObjectInputStream is = new ObjectInputStream(recievedSocket.getInputStream());
                ObjectOutputStream os = new ObjectOutputStream(recievedSocket.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ClientHandler(recievedSocket, os, is);

                // Invoking the start() method
                t.run();

            }
            catch (Exception e)
            {
                ServerSocket.close();
                e.printStackTrace();
            }
        }
    }
}


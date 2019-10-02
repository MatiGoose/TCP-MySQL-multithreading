package com.pack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class client
{
    private Socket clientSocket;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    public void startConnection(String ip, int port) throws IOException
    {
        clientSocket = new Socket(ip, port);
        os = new ObjectOutputStream(clientSocket.getOutputStream());
        is = new ObjectInputStream(clientSocket.getInputStream());
    }
    public void sendToServer(machine machine) throws IOException
    {
        os.writeObject(machine);
    }
    public String getFromServer() throws IOException, ClassNotFoundException
    {
        return (String)is.readObject();
    }
    public void disconnect() throws IOException
    {
        os.close();
        clientSocket.close();
    }
}

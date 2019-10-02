package com.pack;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

// ClientHandler class
class ClientHandler extends Thread
{
    private static final String url = "jdbc:mysql://localhost:3306/business?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "1311";


    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    final Socket socket;
    ObjectInputStream is;
    ObjectOutputStream os;
    // Constructor
    public ClientHandler(Socket socket, ObjectOutputStream os, ObjectInputStream is)
    {
        this.os = os;
        this.is = is;
        this.socket = socket;
    }

    @Override
    public void run()
    {
        String query = new String("");
        while(socket.isConnected())
        {
            machine machine = null;
            try
            {
                machine = (machine)is.readObject();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            query = "insert into business.militarymachine (name, type, crediting_date, people_capacity, ammunition) values ('" + machine.name + "', '" + machine.type + "', '" + machine.crediting_time + "', " + machine.people_capacity + ", '" + machine.ammunition + "');";
            try
            {
                //os = new ObjectOutputStream(recievedSocket.getOutputStream());
                connection = DriverManager.getConnection(url, user, password);
                statement = connection.createStatement();
                statement.executeUpdate(query);
                //Statement newSt = connection.createStatement();

                //ResultSet rs = newSt.executeQuery("select * from business.militarymachine where name = 'T34'");
                //String str = "";
                //String str2 = "";
                //while(rs.next())
                //{
                //    str = rs.getString("name");
                //    str2 = rs.getString("type");
                //    //str += "\n";
                //    os.writeObject("Name: " + str + "Type: " + str2);
                //}
                os.writeObject("done");
            }
            catch (SQLException  e)
            {
                try
                {
                    os.writeObject("Error");
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
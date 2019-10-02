package com.pack;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.ParseException;

public class window extends JFrame
{
    JLabel name = new JLabel("Machine");
    JLabel type = new JLabel("Type");
    JLabel ammunition = new JLabel("Ammunition");
    JLabel capacity = new JLabel("Capacity");
    JLabel crediting_date = new JLabel("Crediting Date");

    JLabel result = new JLabel("");

    JTextField textName = new JTextField("");
    JTextField textType = new JTextField("");
    JTextField textAmmunition = new JTextField("");
    JTextField textPeopleCapacity  = new JTextField("");
    MaskFormatter dateFormat = new MaskFormatter("##.##.####");
    JFormattedTextField textCreditingDate = new JFormattedTextField(dateFormat);
    JButton addButton = new JButton("Add machine");

    client client = new client();

    public window() throws ParseException, IOException
    {
        super("Military Machine");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        JPanel panel = new JPanel();
        panel.setLayout(null);


        name.setSize(120,20);
        name.setLocation(10, 10);
        textName.setSize(120,20);
        textName.setLocation(10,40);
        panel.add(textName);
        panel.add(name);

        type.setSize(120,20);
        type.setLocation(10, 70);
        textType.setSize(120,20);
        textType.setLocation(10,100);
        panel.add(textType);
        panel.add(type);

        ammunition.setSize(120,20);
        ammunition.setLocation(10, 130);
        textAmmunition.setSize(120,20);
        textAmmunition.setLocation(10,160);
        panel.add(textAmmunition);
        panel.add(ammunition);

        capacity.setSize(120,20);
        capacity.setLocation(10, 190);
        textPeopleCapacity.setSize(120,20);
        textPeopleCapacity.setLocation(10,220);
        panel.add(textPeopleCapacity);
        panel.add(capacity);


        crediting_date.setSize(120,20);
        crediting_date.setLocation(10, 250);
        dateFormat.setPlaceholderCharacter('_');
        textCreditingDate.setSize(120,20);
        textCreditingDate.setLocation(10,280);
        panel.add(textCreditingDate);
        panel.add(crediting_date);

        result.setSize(120,20);
        result.setLocation(150, 40);
        panel.add(result);

        addButton.setSize(120,20);
        addButton.setLocation(150, 10);
        addButton.addActionListener(new OurActionListener());
        panel.add(addButton);

        setContentPane(panel);
        panel.setSize(400, 400);


        this.addWindowStateListener(
                new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        try
                        {
                            client.disconnect();
                        }
                        catch (IOException ex)
                        {
                            ex.printStackTrace();
                        }
                        System.exit(0);
                    }

                });
        client.startConnection("127.0.0.1", 1111);
    }
    public class OurActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //add to server
            try
            {
                client.sendToServer(new machine(textName.getText(), textType.getText(), textCreditingDate.getText(), Integer.parseInt(textPeopleCapacity.getText()), textAmmunition.getText()));
                String str = "";
                str = client.getFromServer();
                //while(!"end".equals(temp))
                //{
                 //   temp = client.getFromServer();
                  //  str += temp + "\n";
                //}
                result.setText(str);
                //result.setText((String)client.getFromServer());
            }
            catch (IOException | ClassNotFoundException ex)
            {
                ex.printStackTrace();
            }
        }
    }

}

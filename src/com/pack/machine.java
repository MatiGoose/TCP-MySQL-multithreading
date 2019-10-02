package com.pack;

public class machine implements java.io.Serializable
{
    public String name;
    public String type;
    public String crediting_time;
    public int people_capacity;
    public String ammunition;
    public machine(String name, String type, String crediting_time, int people_capacity, String ammunition)
    {
        this.name = name;
        this.type = type;
        this. crediting_time = crediting_time;
        this.people_capacity = people_capacity;
        this.ammunition = ammunition;
    }
}

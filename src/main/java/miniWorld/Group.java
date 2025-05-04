package main.java.miniWorld;

import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class Group{
    public List<Partical> list;
    public int size;

    Group(int size, Shap c, Color color,int radius, int wind_width, int wind_height)
    {
        if (size <= 0)
            throw new RuntimeException("Group constructor with Size < 0");
        if (radius <= 0)
            throw new RuntimeException("Group constructor with Size < 0");
        list = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {
            int x = (int)(Math.random()*10000)%wind_width;
            int y = (int)(Math.random()*10000)%wind_height;
            list.add(new Partical(x, y, radius, c, color));
        }
    }

    Group(int size, Shap c, Color color,int radius)
    {
        if (size <= 0)
            throw new RuntimeException("Group constructor with Size <= 0");
        if (radius <= 0)
            throw new RuntimeException("Group constructor with Raduis <= 0");
        list = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {
            int x = (int)(Math.random()*10000);
            int y = (int)(Math.random()*10000);
            list.add(new Partical(x, y, radius, c, color));
        }
    }

    Group(int size, Shap c, int width, int height)
    {
        if (size <= 0)
        throw new RuntimeException("Group constructor with Size <= 0");
        if (width <= 0 || height<=0)
            throw new RuntimeException("Group constructor with Width or Height <= 0");
        list = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {
            int x = (int)(Math.random()*10000);
            int y = (int)(Math.random()*10000);
            list.add(new Partical(x, y, width, height, c));
        }
    }

    void add(Partical p)
    {
        list.add(p);
    }

    void drawGroup(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < list.size(); i++){
            ParticleRenderer.draw(g2d, list.get(i));
        }
    }

    void gravity_dekey(float dekey_fact)
    {
        for (int i = 0; i < list.size(); i++)
        {
            list.get(i).pos.vx *= dekey_fact;
            list.get(i).pos.vy *= dekey_fact;
        }
    }
}

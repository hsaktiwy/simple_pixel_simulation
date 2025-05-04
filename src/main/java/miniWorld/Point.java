package main.java.miniWorld;

public class Point {
    public float x;
    public float y;
    public float vx;
    public float vy;
    public float ax;
    public float ay;

    public Point(float _x, float _y){
        x = _x;
        y = _y;
        vx = 0;
        vy = 0;
        ax = 0;
        ay = 0;
    }

    public void move(float dt){
        x += vx * dt;
        y += vy * dt;
    }
}

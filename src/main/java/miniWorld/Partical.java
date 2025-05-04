package main.java.miniWorld;
import java.awt.Color;

enum Shap{
    CIRCLE,
    RECTANGLE,
    TRIANGLE,
    POLYGON,
    LINE,
    ELLIPSE,
    POINT,
    ARC,
    BEZIER,
    SPLINE,
    PATH
}


public class Partical {
    public Point pos;
    public Shap shap;
    public Color color;
    public int radius;
    public int width;
    public int height;

    Partical(float _x, float _y, Shap _shap){
        pos = new Point(_x, _y);
    }

    

    Partical(float _x, float _y, int _radius, Shap _shap){
        pos = new Point(_x, _y);
        radius = _radius;
        shap = _shap;
    }

    Partical(float _x, float _y, int _radius, Shap _shap, Color _color){
        pos = new Point(_x, _y);
        radius = _radius;
        shap = _shap;
        color = _color;
    }

    Partical(float _x, float _y, float _vx, float _vy, Shap _shap){
        pos = new Point(_x, _y);
        pos.vx = _vx;
        pos.vy = _vy;
        shap = _shap;
    }
}

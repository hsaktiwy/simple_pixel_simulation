package main.java.miniWorld;
import java.awt.*;
import java.awt.geom.*;

public class ParticleRenderer {

    public static void drawBackGround(Graphics2D g2d, Color color,int width , int height)
    {
        g2d.setColor(color);
        g2d.fill(new Rectangle2D.Float(0, 0, width, height));
    }
    public static void draw(Graphics2D g2d, Partical p) {
        g2d.setColor(p.color);
        float x = p.pos.x;
        float y = p.pos.y;
        float radius = p.radius;
        //System.out.println(x + " " + y + " " + p.radius);
        switch (p.shap) {
            case CIRCLE:
                g2d.fill(new Ellipse2D.Float(x - radius/2, y - radius/2, radius, radius));
                break;
            case RECTANGLE:
                g2d.fill(new Rectangle2D.Float(x - 10, y - 10, 20, 20));
                break;
            case TRIANGLE:
                Polygon triangle = new Polygon();
                triangle.addPoint((int) x, (int) (y - 10));
                triangle.addPoint((int) (x - 10), (int) (y + 10));
                triangle.addPoint((int) (x + 10), (int) (y + 10));
                g2d.fillPolygon(triangle);
                break;
            case POLYGON:
                // Example: Hexagon
                Polygon hexagon = new Polygon();
                for (int i = 0; i < 6; i++) {
                    double angle = Math.toRadians((60 * i) - 30);
                    int px = (int) (x + 10 * Math.cos(angle));
                    int py = (int) (y + 10 * Math.sin(angle));
                    hexagon.addPoint(px, py);
                }
                g2d.fillPolygon(hexagon);
                break;
            case LINE:
                g2d.draw(new Line2D.Float(x - 10, y - 10, x + 10, y + 10));
                break;
            case ELLIPSE:
                g2d.fill(new Ellipse2D.Float(x - 15, y - 10, 30, 20));
                break;
            case POINT:
                g2d.fillRect((int) x, (int) y, 1, 1);
                break;
            case ARC:
                g2d.draw(new Arc2D.Float(x - 10, y - 10, 20, 20, 0, 180, Arc2D.OPEN));
                break;
            case BEZIER:
                CubicCurve2D bezier = new CubicCurve2D.Float(x - 10, y, x - 5, y - 15, x + 5, y + 15, x + 10, y);
                g2d.draw(bezier);
                break;
            case SPLINE:
                QuadCurve2D spline = new QuadCurve2D.Float(x - 10, y, x, y - 15, x + 10, y);
                g2d.draw(spline);
                break;
            case PATH:
                GeneralPath path = new GeneralPath();
                path.moveTo(x - 10, y);
                path.lineTo(x, y - 10);
                path.lineTo(x + 10, y);
                path.lineTo(x, y + 10);
                path.closePath();
                g2d.fill(path);
                break;
            default:
                // Handle unknown shapes
                break;
        }
    }
}

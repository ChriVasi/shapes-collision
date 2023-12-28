import javax.swing.*;
import java.awt.*;

public class rectangle_ellipse_polygon extends JFrame {

    public rectangle_ellipse_polygon() {
        setTitle("Drawing Shapes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the JFrame
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw Rectangle
        g.drawRect(50, 50, 100, 75);

        // Draw Ellipse
        g.drawOval(200, 50, 100, 75);

        // Draw Polygon (Triangle)
        int[] xPoints = {350, 300, 400};
        int[] yPoints = {50, 125, 125};
        g.drawPolygon(xPoints, yPoints, 3);

        // Draw Circle
        g.drawOval(50, 200, 100, 100);

        // Draw Hexagon
        int[] xHexagon = {200, 250, 350, 400, 350, 250};
        int[] yHexagon = {200, 150, 150, 200, 250, 250};
        g.drawPolygon(xHexagon, yHexagon, 6);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new rectangle_ellipse_polygon(); // Corrected class name
        });
    }
}

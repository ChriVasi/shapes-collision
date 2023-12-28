import javax.swing.*;
import java.awt.*;

public class Star extends JFrame {

    public Star() {
        setTitle("Drawing Star");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the JFrame

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int numPoints = 5;
        int[] xPoints = new int[numPoints * 2];
        int[] yPoints = new int[numPoints * 2];

        double angle = Math.PI / numPoints;
        double rotation = Math.PI / 2;

        for (int i = 0; i < numPoints * 2; i++) {
            double factor = (i % 2 == 0) ? 1.0 : 0.5; // Alter the radius every other point
            xPoints[i] = (int) (centerX + Math.cos(rotation) * 50 * factor);
            yPoints[i] = (int) (centerY + Math.sin(rotation) * 50 * factor);
            rotation += angle;
        }

        g.drawPolygon(xPoints, yPoints, numPoints * 2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Star();
        });
    }
}

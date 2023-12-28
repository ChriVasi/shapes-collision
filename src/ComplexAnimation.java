import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DrawingPanel extends JPanel {

    private int ballX = 100;
    private int ballY = 100;
    private int ballSize = 30;
    private int ballSpeedX = 5;
    private int ballSpeedY = 5;

    private int rectX = 200;
    private int rectY = 200;
    private int rectWidth = 50;
    private int rectHeight = 30;
    private double rectAngle = 0;
    private double rectAngularSpeed = 0.02;
    private int rectSpeedX = 2;
    private int rectSpeedY = 2;

    public DrawingPanel() {
        setDoubleBuffered(false);

        Timer timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBallPosition();
                updateRectPosition();
                repaint();
            }
        });

        timer.start();
    }

    private void updateBallPosition() {
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Bounce off the walls
        if (ballX <= 0 || ballX + ballSize >= getWidth()) {
            ballSpeedX = -ballSpeedX;
        }

        if (ballY <= 0 || ballY + ballSize >= getHeight()) {
            ballSpeedY = -ballSpeedY;
        }

        // Check for collision with rectangle
        if (ballX + ballSize >= rectX && ballX <= rectX + rectWidth &&
                ballY + ballSize >= rectY && ballY <= rectY + rectHeight) {
            // Change directions for the ball
            ballSpeedX = -ballSpeedX;
            ballSpeedY = -ballSpeedY;
        }
    }

    private void updateRectPosition() {
        rectX += rectSpeedX;
        rectY += rectSpeedY;

        // Bounce off the walls for the rectangle
        if (rectX <= 0 || rectX + rectWidth >= getWidth()) {
            rectSpeedX = -rectSpeedX;
        }

        if (rectY <= 0 || rectY + rectHeight >= getHeight()) {
            rectSpeedY = -rectSpeedY;
        }

        rectAngle += rectAngularSpeed;

        // Adjusted to make the rectangle rotate continuously
        if (rectAngle > 2 * Math.PI) {
            rectAngle -= 2 * Math.PI;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBall(g);
        drawRotatingRect(g);
    }

    private void drawBall(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(ballX, ballY, ballSize, ballSize);
    }

    private void drawRotatingRect(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.RED);
        g2d.translate(rectX + rectWidth / 2, rectY + rectHeight / 2);
        g2d.rotate(rectAngle);
        g2d.fillRect(-rectWidth / 2, -rectHeight / 2, rectWidth, rectHeight);
        g2d.dispose();
    }
}

public class ComplexAnimation extends JFrame {

    public ComplexAnimation() {
        setTitle("Complex Animation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new DrawingPanel());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ComplexAnimation();
        });
    }
}

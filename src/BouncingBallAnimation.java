import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BouncingBallAnimation extends JFrame {

    private int ballX = 200;
    private int ballY = 50;
    private int ballSize = 20;
    private int xSpeed = 0;
    private int ySpeed = 1;

    public BouncingBallAnimation() {
        setTitle("Bouncing Ball Animation");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBall();
                repaint();
            }
        });

        timer.start();
        setVisible(true);
    }

    private void moveBall() {
        ballX += xSpeed;
        ballY += ySpeed;

        // Bounce off the top and bottom walls
        if (ballY + ballSize > getHeight() || ballY < 0) {
            ySpeed = -ySpeed;
        }
    }

    @Override
    public void paint(Graphics g) {
        // Clear the frame with a background color
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw the ball
        g.setColor(Color.BLUE);
        g.fillOval(ballX, ballY, ballSize, ballSize);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BouncingBallAnimation();
        });
    }
}

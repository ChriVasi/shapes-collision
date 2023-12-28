import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PaintApplication extends JFrame {

    private List<Shape> shapes;
    private Timer animationTimer;
    private int animationSpeed = 5;

    public PaintApplication() {
        setTitle("Paint Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        shapes = new ArrayList<>();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Create a new line starting from the mouse click point
                shapes.add(new Line(PaintApplication.this, e.getX(), e.getY(), e.getX(), e.getY()));
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Update the end point of the current line during dragging
                if (!shapes.isEmpty() && shapes.get(shapes.size() - 1) instanceof Line) {
                    Line currentLine = (Line) shapes.get(shapes.size() - 1);
                    currentLine.setEndX(e.getX());
                    currentLine.setEndY(e.getY());
                    repaint();
                }
            }
        });

        JButton animateButton = new JButton("Animate");
        animateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAnimation();
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapes.clear();
                stopAnimation();
                repaint();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(animateButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void startAnimation() {
        animationTimer = new Timer(animationSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveShapes();
                repaint();
            }
        });

        animationTimer.start();
    }

    private void stopAnimation() {
        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }
    }

    private void moveShapes() {
        for (Shape shape : shapes) {
            shape.move();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    // Define a generic Shape interface
    interface Shape {
        void draw(Graphics g);

        void move();
    }

    // Implementation of a Line shape
    static class Line implements Shape {
        private int startX, startY, endX, endY;
        private PaintApplication paintApplication;

        public Line(PaintApplication paintApplication, int startX, int startY, int endX, int endY) {
            this.paintApplication = paintApplication;
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }

        public void setEndX(int endX) {
            this.endX = endX;
        }

        public void setEndY(int endY) {
            this.endY = endY;
        }

        @Override
        public void draw(Graphics g) {
            g.drawLine(startX, startY, endX, endY);
        }

        @Override
        public void move() {
            // Move the line horizontally
            startX += 5;
            endX += 5;

            // Wrap around to the left when reaching the right edge
            if (startX > paintApplication.getWidth()) {
                startX = -20;
                endX = startX + 20;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PaintApplication();
        });
    }
}

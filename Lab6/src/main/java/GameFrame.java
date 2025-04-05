import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Dot Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel configurationPanel = new JPanel();
        configurationPanel.add(new JLabel("Number of Dots: "));
        JTextField nrDotsInput = new JTextField(5);
        configurationPanel.add(nrDotsInput);
        JButton newGameButton = new JButton("New Game");
        configurationPanel.add(newGameButton);
        add(configurationPanel, BorderLayout.NORTH);


        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillOval(100, 100, 10, 10); // Example dot
            }
        };
        canvas.setPreferredSize(new Dimension(400, 400));
        add(canvas, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JButton("Load"));
        controlPanel.add(new JButton("Save"));
        controlPanel.add(new JButton("Exit"));
        add(controlPanel, BorderLayout.SOUTH);
    }
}
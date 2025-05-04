package main.java.miniWorld;
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.concurrent.ThreadLocalRandom;

class MyCanva extends JPanel implements ActionListener {
    private Timer timer;
    static final int maxWidth = 1400;
    static final int maxHeight = 720;
    static private List<Group> colonies;

    public MyCanva() {
        // this where we init our Objects
        colonies = new ArrayList<>();
        timer = new Timer(30, this); // Update every 20 milliseconds
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ParticleRenderer.drawBackGround((Graphics2D)g, Color.BLACK, maxWidth, maxHeight);
        for (Group group: colonies)
        {
            group.drawGroup(g);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Rules.applayAll(); // Apply all rules
        repaint(); // Request a repaint
    }

    private static void showTemporaryMessage(JLabel label, String message, Color color, int durationMs) {
        label.setForeground(color);
        label.setText(message);
        new javax.swing.Timer(durationMs, e -> label.setText(" ")).start();
    }

    private static void showTemporaryMessage(JLabel label, String message, int durationMs) {
        label.setText(message);
        new javax.swing.Timer(durationMs, e -> label.setText(" ")).start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MiniWorld");

        // lable to show status
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.DARK_GRAY);
        // Button to add a random group
        JButton AddGroup = new JButton("Add Random Group");
        JButton ResetRule = new JButton("Reset Rules");
        ResetRule.addActionListener(e -> {
            Rules.clearRules();
            showTemporaryMessage(statusLabel, "Rules cleared.", Color.RED, 2000);
        });

        AddGroup.addActionListener(e -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int size = random.nextInt(50, 1000); // Random size between 50 and 200
            // Color color = COLORS[random.nextInt(COLORS.length)];
            Color color2 = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            Group blue = new Group(size, Shap.CIRCLE, color2,4, maxWidth, maxHeight);
            colonies.add(blue);
            showTemporaryMessage(statusLabel, "Created new Group with color (size: "+size+")", Color.RED, 2000);
        });

        // Button to creaat rules
        JButton AddRules = new JButton("Add Rules");
        AddRules.addActionListener(e -> {
            ThreadLocalRandom rand = ThreadLocalRandom.current();
            if (colonies.size() < 2) {
                System.out.println("Not enough colonies to apply rules.");
                showTemporaryMessage(statusLabel, "Not enough colonies to apply rules.", 2000);
                return;
            }
            int fromIndex = rand.nextInt(colonies.size());
            int toIndex = rand.nextInt(colonies.size());
            int gravitySide = rand.nextInt(0, 2); // 0 for negative, 1 for positive
            float gravity = (float)(Math.random()*(gravitySide == 0 ? 1f : -1f)); // -0.5 to +0.5
            int minDist = rand.nextInt(0, 5); // min distance: 0–50
            int maxDist = rand.nextInt(50, 150); // max distance: 50–150
    
            Rules.addRule(
                colonies.get(fromIndex),
                colonies.get(toIndex),
                gravity,
                minDist,
                maxDist,
                maxHeight,
                maxWidth
            );
            showTemporaryMessage(statusLabel, "Rules Added.", Color.RED, 2000);
        });

        JButton KillAll = new JButton("Kill All");
        KillAll.addActionListener(e -> {
            colonies.clear();
            Rules.clearRules();
            showTemporaryMessage(statusLabel, "All groups killed and Rules Cleared.", Color.RED, 4000);
        });
        JPanel topPanel = new JPanel();
        topPanel.add(AddGroup);
        topPanel.add(AddRules);
        topPanel.add(ResetRule);
        topPanel.add(KillAll);
        MyCanva animation = new MyCanva();
        // adding components to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(animation, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH); // Status bar

        // Set up the frame
        frame.setSize(maxHeight, maxWidth);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

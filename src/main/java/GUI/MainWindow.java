package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MainWindow {

    public static void createWindow() {
        JFrame jFrame = new JFrame("SortingVisually");
        jFrame.setPreferredSize(new Dimension(1030, 600));
        jFrame.setResizable(false);
        jFrame.getContentPane().setBackground(Color.DARK_GRAY);
        jFrame.pack();

        GraphicsPanel graphicsPanel = new GraphicsPanel();

        jFrame.add(graphicsPanel);

        jFrame.setVisible(true);

    }

}

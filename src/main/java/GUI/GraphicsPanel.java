package GUI;

import Util.Action;
import Util.ArrayCreator;
import Util.Pair;

import javax.swing.*;
import javax.swing.plaf.SeparatorUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Queue;
import java.util.Random;

import static java.lang.Thread.sleep;

public class GraphicsPanel extends JPanel {


    private volatile int[] arr;
    private volatile int firstSelected = -1;
    private volatile int secondSelected = -1;
    private volatile boolean isRunningSort = false;

    private volatile Queue<Action> bubbleSortingData;
    private volatile Queue<Action> selectionSortingData;
    private volatile Queue<Action> combSortingData;
    private volatile Label durationInfo;

    public GraphicsPanel() {
        arr = ArrayCreator.createArray();

        Button btnBubbleSort = new Button("Bubble Sort");
        btnBubbleSort.addActionListener(e -> {
            if (!isRunningSort)
                bubbleSortShow();
        });
        this.add(btnBubbleSort);

        Button btnSelectionSort = new Button("Selection Sort");
        btnSelectionSort.addActionListener(e -> {
            if (!isRunningSort)
                selectionSortShow();
        });
        this.add(btnSelectionSort);

        Button btnCombSort = new Button("Comb Sort");
        btnCombSort.addActionListener(e -> {
            if (!isRunningSort)
                combSortShow();
        });
        this.add(btnCombSort);

        Button btnShuffle = new Button("Shuffle Array");
        btnShuffle.addActionListener(e -> {
            if (!isRunningSort) {
                arr = ArrayCreator.shuffleArray(arr);
                repaint();
            }
        });
        this.add(btnShuffle);

        durationInfo = new Label("Duration: .........ms  Replaces: ......  Speed: 1/7000x");
        this.add(durationInfo);

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        Graphics2D g2  = (Graphics2D) g;
        for (int i = 0; i < arr.length; i++) {

            if (i == firstSelected) {
                g2.setColor(Color.RED);
            } else if (i == secondSelected) {
                g2.setColor(Color.GREEN);
            } else {
                g2.setColor(Color.BLACK);
            }
            Rectangle rectangle = new Rectangle(i*10, 560 - arr[i]*5 , 10, arr[i]*5);
            g2.draw(rectangle);
            g2.fill(rectangle);
        }

        firstSelected = -1;
        secondSelected = -1;
    }

    public void bubbleSortShow() {
        bubbleSortingData = ArrayCreator.bubbleSorting(Arrays.copyOf(arr, arr.length));
        ShowSorting newThread = new ShowSorting(bubbleSortingData);
        newThread.start();
    }

    public void selectionSortShow() {
        selectionSortingData = ArrayCreator.selectionSorting(Arrays.copyOf(arr, arr.length));
        ShowSorting newThread = new ShowSorting(selectionSortingData);
        newThread.start();
    }

    public void combSortShow() {
        combSortingData = ArrayCreator.combSort(Arrays.copyOf(arr, arr.length));
        ShowSorting newThread = new ShowSorting(combSortingData);
        newThread.start();
    }

    class ShowSorting extends Thread {

        private Queue<Action> sortingData;

        ShowSorting(Queue<Action> sortingData) {
            super();
            this.sortingData = sortingData;
        }

        @Override
        public void run() {
            isRunningSort = true;
            Action firstAction = sortingData.poll();

            double durationMilli = (double)firstAction.getDuration() / 1000000;
            double delay = durationMilli * 7000 / firstAction.getSize();
            durationInfo.setText("Duration: " + (durationMilli) + "ms \n Replaces: " + firstAction.getSize() + " Speed: 1/7000x");

            while (!firstAction.isEmpty()) {
                Pair nextPair = firstAction.getPair();
                firstSelected = nextPair.getFirst();
                secondSelected = nextPair.getSecond();
                int tmp = arr[nextPair.getFirst()];
                arr[nextPair.getFirst()] = arr[nextPair.getSecond()];
                arr[nextPair.getSecond()] = tmp;

                try {
                    sleep((int)delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                repaint();
            }
            isRunningSort = false;
        }
    }


}

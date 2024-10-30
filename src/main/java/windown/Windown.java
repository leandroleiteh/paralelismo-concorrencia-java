package windown;


import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Windown {

    private static JFrame jFrame;

    public static synchronized Message createWindown(String startText) {
        jFrame = new JFrame("Contador");
        Message jLabel = new Message();
        jLabel.setText("<html><body>" + startText + "</body></html>");
        jLabel.setFont(new Font("Serif", Font.ROMAN_BASELINE, 72));
        JPanel jPanel = new JPanel();
        jPanel.add(jLabel);
        jFrame.add(jPanel);
        jFrame.setSize(800, 600);
        jFrame.setLocation(1000, 200);
        jFrame.setVisible(true);
        return jLabel;
    }

    public static void closed() {
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static final void monitor(Supplier<String> supplier) {
        Message msg = Windown.createWindown("Contador");
        Runnable monitor = () -> {
            msg.setText(supplier.get());
            closed();
        };
        var executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(monitor, 0, 50, TimeUnit.MILLISECONDS);
    }

    public static class Message extends JLabel {
        private static final long serialVersionUID = 1L;

        @Override
        public void setText(String text) {
            super.setText("<html><body><p style=\"width:400px\">" + text + "</p></body></html>");
        }
    }
}


package src;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new src.ToDoListGui().setVisible(true);
            }
        });
    }
}

package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TaskComponent extends JPanel implements ActionListener {
    private JCheckBox checkBox;
    private JTextPane taskfield;
    private JButton deletebutton;

    public JTextPane getTaskfield() {
        return taskfield;

    }

    //this panel is used so that we can make updates to the task component panel when deleting task
    private JPanel parentpanel;

    public TaskComponent(JPanel parentpanel) {
        this.parentpanel = parentpanel;

        //task field
        taskfield = new JTextPane();
        taskfield.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taskfield.setPreferredSize(CommonConstants.TASKFIELD_SIZE);
        taskfield.setContentType("text/html");
        taskfield.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                taskfield.setBackground(Color.WHITE);

            }

            @Override
            public void focusLost(FocusEvent e) {
                taskfield.setBackground(Color.WHITE);
            }
        });

        //checkbox
        checkBox = new JCheckBox();
        checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBox.addActionListener(this);


        //delete button
        deletebutton = new JButton("X");
        deletebutton.setPreferredSize(CommonConstants.DELETE_BUTTON);
        deletebutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deletebutton.addActionListener(this);

        //add to this taskcomponet
        add(checkBox);
        add(taskfield);
        add(deletebutton);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(checkBox.isSelected()) {
            //replace all html tags to empty string to grab the main text
            String taskText = taskfield.getText().replaceAll("<[^>]*>", "");

            //add strikethrough text
            taskfield.setText("<html><s>" + taskText + "</s></html>");
        }else if(!checkBox.isSelected()){
            String tasktext = taskfield.getText().replaceAll("<[^>]*>","");

            taskfield.setText(tasktext);

        if(e.getActionCommand().equalsIgnoreCase("X")){
            //delete the component from parent panel
            parentpanel.remove(this);
            parentpanel.repaint();;
            parentpanel.revalidate();
        }

        }
    }
}

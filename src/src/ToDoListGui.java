package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ToDoListGui extends JFrame implements ActionListener {
   //taskpanel will act as a container for the taskcomponentpanel
   // will store all task components
   private JPanel taskPanel,taskComponentPanel;

   public ToDoListGui() {
      super("To Do List Application");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setPreferredSize(src.CommonConstants.GUI_SIZE);
      pack();
      setLocationRelativeTo(null);
      setResizable(false);
      setLayout(null);
      addGuiComponent();
   }
   private void addGuiComponent(){
       //banner text
      JLabel bannerlabel = new JLabel("To Do list");
      bannerlabel.setFont(createFont("resources/LEMONMILK-Light.otf",36f));
      bannerlabel.setBounds(
              (src.CommonConstants.GUI_SIZE.width - bannerlabel.getPreferredSize().width)/2,
              15,
              src.CommonConstants.BANNER_SIZE.width,
              src.CommonConstants.BANNER_SIZE.height
      );

      //taskpanel
      taskPanel = new JPanel();
      //taskcomponentpanel
      taskComponentPanel = new JPanel();
      taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel,BoxLayout.Y_AXIS));
      taskPanel.add(taskComponentPanel);

      //add scroll to taskpanel
      JScrollPane scrollPane = new JScrollPane(taskPanel);
      scrollPane.setBounds(0,70, src.CommonConstants.TASKPANEL_SIZE.width, src.CommonConstants.TASKPANEL_SIZE.height);
      scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
      scrollPane.setMaximumSize(src.CommonConstants.TASKPANEL_SIZE);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

      //changing time speed of scroll bar
      JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
      verticalScrollBar.setUnitIncrement(20);

      //add task button
      JButton addtaskbutton = new JButton("ADD TASK");
      addtaskbutton.setFont(createFont("resources/LEMONMILK-Light.otf",18f));
      addtaskbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      addtaskbutton.setBounds(-5,CommonConstants.GUI_SIZE.height -88,
              CommonConstants.ADDTASK_BUTTON_SIZE.width,CommonConstants.ADDTASK_BUTTON_SIZE.height);
      addtaskbutton.addActionListener(this);


       //add to frame
      this.getContentPane().add(bannerlabel);
      this.getContentPane().add(scrollPane);
      this.getContentPane().add(addtaskbutton);

   }
   private Font createFont(String resource,float size) {
      //get the font file path
      String filepath = getClass().getClassLoader().getResource(resource).getPath();

      //check to see if the path contains a folder with spaces in them
      if (filepath.contains("%20")) {
         filepath = getClass().getClassLoader().getResource(resource).getPath()
                 .replaceAll("%20", " ");
      }

      //create font
      try {
         File customFontFile = new File(filepath);
         Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile).deriveFont(size);
         return customFont;

      } catch (Exception e) {
         System.out.println("Error" + e);
      }
      return null;
   }



   public void actionPerformed(ActionEvent e) {
         String command = e.getActionCommand();
         if(command.equalsIgnoreCase("Add task")){
            //create a task component
            TaskComponent taskComponent= new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            //make the previous task appear disabled
            if(taskComponentPanel.getComponentCount()>1){
               TaskComponent previousTask = (TaskComponent) taskComponentPanel.getComponent(
                       taskComponentPanel.getComponentCount()-2);
               previousTask.getTaskfield().setBackground(null);

            }

            //make the task field request foucus after creation
            taskComponent.getTaskfield().requestFocus();
            repaint();
            revalidate();
         }
      }
   }




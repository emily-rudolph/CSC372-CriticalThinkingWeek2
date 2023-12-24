import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

//Declare Class 
public class UserBirthday extends JFrame implements ActionListener{
    //Declare Frame 
    private JFrame f; 

    //Declare Panels
    private JPanel p; 
    private JPanel p1;

    //Declare Components 
    private JTextField bDayText; 
    private JButton b;
    private JLabel ageLable; 

    //Constructor 
    UserBirthday() { 

        //initialize frame 
        f = new JFrame(); 
        f.setLayout(new FlowLayout());

        //initialize button 
        b = new JButton("Enter Your Birthday!"); 
        b.setHorizontalAlignment(JButton.LEFT);
        b.addActionListener(this);

        //initialize text field 
        bDayText = new JTextField(11);
        bDayText.setEditable(true);
        bDayText.setText("mm/dd/yyyy");
        bDayText.setPreferredSize(new Dimension(40,20));  
        bDayText.setHorizontalAlignment(JTextField.RIGHT); 

        //initalize age results label 
        ageLable = new JLabel();

        //initialize first panel
        p = new JPanel();  
        p.setLayout(new FlowLayout());

        //add components to panel 
        p.add(b); 
        p.add(bDayText);

        //set information about frame 
        f.setSize(500,150); 
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Age Calculator");  

        //add first panel to frame
        f.add(p);  
         
        //initalize second panel 
        p1 = new JPanel(); 
        p1.setBackground(Color.WHITE); 
        p1.setLayout(new FlowLayout());
        p1.setPreferredSize(new Dimension(500, 100));

        //add components to panel 
        p1.add(ageLable); 
        
        //add second panel to frame 
        f.add(p1); 
        f.revalidate(); 

    }

        //override action listener for button 
        @Override 
        public void actionPerformed (ActionEvent event) { 
            if (event.getSource() == b) {

                //remove existing age results label 
                Container parent = ageLable.getParent(); 
                parent.remove(ageLable);
                
                //add new age results label 
                ageLable = new JLabel(getAge()); 
                p1.add(ageLable); 

                //update panel and frame 
                parent.validate();
                parent.repaint();
                f.revalidate(); 
            }
    }

        public String getAge() { 
            LocalDate dob;
            String userInput; 
            LocalDate date; 
            int age; 

            //get the current date and the value entered by the user
            date = LocalDate.now(); 
            userInput = bDayText.getText(); 

            //initialize formatter 
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            try { 

                //convert string to date 
                dob = LocalDate.parse(userInput, formatter);   

                //calcualte difference in years 
                age = Period.between(dob, date).getYears(); 

                //format and return answer as string 
                String s = String.format("Your Age IS: %d", age); 
                return s;

            }
            
            catch(Exception e) {
                //return answer as 'error' message to user. 
                return ("Sorry Your Date Of Birth Was Not Entered Correctly. Please Try Again!"); 
        }
    }
}

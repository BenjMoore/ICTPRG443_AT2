package Quiz.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class mainScreen extends JFrame implements ActionListener {
    // declare buttons and layout
    SpringLayout myLayout = new SpringLayout();
    JButton btnExit, btnSave, btnOpen, btnBinary, btnClear, btnSort, btnRAF;
    JTextField[][] textFields = new JTextField[15][9];
    JTextField txtSearch;
    JTextField txtTeacher, txtClass, txtRoom, txtDate;


    public mainScreen() {
        //initialise main screen
        setSize(700, 500);
        setLocation(450, 200);
        AddWindowListenerToForm();
        setLayout(myLayout);
        SetupButtons();
        SetupTextfields();
        setVisible(true);
        ArrayList<Object[]> al = new ArrayList();

        System.out.println("Initial size of al: " + al.size());

        // add elements to the array list
        al.add(new Object[]{"Yes", "No"});
        al.add(new Object[]{"Hi", "there"});
        al.add(new Object[]{"True", "False"});
        al.add(new Object[]{"Yes", "No"});
        System.out.println("Size of al after additions: " + al.size());

        // display the array list
        System.out.println("Contents of al: " + al);
        // Remove elements from the array list
        al.remove("F");
        al.remove(2);
        System.out.println("Size of al after deletions: " + al.size());
        System.out.println("Contents of al: " + al);

        System.out.println("Item 1.1: " + al.get(0)[0]);
        System.out.println("Item 1.2: " + al.get(0)[1]);
        System.out.println("Item 2.1: " + al.get(1)[0]);
        System.out.println("Item 2.2: " + al.get(1)[1]);
        //Initiates file load upon start of application.

    }

    private void SetupButtons() {
        // set up all buttons
        btnExit = UIComponentLibrary.CreateJButton("Exit", 80, 25, 250, 350, this, this, myLayout);
        btnOpen = UIComponentLibrary.CreateJButton("Open", 80, 25, 150, 350, this, this, myLayout);
        btnSave = UIComponentLibrary.CreateJButton("Save", 80, 25, 50, 350, this, this, myLayout);
        btnBinary = UIComponentLibrary.CreateJButton("Search", 120, 25, 25, 400, this, this, myLayout);
        btnClear = UIComponentLibrary.CreateJButton("Clear", 80, 25, 350, 350, this, this, myLayout);
        btnSort = UIComponentLibrary.CreateJButton("Sort", 80, 25, 450, 350, this, this, myLayout);
        btnRAF = UIComponentLibrary.CreateJButton("RAF", 80, 25, 550, 350, this, this, myLayout);


    }

    private void SetupTextfields() {
        // set up all text fields
        txtSearch = UIComponentLibrary.CreateAJTextField(10, 150, 405, this, myLayout);
        txtTeacher = UIComponentLibrary.CreateAJTextField(10, 50, 10, this, myLayout);
        txtClass = UIComponentLibrary.CreateAJTextField(10, 200, 10, this, myLayout);
        txtRoom = UIComponentLibrary.CreateAJTextField(10, 350, 10, this, myLayout);
        txtDate = UIComponentLibrary.CreateAJTextField(10, 500, 10, this, myLayout);
        txtTeacher.setOpaque(true);
        txtClass.setOpaque(true);
        txtRoom.setOpaque(true);
        txtDate.setOpaque(true);
        txtTeacher.setBackground(Color.CYAN);
        txtClass.setBackground(Color.CYAN);
        txtRoom.setBackground(Color.CYAN);
        txtDate.setBackground(Color.CYAN);
        txtTeacher.setText("Teacher");
        txtClass.setText("Class");
        txtRoom.setText("Room");
        txtDate.setText("Date");

        //Iterates through 2D textfield array to generate and build individual textfields
        //Y values indicate Y-Axis of array values X indicate X-Axis eg.array[y][x]

        // add text to the text box array
        for (int y = 0; y < textFields.length; y++) {
            for (int x = 0; x < textFields[y].length; x++) {
                //Calculates X & y positions of current textfield
                //Format used = spacingBetweenUnits * iteration + paddingFromEdge
                int xPos = 60 * x + 70;
                int yPos = 20 * y + 40;
                textFields[y][x] = UIComponentLibrary.CreateAJTextField(5, xPos, yPos, this, myLayout);

                //Add focusListener to trigger on focus loss
                textFields[y][x].addFocusListener(new FocusAdapter() {
                                                      @Override
                                                      public void focusLost(FocusEvent focusEvent) {
                                                          super.focusLost(focusEvent);
                                                          JTextField field = (JTextField) focusEvent.getSource();
                                                          if (field.getText().equalsIgnoreCase("Desk")) {
                                                              field.setBackground(Color.CYAN);
                                                          } else {
                                                              field.setBackground(Color.white);
                                                          }
                                                      }
                                                  }
                );
            }
        }
    }

    private void AddWindowListenerToForm() // action listner add
    {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) // action listner class
    {
        if (actionEvent.getSource() == btnExit) //  exit button
        {
            System.exit(0); // exit
        }
    }

}

// loop though text fields
// if













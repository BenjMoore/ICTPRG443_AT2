package FileDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainScreen extends JFrame implements ActionListener
{
    // declare buttons and layout
    SpringLayout myLayout = new SpringLayout();
    JButton btnExit, btnSave, btnOpen, btnBinary,btnClear,btnSort, btnRAF;
    JTextField[][] textFields = new JTextField[15][9];
    JTextField txtSearch;
    JTextField txtTeacher,txtClass,txtRoom,txtDate;
    Student[] studentArray;
    LinkedList<Student> studentList = new LinkedList<>();

    public MainScreen()
    {
        //initialise main screen
        setSize(700,500);
        setLocation(450,200);
        AddWindowListenerToForm();
        setLayout(myLayout);
        SetupButtons();
        SetupTextfields();
        setVisible(true);
        ChooseFileToLoad();//Initiates file load upon start of application.

    }

    private void SetupButtons()
    {
        // set up all buttons
        btnExit = UIComponentLibrary.CreateJButton("Exit",80,25,250,350,this,this,myLayout);
        btnOpen = UIComponentLibrary.CreateJButton("Open",80,25,150,350,this,this,myLayout);
        btnSave = UIComponentLibrary.CreateJButton("Save",80,25,50,350,this,this,myLayout);
        btnBinary = UIComponentLibrary.CreateJButton("Search",120,25,25,400,this,this,myLayout);
        btnClear = UIComponentLibrary.CreateJButton("Clear",80,25,350,350,this,this,myLayout);
        btnSort = UIComponentLibrary.CreateJButton("Sort",80,25,450,350,this,this,myLayout);
        btnRAF = UIComponentLibrary.CreateJButton("RAF",80,25,550,350,this,this,myLayout);


    }

    private void SetupTextfields()
    {
        // set up all text fields
        txtSearch = UIComponentLibrary.CreateAJTextField(10,150,405,this,myLayout);
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
        for(int y = 0; y < textFields.length; y++)
        {
            for (int x = 0; x <textFields[y].length; x++)
            {
                //Calculates X & y positions of current textfield
                //Format used = spacingBetweenUnits * iteration + paddingFromEdge
                int xPos = 60 * x + 70;
                int yPos = 20 * y + 40;
                textFields[y][x] = UIComponentLibrary.CreateAJTextField(5,xPos,yPos,this,myLayout);

                //Add focusListener to trigger on focus loss
                textFields[y][x].addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent focusEvent)
                    {
                        super.focusLost(focusEvent);
                        JTextField field = (JTextField) focusEvent.getSource();
                        if (field.getText().equalsIgnoreCase("Desk"))
                        {
                            field.setBackground(Color.CYAN);
                        }
                        else
                        {
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
        if (actionEvent.getSource() == btnBinary) { // binary search

            Student[] students = GetStudentDetails(); // create array
            new SearchForm(students, txtSearch.getText()); // new instance of search form
            showdisplay(); // highlight search on main form
        }

        if (actionEvent.getSource() == btnClear){ // clear
            txtTeacher.setText("TEACHER:"); // reset text fields
            txtClass.setText("CLASS:");
            txtRoom.setText("ROOM:");
            txtDate.setText("DATE:");
            txtSearch.setText("");
            for (int i = 0; i < textFields.length; i++) { // loop to clear text fields and background color
                for (int j = 0; j < textFields[i].length; j++) {
                    textFields[i][j].setText("");
                    textFields[i][j].setBackground(Color.white);
                }
            }
            checkcolor(); // checks background color
        }
        if (actionEvent.getSource() == btnOpen) // opens file dialog
        {
            ChooseFileToLoad();
        }
        if (actionEvent.getSource() == btnRAF) // write to raf
        {
            //WriteToFile();
            WriteToRAF();
        }
        if (actionEvent.getSource() == btnSave) // save [write]
        {
            WriteToFile();

        }
        if (actionEvent.getSource() == btnSort) // sort
        {
            Student[] students = GetStudentDetails(); // array
            new SortingForm(students, txtSearch.getText()); // new instance of sort form
        }
    }

    private Student[] GetStudentDetails() // gets all students details and adds to list [student list]
    {

        for (int y = 0; y < textFields.length; y++)
        {
            for (int x = 0; x < textFields[y].length; x++)
            {
                if (textFields[y][x].getText().isEmpty() == false)
                {
                    studentList.add(new Student(textFields[y][x].getText(), y, x));
                }
            }
        }

        return studentList.toArray(new Student[studentList.size()]);
    }

    private void WriteToFile()
    {
        FileDialog fd = new FileDialog(this,"Save File",FileDialog.SAVE); // create file dialog
        fd.setDirectory("C://"); // set default directory
        fd.setVisible(true); // set visible
        if (fd.getFile() == null)
        {
            return;
        }
        String filePath = fd.getDirectory() + fd.getFile();
        if ( (filePath.endsWith(".csv") || filePath.endsWith(".txt"))  == false) // check if filepath ends in .csv or txt and if not make it .csv
        {
            filePath+= ".csv";
        }

        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath)); // create buffered writer into the path selected
            bw.write(txtTeacher.getText() + "\n");
            bw.write(txtClass.getText()+ "\n");
            bw.write(txtRoom.getText()+ "\n");
            bw.write(txtDate.getText()+ "\n");
            for (int y = 0; y < textFields.length; y++) // for all textfields in text field array
            {
                for (int x = 0; x < textFields[y].length; x++)
                {
                    if (textFields[y][x].getText().isEmpty() == false) // if not empty
                    {
                        bw.write(y + "," + x + "," + textFields[y][x].getText() + ","); // write ypos, xpos and text values
                        if (textFields[y][x].getBackground() == Color.CYAN) // if background is cyan write cyan
                        {
                            bw.write("CYAN");
                        }
                        bw.newLine(); // newline
                    }
                }
            }
            bw.close(); // close file after loop
            JOptionPane.showMessageDialog(null,"Records Saved Successfully"); // open message dialog

        }
        catch (Exception e)
        {

        }

    }

    private void ReadFromFile(String filePath)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath)); // create buffered reader

            int n = 1;

            txtTeacher.setText(br.readLine());
            txtClass.setText(br.readLine());
            txtRoom.setText(br.readLine());
            txtDate.setText(br.readLine());

            String line = ""; // create line string
            while((line = br.readLine()) != null) // while line not empty
            {
                String[] temp = line.split(","); // create temp array, split by comma
                int yPos = Integer.parseInt(temp[0]); // add ypos
                int xPOs = Integer.parseInt(temp[1]); // add xpos
                textFields[yPos][xPOs].setText(temp[2]); // add text

                // convert function if background fill present. sets to current config of desk,cyan
                if (temp[2].equals("BKGRND FILL"))
                {
                    textFields[yPos][xPOs].setText("Desk");
                    textFields[yPos][xPOs].setBackground(Color.CYAN);
                }

                if (temp.length >= 4)
                {
                    if (temp[3].equals("CYAN")) // if text cyan present
                    {
                        textFields[yPos][xPOs].setBackground(Color.CYAN); // set background color
                    }

                }
            }

            br.close(); // close buffered reader

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private void WriteToRAF()
    {
        FileDialog fd = new FileDialog(this,"Save File",FileDialog.SAVE); // new file dialoug
        fd.setDirectory("C://"); // set default directory
        fd.setVisible(true); // make visable
        if (fd.getFile() == null) // if file does not exist
        {
            return;
        }
        String filePath = fd.getDirectory() + fd.getFile();
        if (filePath.endsWith(".RAF") == false) // if file does not end in .raf
        {
            filePath+= ".RAF"; // make .raf
        }

        try
        {
            RandomAccessFile raf = new RandomAccessFile(filePath,"rw"); // new RAF writer

            int count = 0;
            int index = 0;
            // I am aware this is a stupid solution but it is all i can get to work :/
            raf.seek(9900);
            raf.writeUTF(txtTeacher.getText());
            raf.seek( 10000);
            raf.writeUTF(txtClass.getText());
            raf.seek( 11000);
            raf.writeUTF(txtRoom.getText());
            raf.seek( 12000);
            raf.writeUTF(txtDate.getText());

            for (int y = 0; y < textFields.length; y++)
            {
                for (int x = 0; x < textFields[y].length; x++)
                {
                    if (textFields[y][x].getText().isEmpty() == false) // if text not empty
                    {
                        index = count * 150 ; //number indicates length of each set of entries
                        raf.seek(index); // seek index
                        raf.writeUTF(textFields[y][x].getText()); // write text value
                        raf.seek(index + 50); // seek index
                        raf.writeInt(y); // write posy
                        raf.seek(index + 75); //seek index
                        raf.writeInt(x); //write posx

                        if (textFields[y][x].getBackground() == Color.CYAN) // if BG color is cyan
                        {
                            raf.seek(index + 100); // seek index
                            raf.writeUTF("CYAN"); // write cyan
                        }
                        count++; //itterate
                    }
                }

            }

            raf.close(); // close raf file viewer
            JOptionPane.showMessageDialog(null,"Records Saved Successfully"); // create message dialog

        }
        catch (Exception e)
        {

        }

    }

    private void ReadFromRAF(String filePath)
    {
        try
        {
            RandomAccessFile raf = new RandomAccessFile(filePath, "r"); // create RAF reader

            int count = 0;
            int index = 0;
            // I am aware this is a stupid solution but it is all i can get to work :/
            raf.seek(9900);
            txtTeacher.setText(raf.readUTF());
            raf.seek(10000);
            txtClass.setText(raf.readUTF());
            raf.seek( 11000);
            txtRoom.setText(raf.readUTF());
            raf.seek( 12000);
            txtDate.setText(raf.readUTF());

            while(count * 150 < raf.length()) // while count != raf length
            {
                index = count * 150 ; // set index
                raf.seek(index); // seek index
                String name = raf.readUTF(); // read name
                raf.seek(index + 50); // seek index
                int yPos = raf.readInt(); // read posy
                raf.seek(index + 75); // seek index
                int xPos = raf.readInt(); // read posx
                textFields[yPos][xPos].setText(name); // set textbox array to name
                if (name.compareToIgnoreCase("Desk") == 0) // if value == desk
                {
                    raf.seek(100); // seek
                    String color = raf.readUTF(); // set color var to line
                    if (color.compareToIgnoreCase("Cyan") == 0) // if line == cyan
                    {
                        textFields[yPos][xPos].setBackground(Color.CYAN); // set background to cyan
                    }
                }
                count++; // itterate


            }

            raf.close(); // close raf
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private void ChooseFileToLoad() // select file function
    {
        FileDialog fd = new FileDialog(this,"Choose a file",FileDialog.LOAD);
        fd.setDirectory("C://");
        fd.setVisible(true);
        if (fd.getFile() == null)
        {
            return;
        }
        ClearAllTextfields();
        String filePath = fd.getDirectory() + fd.getFile();
        if (filePath.endsWith(".RAF"))
        {
            ReadFromRAF(filePath);
            return;
        }
        ReadFromFile(filePath);
    }

    private void ClearAllTextfields() // clears text fields
    {
        for (int y = 0; y < textFields.length; y++)
        {
            for (int x = 0; x < textFields[y].length; x++)
            {
                textFields[y][x].setBackground(Color.white);
                textFields[y][x].setText("");
            }
        }
    }

    private void showdisplay(){ // shows search value

        for (int y = 0; y < textFields.length; y++)
        {
            for (int x = 0; x < textFields[y].length; x++)
            {
                if(textFields[y][x].getText().isEmpty() == false){
                    if (textFields[y][x].getText().equalsIgnoreCase(txtSearch.getText()))
                    {
                   textFields[y][x].setBackground(Color.orange);
                }

                }
            }
        }
    }
    private void checkcolor(){  // checks bg color for all text boxes
        for (int y = 0; y < textFields.length; y++)
        {
            for (int x = 0; x < textFields[y].length; x++)
            {
                if(textFields[y][x].getText().isEmpty() == false)
                {
                    if (textFields[y][x].getBackground() == Color.orange)
                    {

                        textFields[y][x].setBackground(Color.white);
                    }

                }
            }
        }
    }

}

// loop though text fields
    // if












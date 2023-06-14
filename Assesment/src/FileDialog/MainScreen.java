package FileDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;

public class MainScreen extends JFrame implements ActionListener
{
    SpringLayout myLayout = new SpringLayout();
    JButton btnExit, btnSave, btnOpen, btnBinary,btnClear,btnSort;
    JTextField[][] textFields = new JTextField[15][9];
    JTextField txtSearch;
    JTextField txtTeacher,txtClass,txtRoom,txtDate;


    public MainScreen()
    {
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
        btnExit = UIComponentLibrary.CreateJButton("Exit",80,25,300,350,this,this,myLayout);
        btnOpen = UIComponentLibrary.CreateJButton("Open",80,25,200,350,this,this,myLayout);
        btnSave = UIComponentLibrary.CreateJButton("Save",80,25,100,350,this,this,myLayout);
        btnBinary = UIComponentLibrary.CreateJButton("Search",120,25,25,400,this,this,myLayout);
        btnClear = UIComponentLibrary.CreateJButton("Clear",80,25,400,350,this,this,myLayout);
        btnSort = UIComponentLibrary.CreateJButton("Sort",80,25,500,350,this,this,myLayout);


    }

    private void SetupTextfields()
    {
        txtSearch = UIComponentLibrary.CreateAJTextField(10,150,405,this,myLayout);
        txtTeacher = UIComponentLibrary.CreateAJTextField(10, 10, 10, this, myLayout);
        txtClass = UIComponentLibrary.CreateAJTextField(10, 150, 10, this, myLayout);
        txtRoom = UIComponentLibrary.CreateAJTextField(10, 290, 10, this, myLayout);
        txtDate = UIComponentLibrary.CreateAJTextField(10, 440, 10, this, myLayout);

        //Iterates through 2D textfield array to generate and build individual textfields
        //Y values indicate Y-Axis of array values X indicate X-Axis eg.array[y][x]
        for(int y = 0; y < textFields.length; y++)
        {
            for (int x = 0; x <textFields[y].length; x++)
            {
                //Calculates X & y positions of current textfield
                //Format used = spacingBetweenUnits * iteration + paddingFromEdge
                int xPos = 60 * x + 55;
                int yPos = 20 * y + 40;
                textFields[y][x] = UIComponentLibrary.CreateAJTextField(5,xPos,yPos,this,myLayout);

                //Add focusListener to trigger on focus loss
                textFields[y][x].addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent focusEvent) {
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
                });
            }
        }
    }

    private void AddWindowListenerToForm()
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
    public void actionPerformed(ActionEvent actionEvent)
    {
        if (actionEvent.getSource() == btnExit)
        {
            System.exit(0);
        }
        if (actionEvent.getSource() == btnBinary){
            Student[] students = GetStudentDetails();
            new SortingForm(students, txtSearch.getText());
        }
        if (actionEvent.getSource() == btnClear){
            for (int i = 0; i < textFields.length; i++) {
                for (int j = 0; j < textFields[i].length; j++) {
                    textFields[i][j].setText("");
                    textFields[i][j].setBackground(Color.white);
                }
            }
        }
        if (actionEvent.getSource() == btnOpen)
        {
            ChooseFileToLoad();
        }
        if (actionEvent.getSource() == btnSave)
        {
            //WriteToFile();
            WriteToRAF();
        }
        if (actionEvent.getSource() == btnSort)
        {
            Student[] students = GetStudentDetails();
            new SortingForm(students, txtSearch.getText());
        }
    }

    private Student[] GetStudentDetails()
    {
        LinkedList<Student> studentList = new LinkedList<>();
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
        FileDialog fd = new FileDialog(this,"Save File",FileDialog.SAVE);
        fd.setDirectory("C://");
        fd.setVisible(true);
        if (fd.getFile() == null)
        {
            return;
        }
        String filePath = fd.getDirectory() + fd.getFile();
        if ( (filePath.endsWith(".csv") || filePath.endsWith(".txt"))  == false)
        {
            filePath+= ".csv";
        }

        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (int y = 0; y < textFields.length; y++)
            {
                for (int x = 0; x < textFields[y].length; x++)
                {
                    if (textFields[y][x].getText().isEmpty() == false)
                    {
                        bw.write(y + "," + x + "," + textFields[y][x].getText() + ",");
                        if (textFields[y][x].getBackground() == Color.CYAN)
                        {
                            bw.write("CYAN");
                        }
                        bw.newLine();
                    }
                }
            }
            bw.close();
            JOptionPane.showMessageDialog(null,"Records Saved Successfully");

        }
        catch (Exception e)
        {

        }

    }

    private void ReadFromFile(String filePath)
    {


        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = br.readLine()) != null)
            {
                String[] temp = line.split(",");
                int yPos = Integer.parseInt(temp[0]);
                int xPOs = Integer.parseInt(temp[1]);
                textFields[yPos][xPOs].setText(temp[2]);
                if (temp.length >= 4)
                {
                    if (temp[3].equals("CYAN"))
                    {
                        textFields[yPos][xPOs].setBackground(Color.CYAN);
                    }
                }
            }

            br.close();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private void WriteToRAF()
    {
        FileDialog fd = new FileDialog(this,"Save File",FileDialog.SAVE);
        fd.setDirectory("C://");
        fd.setVisible(true);
        if (fd.getFile() == null)
        {
            return;
        }
        String filePath = fd.getDirectory() + fd.getFile();
        if (filePath.endsWith(".RAF") == false)
        {
            filePath+= ".RAF";
        }

        try
        {
            RandomAccessFile raf = new RandomAccessFile(filePath,"rw");
            int count = 0;
            for (int y = 0; y < textFields.length; y++)
            {
                for (int x = 0; x < textFields[y].length; x++)
                {
                    if (textFields[y][x].getText().isEmpty() == false)
                    {
                        int index = count * 150; //number indicates length of each set of entries
                        raf.seek(index);
                        raf.writeUTF(textFields[y][x].getText());
                        raf.seek(index + 50);
                        raf.writeInt(y);
                        raf.seek(index + 75);
                        raf.writeInt(x);

                        if (textFields[y][x].getBackground() == Color.CYAN)
                        {
                            raf.seek(index + 100);
                            raf.writeUTF("CYAN");
                        }
                        count++;
                    }
                }
            }
            raf.close();
            JOptionPane.showMessageDialog(null,"Records Saved Successfully");

        }
        catch (Exception e)
        {

        }

    }

    private void ReadFromRAF(String filePath)
    {
        try
        {
            RandomAccessFile raf = new RandomAccessFile(filePath, "r");
            int count = 0;
            while(count * 150 < raf.length())
            {
                int index = count * 150;
                raf.seek(index);
                String name = raf.readUTF();
                raf.seek(index + 50);
                int yPos = raf.readInt();
                raf.seek(index + 75);
                int xPos = raf.readInt();
                textFields[yPos][xPos].setText(name);
                if (name.compareToIgnoreCase("Desk") == 0)
                {
                    raf.seek(100);
                    String color = raf.readUTF();
                    if (color.compareToIgnoreCase("Cyan") == 0)
                    {
                        textFields[yPos][xPos].setBackground(Color.CYAN);
                    }
                }
                count++;
            }
            raf.close();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private void ChooseFileToLoad()
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

    private void ClearAllTextfields()
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
}











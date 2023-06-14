package FileDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.LinkedList;

public class SortingForm extends JFrame
{
    SpringLayout myLayout = new SpringLayout();
    JTextField[][] textfields;
    Student[] studentArray;
    String searchName;

    public SortingForm(Student[] array, String search)
    {
        setSize(250,array.length * 20 + 100);
        setLocation(300,50);
        setLayout(myLayout);
        textfields = new JTextField[array.length][3];
        studentArray = array;
        searchName = search;
        AddWindowListenerToForm();
        SetupTextFields();
        DisplayStudents();
        ShowSearchedStudent();





        setVisible(true);
    }

    private void AddWindowListenerToForm()
    {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                dispose();
            }
        });
    }

    private void SetupTextFields()
    {
        for (int y = 0; y < textfields.length ; y++)
        {
            for (int x = 0; x < textfields[y].length; x++)
            {
                int xPos = x * 60 + 25;
                int yPos = y * 20 + 25;
                textfields[y][x] = UIComponentLibrary.CreateAJTextField(5,xPos,yPos,this,myLayout);
            }
        }
    }

    private void DisplayStudents()
    {
        Arrays.sort(studentArray);
        for (int i = 0; i < studentArray.length; i++)
        {
            textfields[i][0].setText(studentArray[i].getStudentName());
            textfields[i][1].setText(Integer.toString(studentArray[i].getyPos()));
            textfields[i][2].setText(Integer.toString(studentArray[i].getxPos()));
        }
    }



    private void ShowSearchedStudent()
    {
        int index = Arrays.binarySearch(studentArray,searchName);

        if (index > -1)
        {
            for (int i = 0; i < textfields[index].length ; i++)
            {
                textfields[index][i].setBackground(Color.orange);

            }
        }
    }
}










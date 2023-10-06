package FileDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class SearchForm extends JFrame
{
    SpringLayout myLayout = new SpringLayout();
    JTextField[][] textfields;
    Student[] studentArray;
    String searchName;

    public SearchForm(Student[] array, String search)
    {
        int i = 0;
        int g = 0;
        while(array.length != g)
        {
            if(studentArray[i].getStudentName() == "Desk")
            {
                i--;
                g++;
            }
            i++;
            g++;
        }

        int arraylen = i;

        setSize(250,array.length + 100);
        setLocation(300,50);
        setLayout(myLayout);
        textfields = new JTextField[arraylen][3];
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


        for (int y = 0; y < textfields.length ; y++) {
                for (int x = 0; x < textfields[y].length; x++) {

                        int xPos = x * 60 + 25;
                        int yPos = y * 20 + 25;
                        textfields[y][x] = UIComponentLibrary.CreateAJTextField(5, xPos, yPos, this, myLayout);
                    }
            }
    }


    private void DisplayStudents() {

        Arrays.sort(studentArray);
        for (int i = 0; i < studentArray.length; i++) {
            if(studentArray[i].getStudentName() == "Desk"){

            }
            else {
                textfields[i][0].setText(studentArray[i].getStudentName());
                textfields[i][1].setText(Integer.toString(studentArray[i].getyPos()));
                textfields[i][2].setText(Integer.toString(studentArray[i].getxPos()));
            }
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










package FileDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * SearchForm class extends JFrame.
 * This class creates a form to display a list of students, highlighting a searched student's details.
 */
public class SearchForm extends JFrame {
    // Define the layout manager for the form
    SpringLayout myLayout = new SpringLayout();
    // 2D array of JTextFields for displaying student information
    JTextField[][] textfields;
    // Array of Student objects to hold the students being displayed
    Student[] studentArray;
    // String to hold the name of the student being searched
    String searchName;

    /**
     * Constructor for SearchForm.
     * Initializes the SearchForm, sets up text fields, and displays the student list.
     *
     * @param array  Array of Student objects to display in the form.
     * @param search The name of the student to search for and highlight.
     */
    public SearchForm(Student[] array, String search) {
        // Set the size of the form based on the number of students
        setSize(250, array.length * 10);
        // Set the location of the form on the screen
        setLocation(300, 50);
        // Set the layout manager for the form
        setLayout(myLayout);
        // Initialize the text fields array with dimensions based on the number of students
        textfields = new JTextField[array.length][3];
        // Assign the student array passed in the constructor to the class variable
        studentArray = array;
        // Assign the search string passed in the constructor to the class variable
        searchName = search;
        // Add a window listener to handle form closing events
        AddWindowListenerToForm();
        // Setup the text fields to display student information
        SetupTextFields();
        // Display the list of students in the form
        DisplayStudents();
        // Highlight the searched student in the list
        ShowSearchedStudent();
        // Make the form visible on the screen
        setVisible(true);
    }

    /**
     * Adds a window listener to handle the event when the form is closing.
     */
    private void AddWindowListenerToForm() {
        // Add a window listener to manage the form's closing behavior
        addWindowListener(new WindowAdapter() {
            @Override
            /**
             * Handles the window closing event.
             * @param windowEvent The event triggered when the window is closing.
             */
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                // Dispose of the form to free up resources when closing
                dispose();
            }
        });
    }

    /**
     * Sets up the JTextFields to display student information.
     * Only valid student entries (i.e., not labeled as "desk") are displayed.
     */
    private void SetupTextFields() {
        // Variable to track the number of valid students (i.e., students not labeled as "desk")
        int validStudentCount = 0;

        // Loop through the student array to create text fields for valid students
        for (int y = 0; y < studentArray.length; y++) {
            // Check if the student's name is not "desk"
            if (!studentArray[y].getStudentName().equalsIgnoreCase("desk")) {
                // Create and position text fields for the valid student
                for (int x = 0; x < textfields[y].length; x++) {
                    // Calculate the x-position of the text field
                    int xPos = x * 60 + 25;
                    // Calculate the y-position of the text field based on the valid student count
                    int yPos = validStudentCount * 20 + 25;
                    // Create and position the text field in the form
                    textfields[validStudentCount][x] = UIComponentLibrary.CreateAJTextField(5, xPos, yPos, this, myLayout);
                }
                // Increment the count of valid students
                validStudentCount++;
            }
        }
    }

    /**
     * Displays the list of students in the text fields.
     * The list is sorted alphabetically before being displayed.
     */
    private void DisplayStudents() {
        // Sort the student array alphabetically
        Arrays.sort(studentArray);
        // Variable to track the number of valid students (i.e., students not labeled as "desk")
        int validStudentCount = 0;

        // Loop through the sorted student array to display their information
        for (int i = 0; i < studentArray.length; i++) {
            // Check if the student's name is not "desk"
            if (!studentArray[i].getStudentName().equalsIgnoreCase("desk")) {
                // Set the text field with the student's name
                textfields[validStudentCount][0].setText(studentArray[i].getStudentName());
                // Set the text field with the student's y-position
                textfields[validStudentCount][1].setText(Integer.toString(studentArray[i].getyPos()));
                // Set the text field with the student's x-position
                textfields[validStudentCount][2].setText(Integer.toString(studentArray[i].getxPos()));
                // Increment the count of valid students
                validStudentCount++;
            }
        }
    }

    /**
     * Highlights the searched student's details in the text fields by changing the background color.
     */
    private void ShowSearchedStudent() {
        // Loop through the text field array to find and highlight the searched student
        for (int y = 0; y < textfields.length; y++) {
            // Check if the row in the text field array is initialized
            if (textfields[y] != null) {
                // Loop through the columns of the current row
                for (int x = 0; x < textfields[y].length; x++) {
                    // Check if the current text field is initialized and not empty
                    if (textfields[y][x] != null && !textfields[y][x].getText().isEmpty()) {
                        // If the text matches the searched name, change the background to orange
                        if (textfields[y][x].getText().equals(searchName)) {
                            textfields[y][x].setBackground(Color.orange);
                        } else {
                            // Reset the background color if it does not match
                            textfields[y][x].setBackground(null);
                        }
                    }
                }
            }
        }
    }
}

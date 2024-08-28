package FileDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;


/**
 * MainScreen class extends JFrame and implements ActionListener.
 * This is the main UI class for the application, responsible for setting up the main screen,
 * handling user actions, and managing file operations (read/write).
 */
public class MainScreen extends JFrame implements ActionListener {
    // Declare the layout manager
    SpringLayout myLayout = new SpringLayout();
    // Declare buttons for various functionalities
    JButton btnExit, btnSave, btnOpen, btnBinary, btnClear, btnSort, btnRAF;
    // Declare a 2D array of JTextField to represent a grid of text fields
    JTextField[][] textFields = new JTextField[19][10];
    // Declare text fields for search, teacher, class, room, and date information
    JTextField txtSearch;
    JTextField txtTeacher, txtClass, txtRoom, txtDate;
    // Declare an array to hold Student objects
    //Student[] studentArray;
    // Declare a LinkedList to hold Student objects
    LinkedList<Student> studentList = new LinkedList<>();

    /**
     * Constructor for MainScreen.
     * Initializes the main screen with specific dimensions, sets up the layout,
     * buttons, text fields, and starts the file loading process.
     */
    public MainScreen() {
        // Set the size of the main window
        setSize(800, 700);
        // Set the location of the main window on the screen
        setLocation(450, 200);
        // Add a window listener to handle the closing of the window
        AddWindowListenerToForm();
        // Set the layout manager for the window
        setLayout(myLayout);
        // Setup buttons on the main screen
        SetupButtons();
        // Setup text fields on the main screen
        SetupTextfields();
        // Make the main screen visible
        setVisible(true);
        // Start the file loading process upon application startup
        ChooseFileToLoad();
    }

    /**
     * Setup the JButtons on the main screen.
     * This method initializes and positions all the buttons using the UIComponentLibrary.
     */
    private void SetupButtons() {
        // Create and position the Exit button
        btnExit = UIComponentLibrary.CreateJButton("Exit", 80, 25, 300, 600, this, this, myLayout);
        // Create and position the Open button
        btnOpen = UIComponentLibrary.CreateJButton("Open", 80, 25, 200, 600, this, this, myLayout);
        // Create and position the Save button
        btnSave = UIComponentLibrary.CreateJButton("Save", 80, 25, 100, 600, this, this, myLayout);
        // Create and position the Search button
        btnBinary = UIComponentLibrary.CreateJButton("Search", 120, 25, 225, 50, this, this, myLayout);
        // Create and position the Clear button
        btnClear = UIComponentLibrary.CreateJButton("Clear", 80, 25, 400, 600, this, this, myLayout);
        // Create and position the Sort button
        btnSort = UIComponentLibrary.CreateJButton("Sort", 80, 25, 500, 600, this, this, myLayout);
        // Create and position the RAF button
        btnRAF = UIComponentLibrary.CreateJButton("RAF", 80, 25, 600, 600, this, this, myLayout);
    }

    /**
     * Setup the JTextFields on the main screen.
     * This method initializes and positions the text fields for search, teacher, class, room, date,
     * and a grid of text fields using the UIComponentLibrary.
     */
    private void SetupTextfields() {
        // Create and position the search text field
        txtSearch = UIComponentLibrary.CreateAJTextField(10, 100, 50, this, myLayout);
        // Create and position the teacher text field
        txtTeacher = UIComponentLibrary.CreateAJTextField(10, 100, 10, this, myLayout);
        // Create and position the class text field
        txtClass = UIComponentLibrary.CreateAJTextField(10, 250, 10, this, myLayout);
        // Create and position the room text field
        txtRoom = UIComponentLibrary.CreateAJTextField(10, 400, 10, this, myLayout);
        // Create and position the date text field
        txtDate = UIComponentLibrary.CreateAJTextField(10, 550, 10, this, myLayout);

        // Set the background color of specific text fields to cyan
        txtTeacher.setOpaque(true);
        txtClass.setOpaque(true);
        txtRoom.setOpaque(true);
        txtDate.setOpaque(true);
        txtTeacher.setBackground(Color.CYAN);
        txtClass.setBackground(Color.CYAN);
        txtRoom.setBackground(Color.CYAN);
        txtDate.setBackground(Color.CYAN);

        // Set default text for specific text fields
        txtTeacher.setText("Teacher");
        txtClass.setText("Class");
        txtRoom.setText("Room");
        txtDate.setText("Date");

        // Iterate through the 2D text field array to initialize and position individual text fields
        for (int y = 0; y < textFields.length; y++) {
            for (int x = 0; x < textFields[y].length; x++) {
                // Calculate the X and Y positions of the current text field
                int xPos = 60 * x + 90;
                int yPos = 20 * y + 100;
                // Create and position the text field in the array
                textFields[y][x] = UIComponentLibrary.CreateAJTextField(5, xPos, yPos, this, myLayout);

                // Add a focus listener to each text field to handle focus loss events
                textFields[y][x].addFocusListener(new FocusAdapter() {
                    @Override
                    /**
                     * If focus is lost, check the text content and update background color accordingly.
                     * @param focusEvent The focus event triggered when a text field loses focus.
                     */
                    public void focusLost(FocusEvent focusEvent) {
                        super.focusLost(focusEvent);
                        JTextField field = (JTextField) focusEvent.getSource();
                        // Set background color to cyan if the text is "Desk", otherwise set it to white
                        if (field.getText().equalsIgnoreCase("Desk")) {
                            field.setBackground(Color.CYAN);
                        } else {
                            field.setBackground(Color.white);
                        }
                    }
                });
            }
        }
    }

    /**
     * Add a window listener to the main screen.
     * This method handles the event when the window is being closed by the user.
     */
    private void AddWindowListenerToForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            /**
             * Handle the window closing event.
             * @param windowEvent The window event triggered when the window is closing.
             */
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                // Exit the application when the window is closed
                System.exit(0);
            }
        });
    }

    /**
     * Handle actions performed on the main screen.
     * This method handles various button clicks and performs the corresponding actions.
     * @param actionEvent The action event triggered by a button click.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Handle the Exit button click
        if (actionEvent.getSource() == btnExit) {
            // Exit the application
            System.exit(0);
        }
        // Handle the Search button click
        if (actionEvent.getSource() == btnBinary) {
            // Perform the binary search operation
            checkcolor();
            // Get student details from the grid
            Student[] students = GetStudentDetails();
            // Open a new SearchForm to display search results
            new SearchForm(students, txtSearch.getText());
            // Highlight the search results on the main screen
            showdisplay();
        }
        // Handle the Clear button click
        if (actionEvent.getSource() == btnClear) {
            // Reset the text fields and background colors
            txtTeacher.setText("TEACHER:");
            txtClass.setText("CLASS:");
            txtRoom.setText("ROOM:");
            txtDate.setText("DATE:");
            txtSearch.setText("");
            // Loop through the text fields to clear their content and reset background color
            for (int i = 0; i < textFields.length; i++) {
                for (int j = 0; j < textFields[i].length; j++) {
                    textFields[i][j].setText("");
                    textFields[i][j].setBackground(Color.white);
                }
            }
            // Check and update the background color of text fields if needed
            checkcolor();
        }
        // Handle the Open button click
        if (actionEvent.getSource() == btnOpen) {
            // Open the file dialog to choose a file to load
            ChooseFileToLoad();
        }
        // Handle the RAF button click
        if (actionEvent.getSource() == btnRAF) {
            // Write the current data to a RAF (Random Access File)
            WriteToRAF();
        }
        // Handle the Save button click
        if (actionEvent.getSource() == btnSave) {
            // Save the current data to a file
            WriteToFile();
        }
        // Handle the Sort button click
        if (actionEvent.getSource() == btnSort) {
            // Get student details from the grid
            Student[] students = GetStudentDetails();
            // Open a new SortingForm to display sorted data
            new SortingForm(students, txtSearch.getText());
        }
    }

    /**
     * Get details of students from the text field grid.
     * This method extracts the data from the text fields and returns it as an array of Student objects.
     * @return An array of Student objects containing the data from the grid.
     */
    private Student[] GetStudentDetails() {
        // Clear the studentList before adding new elements
        studentList.clear();

        // Iterate through the text field grid to collect student data
        for (int y = 0; y < textFields.length; y++) {
            for (int x = 0; x < textFields[y].length; x++) {
                // Skip entries labeled as "Desk"
                if (textFields[y][0].getText().equals("Desk")) {
                    continue;
                }
                // If the text field is not empty, add the student to the list
                if (!textFields[y][x].getText().isEmpty()) {
                    studentList.add(new Student(textFields[y][x].getText(), y, x));
                }
            }
        }

        // Convert the LinkedList to an array and return it
        return studentList.toArray(new Student[studentList.size()]);
    }

    /**
     * Write the current data to a CSV file.
     * This method opens a file dialog to choose the location and name of the file,
     * and then writes the data from the text fields to the selected file.
     */
    private void WriteToFile() {
        // Create a file dialog for saving the file
        FileDialog fd = new FileDialog(this, "Save File", FileDialog.SAVE);
        // Set the default directory for the file dialog
        fd.setDirectory("C://");
        // Show the file dialog
        fd.setVisible(true);
        // If the user cancels the file dialog, exit the method
        if (fd.getFile() == null) {
            return;
        }
        // Get the file path from the dialog
        String filePath = fd.getDirectory() + fd.getFile();
        // Check if the file path ends with .csv or .txt, if not, add .csv as the default extension
        if (!(filePath.endsWith(".csv") || filePath.endsWith(".txt"))) {
            filePath += ".csv";
        }

        try {
            // Create a BufferedWriter to write to the selected file
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            // Write the teacher, class, room, and date information to the file
            bw.write(txtTeacher.getText() + "\n");
            bw.write(txtClass.getText() + "\n");
            bw.write(txtRoom.getText() + "\n");
            bw.write(txtDate.getText() + "\n");

            // Iterate through the text field grid to write the data to the file
            for (int y = 0; y < textFields.length; y++) {
                for (int x = 0; x < textFields[y].length; x++) {
                    // If the text field is not empty, write its data to the file
                    if (!textFields[y][x].getText().isEmpty()) {
                        // Write the X and Y positions and the text content of the field
                        bw.write(x + "," + y + "," + textFields[y][x].getText() + ",");
                        // If the background color of the field is cyan, write "CYAN" to the file
                        if (textFields[y][x].getBackground() == Color.CYAN) {
                            bw.write("CYAN");
                        }
                        // Move to the next line after writing the data
                        bw.newLine();
                    }
                }
            }
            // Close the BufferedWriter after writing all data
            bw.close();
            // Display a message dialog to indicate successful saving
            JOptionPane.showMessageDialog(null, "Records Saved Successfully");

        } catch (Exception e) {
            // Handle any exceptions that may occur during file writing
            e.printStackTrace();
        }
    }


/**
     * Reads data from a CSV file and populates the text fields accordingly.
     *
     * @param filePath The file path of the .csv file to be read.
     */
    private void ReadFromFile(String filePath) {
        try {
            // Create a BufferedReader to read the file
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            // Initialize line number for setting values
            int n = 1;

            // Read and set the teacher name from the first line
            txtTeacher.setText(br.readLine());

            // Read and set the class name from the second line
            txtClass.setText(br.readLine());

            // Read and set the room name from the third line
            txtRoom.setText(br.readLine());

            // Read and set the date from the fourth line
            txtDate.setText(br.readLine());

            // Initialize a string to hold each line of the file
            String line = "";

            // Read each subsequent line until the end of the file
            while ((line = br.readLine()) != null) {
                // Split the line by commas into an array of strings
                String[] temp = line.split(",");

                // Parse and set the y position from the second element
                int yPos = Integer.parseInt(temp[1]);

                // Parse and set the x position from the first element
                int xPOs = Integer.parseInt(temp[0]);

                // Set the text field at the specified position with the third element
                textFields[yPos][xPOs].setText(temp[2]);

                // If the text is "BKGRND FILL", set the text to "Desk" and background to cyan
                if (temp[2].equals("BKGRND FILL")) {
                    textFields[yPos][xPOs].setText("Desk");
                    textFields[yPos][xPOs].setBackground(Color.CYAN);
                }

                // If there are more than three elements in the array
                if (temp.length >= 4) {
                    // If the fourth element is "CYAN", set the background color to cyan
                    if (temp[3].equals("CYAN")) {
                        textFields[yPos][xPOs].setBackground(Color.CYAN);
                    }
                }
            }

            // Close the BufferedReader after reading the file
            br.close();

        } catch (Exception e) {
            // Print any exceptions to the console
            System.out.println(e);
        }
    }

    /**
     * Writes data to a RAF (Random Access File) file.
     * Saves the current state of the text fields, including their text and background color.
     */
    private void WriteToRAF() {
        // Create a file dialog for saving the file
        FileDialog fd = new FileDialog(this, "Save File", FileDialog.SAVE);

        // Set the default directory to C://
        fd.setDirectory("C://");

        // Make the file dialog visible
        fd.setVisible(true);

        // If no file is selected, return without saving
        if (fd.getFile() == null) {
            return;
        }

        // Construct the file path from the directory and file name
        String filePath = fd.getDirectory() + fd.getFile();

        // If the file does not end with .RAF, append .RAF to the file name
        if (filePath.endsWith(".RAF") == false) {
            filePath += ".RAF";
        }

        try {
            // Create a RandomAccessFile for writing
            RandomAccessFile raf = new RandomAccessFile(filePath, "rw");

            // Initialize counters for the file writing process
            int count = 0;
            int index = 0;

            // Seek to specific positions and write the teacher, class, room, and date information
            raf.seek(9900);
            raf.writeUTF(txtTeacher.getText());
            raf.seek(10000);
            raf.writeUTF(txtClass.getText());
            raf.seek(11000);
            raf.writeUTF(txtRoom.getText());
            raf.seek(12000);
            raf.writeUTF(txtDate.getText());

            // Iterate over all text fields to save their data
            for (int y = 0; y < textFields.length; y++) {
                for (int x = 0; x < textFields[y].length; x++) {
                    // If the text field is not empty
                    if (textFields[y][x].getText().isEmpty() == false) {
                        // Calculate the index based on the count
                        index = count * 150;

                        // Seek to the calculated index and write the text field's text
                        raf.seek(index);
                        raf.writeUTF(textFields[y][x].getText());

                        // Seek to the calculated index and write the y position
                        raf.seek(index + 50);
                        raf.writeInt(y);

                        // Seek to the calculated index and write the x position
                        raf.seek(index + 75);
                        raf.writeInt(x);

                        // If the background color is cyan
                        if (textFields[y][x].getBackground() == Color.CYAN) {
                            // Seek to the calculated index and write "CYAN"
                            raf.seek(index + 100);
                            raf.writeUTF("CYAN");
                        }

                        // Increment the count for the next iteration
                        count++;
                    }
                }
            }

            // Close the RandomAccessFile after saving the data
            raf.close();

            // Show a message dialog indicating successful save
            JOptionPane.showMessageDialog(null, "Records Saved Successfully");

        } catch (Exception e) {
            // Handle any exceptions silently (could log this in a real application)
        }
    }

    /**
     * Reads data from a RAF (Random Access File) file and populates the text fields accordingly.
     *
     * @param filePath The file path of the RAF file to be read.
     */
    private void ReadFromRAF(String filePath) {
        try {
            // Create a RandomAccessFile for reading
            RandomAccessFile raf = new RandomAccessFile(filePath, "r");

            // Initialize counters for the file reading process
            int count = 0;
            int index = 0;

            // Seek to specific positions and read the teacher, class, room, and date information
            raf.seek(9900);
            txtTeacher.setText(raf.readUTF());
            raf.seek(10000);
            txtClass.setText(raf.readUTF());
            raf.seek(11000);
            txtRoom.setText(raf.readUTF());
            raf.seek(12000);
            txtDate.setText(raf.readUTF());

            // Read each set of data until the end of the file is reached
            while (count * 150 < raf.length()) {
                // Calculate the index based on the count
                index = count * 150;

                // Seek to the calculated index and read the text field's text
                raf.seek(index);
                String name = raf.readUTF();

                // Seek to the calculated index and read the y position
                raf.seek(index + 50);
                int yPos = raf.readInt();

                // Seek to the calculated index and read the x position
                raf.seek(index + 75);
                int xPos = raf.readInt();

                // Set the text field at the specified position with the read text
                textFields[yPos][xPos].setText(name);

                // If the text is "Desk", set the background color to cyan
                if (name.compareToIgnoreCase("Desk") == 0) {
                    raf.seek(100); // Seek to the position of the color information
                    String color = raf.readUTF(); // Read the color value
                    textFields[yPos][xPos].setBackground(Color.CYAN); // Set the background to cyan
                }

                // Increment the count for the next iteration
                count++;
            }

            // Close the RandomAccessFile after reading the data
            raf.close();

        } catch (Exception e) {
            // Print any exceptions to the console
            System.out.println(e);
        }
    }


    /**
     * Opens a file dialog to allow the user to choose a file to load.
     * Determines if the file is a RAF file or a regular text file, and
     * reads the data accordingly.
     */
    private void ChooseFileToLoad() {
        // Create a FileDialog for loading files
        FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);

        // Set the default directory to C://
        fd.setDirectory("C://");

        // Make the file dialog visible
        fd.setVisible(true);

        // If no file is selected, return without doing anything
        if (fd.getFile() == null) {
            return;
        }

        // Clear all text fields before loading new data
        ClearAllTextfields();

        // Construct the full file path
        String filePath = fd.getDirectory() + fd.getFile();

        // If the file is a RAF file, read from RAF
        if (filePath.endsWith(".RAF")) {
            ReadFromRAF(filePath);
            return;
        }

        // If the file is not a RAF file, read it as a regular text file
        ReadFromFile(filePath);
    }

    /**
     * Clears all text fields in the GUI.
     * Resets the background color to white and the text content to empty.
     */
    private void ClearAllTextfields() {
        // Iterate through each row of text fields
        for (int y = 0; y < textFields.length; y++) {
            // Iterate through each column of text fields
            for (int x = 0; x < textFields[y].length; x++) {
                // Set the background color to white
                textFields[y][x].setBackground(Color.white);

                // Clear the text content
                textFields[y][x].setText("");
            }
        }
    }

    /**
     * Highlights the text fields that match the search criteria.
     * Changes the background color to orange for matching text fields.
     */
    private void showdisplay() {
        // Iterate through each row of text fields
        for (int y = 0; y < textFields.length; y++) {
            // Iterate through each column of text fields
            for (int x = 0; x < textFields[y].length; x++) {
                // If the text field is not empty
                if (!textFields[y][x].getText().isEmpty()) {
                    // If the text matches the search text, highlight it in orange
                    if (textFields[y][x].getText().equalsIgnoreCase(txtSearch.getText())) {
                        textFields[y][x].setBackground(Color.orange);
                    }
                }
            }
        }
    }

    /**
     * Resets the background color of all text fields.
     * Changes the background color from orange to white for non-empty text fields.
     */
    private void checkcolor() {
        // Iterate through each row of text fields
        for (int y = 0; y < textFields.length; y++) {
            // Iterate through each column of text fields
            for (int x = 0; x < textFields[y].length; x++) {
                // If the text field is not empty
                if (!textFields[y][x].getText().isEmpty()) {
                    // If the background color is orange, reset it to white
                    if (textFields[y][x].getBackground() == Color.orange) {
                        textFields[y][x].setBackground(Color.white);
                    }
                }
            }
        }
    }


}







package FileDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class SortForm extends JFrame {
    SpringLayout myLayout = new SpringLayout();
    JTable table;
    Student[] studentArray;
    String searchName;

    /**
     * @param array
     * @param search
     */
    public SortForm(Student[] array, String search) {
        setSize(500, array.length * 15);
        setLocation(300, 50);
        setLayout(myLayout);
        studentArray = array;
        searchName = search;
        AddWindowListenerToForm();
        DisplayStudents();
        setVisible(true);
    }

    private void AddWindowListenerToForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                dispose();
            }
        });
    }

    private void DisplayStudents() {
        Arrays.sort(studentArray);

        String[] columnNames = {"Name", "X", "Y"};
        String[][] data = new String[studentArray.length][3];
        int rowCount = 0;

        for (int i = 0; i < studentArray.length; i++) {
            if (!studentArray[i].getStudentName().equals("Desk")) {

                data[rowCount][0] = studentArray[i].getStudentName();
                data[rowCount][1] = String.valueOf(studentArray[i].getxPos());
                data[rowCount][2] = String.valueOf(studentArray[i].getyPos());
                rowCount++;
            }

        }

        Object[][] trimmedData = new Object[rowCount][3];
        System.arraycopy(data, 0, trimmedData, 0, rowCount);

        DefaultTableModel model = new DefaultTableModel(trimmedData, columnNames);

        // Create a panel and add the scroll pane to it
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(new JTable(model));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Remove all components from the frame
        getContentPane().removeAll();

        // Add the panel to the frame
        add(panel);

        // Repaint the frame to update the changes
        revalidate();
        repaint();
    }
}

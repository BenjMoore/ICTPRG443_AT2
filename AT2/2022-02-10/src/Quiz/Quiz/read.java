package Quiz.Quiz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class read {


    public read() throws FileNotFoundException, IOException
    {

        BufferedReader br = new BufferedReader(new FileReader("./data.txt")); // create buffered reader
        int n = 1;
        String line = ""; // create line string
        while ((line = br.readLine()) != null) // while line not empty
        {
            for(int i = 0; i >= 9; i++)
            {
                br.readLine(); // add code
            }

        }
        br.close(); // close buffered reader
    }
    }





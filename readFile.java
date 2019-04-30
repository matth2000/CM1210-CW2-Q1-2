/*Name:     Matthew Herman
Student No: 1825882*/

// class to read file from data
import java.io.IOException;

import javafx.scene.chart.PieChart.Data;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

//uses file reader to read txt fiile
public class readFile {
    private String filename;
    public readFile (String file_name){
        filename = file_name;
    }
    //method 
    public List<String> openFile() throws IOException{
        //opens new file with filereader
        FileReader reader = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(reader);
        String currentLine;
        //creates a new list to store each line of text file
        List<String> text = new ArrayList<>();
        //loops through each line and adds each line to an array until a line is blank
        while ((currentLine = textReader.readLine())!= null){
            text.add(currentLine);
        }//closes the textfile and returns the list of lines
        textReader.close();
        return text;
    }
}
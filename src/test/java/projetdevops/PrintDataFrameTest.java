package projetdevops;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class PrintDataFrameTest {


    @Test
    public void allLines() {
        Dataframe test = new Dataframe("src/test/resources/data.csv");
        String output = test.afficheData(); // Capture the output
        String[] lines = output.split("\n");
        File file = new File("src/test/resources/all_lines.expected");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                assert(lines[i].equals(line));
                i++;
            }
            reader.close();
            System.out.println("All lines are correct");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        
    }
    @Test
    public void firstLines() {
        Dataframe test = new Dataframe("src/test/resources/data.csv");
        int rowCount = 1;
        String output = test.afficherPremieresLignes(rowCount); // Capture the output for the first 5 rows
        String[] lines = output.split("\n");
        File file = new File("src/test/resources/first_lines.expected");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                assert(lines[i].equals(line));
                i++;
            }
            reader.close();
            System.out.println("First lines are correct");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
    @Test
    public void lastLines() {
        Dataframe test = new Dataframe("src/test/resources/data.csv");
        int rowCount = 1;
        String output = test.afficherDernieresLignes(rowCount); // Capture the output for the last 5 rows
        String[] lines = output.split("\n");
        File file = new File("src/test/resources/last_lines.expected");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                assert(lines[i].equals(line));
                i++;
            }
            reader.close();
            System.out.println("Last lines are correct");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

}


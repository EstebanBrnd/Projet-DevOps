package projetdevops;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

public class DataframeTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test_init_dataframe_tableau(){
        Dataframe test = DataframeTestMother.DataframeTestMother();
        assertEquals(test.columnsNamesAndClasses.size(),5);
        assertEquals(test.data.size(),test.columnsNamesAndClasses.size());
        assertEquals(test.data.get(0).size(),3);
        assertEquals(test.data.get(1).size(),3);
        assertEquals(test.columnsNamesAndClasses.get(0).getFirst(),"0");
        assertEquals(test.columnsNamesAndClasses.get(0).getSecond(),String.class);
        assertEquals(test.columnsNamesAndClasses.get(1).getFirst(),"1");
        assertEquals(test.columnsNamesAndClasses.get(1).getSecond(),Integer.class);
        assertEquals(test.columnsNamesAndClasses.get(3).getFirst(),"3");
        assertEquals(test.columnsNamesAndClasses.get(3).getSecond(),Double.class);
    }

    @Test
    public void test_init_dataframe_csv(){
        Dataframe test = new Dataframe("src/test/resources/data.csv");
        assertEquals(test.columnsNamesAndClasses.size(),5);
        assertEquals(test.data.size(),test.columnsNamesAndClasses.size());
        assertEquals(test.data.get(0).size(),3);
        assertEquals(test.data.get(1).size(),3);
    }

    @Test
    public void verify_init_dataframe_csv(){
        Dataframe expected = DataframeTestMother.DataframeTestMother();
        Dataframe actual = new Dataframe("src/test/resources/data.csv");
        for (int i = 0; i < expected.data.size(); i++){
            for (int j = 0; j < expected.data.get(i).size(); j++){
                assertEquals(expected.data.get(i).get(j),actual.data.get(i).get(j));
            }
        }
    }

    @Test
    public void test_dataframe_tableau_empty(){
        ArrayList<Class> types = new ArrayList<>();
        Dataframe test = new Dataframe(types);
        assertEquals(test.columnsNamesAndClasses.size(),0);
        assertEquals(test.data.size(),0);
    }

    @Test
    public void test_dataframe_csv_empty(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = new Dataframe("");
    }

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

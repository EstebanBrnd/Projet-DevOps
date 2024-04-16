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
    public void test_init_dataframe_affichage(){
        Dataframe test = DataframeTestMother.DataframeTestMother();
        System.out.println(test.afficheData());
    }

    @Test
    public void test_init_dataframe_tableau(){
        Dataframe test = DataframeTestMother.DataframeTestMother();
        System.out.println(test.afficheData());
        assertEquals(test.columnsNamesAndClasses.size(),5);
        assertEquals(test.data.size(),test.columnsNamesAndClasses.size());
        assertEquals(test.columnsNamesAndClasses.get(0), new Couple<>("Nom",String.class));
        assertEquals(test.columnsNamesAndClasses.get(1), new Couple<>("Nombre",Integer.class));
        assertEquals(test.columnsNamesAndClasses.get(3), new Couple<>("Float",Float.class));

        assertEquals(test.data.get(0).get(0),"Test");
        assertEquals(test.data.get(1).get(1),3);
        assertEquals(test.data.get(3).get(1),4f);
    }

    @Test
    public void verify_init_dataframe_csv(){
        Dataframe expected = DataframeTestMother.DataframeTestMother();
        Dataframe actual = new Dataframe("src/test/resources/data.csv");
        System.out.println( actual.afficheData() );
        for (int i = 0; i < expected.data.size(); i++){
            for (int j = 0; j < expected.data.get(i).size(); j++){
                assertEquals(expected.data.get(i).get(j),actual.data.get(i).get(j));
            }
        }
    }

    @Test
    public void test_dataframe_tableau_empty(){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> columnNames = new ArrayList<>();
        Dataframe test = new Dataframe(data,columnNames);
        assertEquals(test.columnsNamesAndClasses.size(),0);
        assertEquals(test.data.size(),0);
        
    }

    @Test
    public void test_dataframe_csv_empty(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = new Dataframe("");
        assertEquals(test.columnsNamesAndClasses.size(),0);
        assertEquals(test.data.size(),0);

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
                assertEquals(lines[i], line);
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
                assertEquals(lines[i], line);
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
                assertEquals(lines[i], line);
                i++;
            }
            reader.close();
            System.out.println("Last lines are correct");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
    public void test_init_dataframe_csv(){
        Dataframe test = new Dataframe("src/test/resources/data2.csv");
        assertEquals(test.columnsNamesAndClasses.size(),4);
        assertEquals(test.data.size(),4);
        assertEquals(test.data.get(0).size(),4);
        assertEquals(test.data.get(1).size(),4);
    }

    @Test
    public void test_init_dataframe_csv2(){
        Dataframe test = new Dataframe("src/test/resources/data2.csv");
        assertEquals(test.columnsNamesAndClasses.get(0).getFirst(),"Nom");
        assertEquals(test.columnsNamesAndClasses.get(0).getSecond(),String.class);
        assertEquals(test.columnsNamesAndClasses.get(1).getFirst(),"Prenom");
        assertEquals(test.columnsNamesAndClasses.get(1).getSecond(),String.class);
        assertEquals(test.columnsNamesAndClasses.get(2).getFirst(),"Age");
        assertEquals(test.columnsNamesAndClasses.get(2).getSecond(),Integer.class);
        assertEquals(test.columnsNamesAndClasses.get(3).getFirst(),"Avenir");
        assertEquals(test.columnsNamesAndClasses.get(3).getSecond(),String.class);
    }

    @Test
    public void test_init_dataframe_csv3(){
        Dataframe test = new Dataframe("src/test/resources/data2.csv");
        assertEquals(test.data.get(0).get(0),"Quintela");
        assertEquals(test.data.get(1).get(0),"David");
        assertEquals(test.data.get(2).get(0),42);
        assertEquals(test.data.get(3).get(0),"Retraite");

        assertEquals(test.data.get(0).get(1),"Bouvier");
        assertEquals(test.data.get(1).get(1),"Lilou");
        assertEquals(test.data.get(2).get(1),21);
        assertEquals(test.data.get(3).get(1),"Aucun");

        assertEquals(test.data.get(0).get(2),"Matyasik");
        assertEquals(test.data.get(1).get(2),"Lukasz");
        assertEquals(test.data.get(2).get(2),22);
        assertEquals(test.data.get(3).get(2),"President");

        assertEquals(test.data.get(0).get(3),"Barneaud");
        assertEquals(test.data.get(1).get(3),"Esteban");
        assertEquals(test.data.get(2).get(3),21);
        assertEquals(test.data.get(3).get(3),"Agent secret");
    }
}

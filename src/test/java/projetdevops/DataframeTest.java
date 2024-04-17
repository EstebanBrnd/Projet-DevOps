package projetdevops;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
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
        for (int i = 0; i < expected.data.size(); i++){
            for (int j = 0; j < expected.data.get(i).size(); j++){
                assertEquals(expected.data.get(i).get(j),actual.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(actual));
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

    @Test
    public void test_mean_colonne(){
        Dataframe test = new Dataframe("src/test/resources/data2.csv");
        float mean = test.mean_colonne("Age");
        assertEquals(mean,26.5,0.1);
        // On attend IllegalArgumentException car la colonne "Nom" n'est pas numérique
        thrown.expect(IllegalArgumentException.class);
        test.mean_colonne("Nom");
    }

    @Test
    public void test_max_colonne(){
        Dataframe test = new Dataframe("src/test/resources/data2.csv");
        float max = test.max_colonne("Age");
        assertEquals(42.0,max,0.1);
        thrown.expect(IllegalArgumentException.class);
        test.max_colonne("Nom");
    }

    @Test
    public void test_min_colonne(){
        Dataframe test = new Dataframe("src/test/resources/data2.csv");
        float min = test.min_colonne("Age");
        assertEquals(min,21.0,0.1);
        thrown.expect(IllegalArgumentException.class);
        test.min_colonne("Nom");
    }

    @Test
    public void test_mean(){
        Dataframe test = new Dataframe("src/test/resources/data2.csv");
        ArrayList<Float> mean = test.mean();

        assertNull(mean.get(0));
        assertNull(mean.get(1));

        assertEquals(mean.get(2),26.5,0.1);
        assertNull(mean.get(3));
    }

    @Test
    public void test_max(){
        Dataframe test = new Dataframe("src/test/resources/data2.csv");
        ArrayList<Float> max = test.max();

        assertNull(max.get(0));
        assertNull(max.get(1));
        assertEquals(max.get(2),42,0.1);
        assertNull(max.get(3));
    }

    @Test
    public void test_min(){
        Dataframe test = new Dataframe("src/test/resources/data2.csv");
        ArrayList<Float> min = test.min();

        assertNull(min.get(0));
        assertNull(min.get(1));
        assertEquals(min.get(2),21,0);
        assertNull(min.get(3));
    }

    @Test
    public void test_select_rows_with_integer(){
        ArrayList<Class> types = new ArrayList<>();
        types.add(Integer.class);
        Dataframe expected = new Dataframe(types);
        expected.data.get(0).add(2);
        expected.data.get(0).add(3);
        expected.data.get(0).add(4);

        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(1);
        for (int i = 0; i < expected.data.size(); i++){
            for (int j = 0; j < expected.data.get(i).size(); j++){
                assertEquals(expected.data.get(i).get(j),iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_select_rows_with_integer_out_of_bound(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(10);
    }

    @Test
    public void test_select_rows_with_integer_negative(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(-1);
    }

    @Test
    public void test_select_rows_with_integer_list(){
        ArrayList<Class> types = new ArrayList<>();
        types.add(Integer.class);
        types.add(Double.class);
        Dataframe expected = new Dataframe(types);
        expected.data.get(0).add(2);
        expected.data.get(0).add(3);
        expected.data.get(0).add(4);
        expected.data.get(1).add(2f);
        expected.data.get(1).add(4f);
        expected.data.get(1).add(8f);

        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new int[]{1, 3});
        for (int i = 0; i < expected.data.size(); i++){
            for (int j = 0; j < expected.data.get(i).size(); j++){
                assertEquals(expected.data.get(i).get(j),iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_select_rows_with_empty_integer_list(){
        ArrayList<Class> types = new ArrayList<>();
        Dataframe expected = new Dataframe(types);

        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new int[]{});
        for (int i = 0; i < expected.data.size(); i++){
            for (int j = 0; j < expected.data.get(i).size(); j++){
                assertEquals(expected.data.get(i).get(j),iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_select_rows_with_integer_list_out_of_bound(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new int[]{1, 3, 10});
    }

    @Test
    public void test_select_rows_with_integer_list_negative(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new int[]{1, 3, -1});
    }

    @Test
    public void test_select_rows_with_boolean_mask(){
        ArrayList<Class> types = new ArrayList<>();
        types.add(Integer.class);
        types.add(Double.class);
        Dataframe expected = new Dataframe(types);
        expected.data.get(0).add(2);
        expected.data.get(0).add(3);
        expected.data.get(0).add(4);
        expected.data.get(1).add(2f);
        expected.data.get(1).add(4f);
        expected.data.get(1).add(8f);

        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new Boolean[]{false, true,false,true,false});
        for (int i = 0; i < expected.data.size(); i++){
            for (int j = 0; j < expected.data.get(i).size(); j++){
                assertEquals(expected.data.get(i).get(j),iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_select_rows_with_boolean_mask_of_too_much_size(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new Boolean[]{false, true,false,true,false,true,false,true});
    }

    @Test
    public void test_select_both_with_integer(){
        ArrayList<Class> types = new ArrayList<>();
        types.add(Double.class);
        Dataframe expected = new Dataframe(types);
        expected.data.get(0).add(4f);

        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(3, 1);
        for (int i = 0; i < expected.data.size(); i++){
            for (int j = 0; j < expected.data.get(i).size(); j++){
                assertEquals(expected.data.get(i).get(j),iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_select_both_with_integer_out_of_bound(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(10, 1);
    }

    @Test
    public void test_select_both_with_integer_out_of_bound2(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(1, 10);
    }

    @Test
    public void test_select_both_with_integer_negative(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(-1, 1);
    }

    @Test
    public void test_select_both_with_integer_negative2(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(1, -1);
    }

    @Test
    public void test_select_both_with_integer_list(){
        ArrayList<Class> types = new ArrayList<>();
        types.add(Integer.class);
        types.add(Double.class);
        Dataframe expected = new Dataframe(types);
        expected.data.get(0).add(3);
        expected.data.get(0).add(4);
        expected.data.get(1).add(4f);
        expected.data.get(1).add(8f);

        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new int[]{1, 3}, new int[]{1, 2});
        for (int i = 0; i < expected.data.size(); i++){
            for (int j = 0; j < expected.data.get(i).size(); j++){
                assertEquals(expected.data.get(i).get(j),iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_select_both_with_integer_list_out_of_bound(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new int[]{1, 3, 10}, new int[]{1, 2});
    }

    @Test
    public void test_select_both_with_integer_list_out_of_bound2(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new int[]{1, 3}, new int[]{1, 20});
    }

    @Test
    public void test_select_both_with_boolean_mask(){
        ArrayList<Class> types = new ArrayList<>();
        types.add(Integer.class);
        types.add(Double.class);
        Dataframe expected = new Dataframe(types);
        expected.data.get(0).add(3);
        expected.data.get(0).add(4);
        expected.data.get(1).add(4f);
        expected.data.get(1).add(8f);

        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new Boolean[]{false, true,false,true,false}, new Boolean[]{false, true,true});
        for (int i = 0; i < expected.data.size(); i++){
            for (int j = 0; j < expected.data.get(i).size(); j++){
                assertEquals(expected.data.get(i).get(j),iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

}

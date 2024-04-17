package projetdevops;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DataframeTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test_init_dataframe_affichage() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        System.out.println(test.afficheData());
    }

    @Test
    public void test_init_dataframe_tableau() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        System.out.println(test.afficheData());
        assertEquals(test.columnsNamesAndClasses.size(), 5);
        assertEquals(test.data.size(), test.columnsNamesAndClasses.size());
        assertEquals(test.columnsNamesAndClasses.get(0), new Couple<>("Nom", String.class));
        assertEquals(test.columnsNamesAndClasses.get(1), new Couple<>("Nombre", Integer.class));
        assertEquals(test.columnsNamesAndClasses.get(3), new Couple<>("Float", Float.class));

        assertEquals(test.data.get(0).get(0), "Test");
        assertEquals(test.data.get(1).get(1), 3);
        assertEquals(test.data.get(3).get(1), 4f);
    }

    @Test
    public void verify_init_dataframe_csv() {
        Dataframe expected = DataframeTestMother.DataframeTestMother();
        Dataframe actual = new Dataframe("src/test/resources/data.csv");
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), actual.data.get(i).get(j));
            }
        }
    }

    @Test
    public void test_dataframe_tableau_empty() {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> columnNames = new ArrayList<>();
        Dataframe test = new Dataframe(data, columnNames);
        assertEquals(test.columnsNamesAndClasses.size(), 0);
        assertEquals(test.data.size(), 0);
    }

    @Test
    public void test_dataframe_csv_empty() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = new Dataframe("");
        assertEquals(test.columnsNamesAndClasses.size(), 0);
        assertEquals(test.data.size(), 0);

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

    @Test
    public void test_init_dataframe_csv() {
        Dataframe test = new Dataframe("src/test/resources/data2.csv");
        assertEquals(test.columnsNamesAndClasses.size(), 4);
        assertEquals(test.data.size(), 4);
        assertEquals(test.data.get(0).size(), 4);
        assertEquals(test.data.get(1).size(), 4);
    }

    @Test
    public void test_init_dataframe_csv2() {
        Dataframe test = new Dataframe("src/test/resources/data2.csv");
        assertEquals(test.columnsNamesAndClasses.get(0).getFirst(), "Nom");
        assertEquals(test.columnsNamesAndClasses.get(0).getSecond(), String.class);
        assertEquals(test.columnsNamesAndClasses.get(1).getFirst(), "Prenom");
        assertEquals(test.columnsNamesAndClasses.get(1).getSecond(), String.class);
        assertEquals(test.columnsNamesAndClasses.get(2).getFirst(), "Age");
        assertEquals(test.columnsNamesAndClasses.get(2).getSecond(), Integer.class);
        assertEquals(test.columnsNamesAndClasses.get(3).getFirst(), "Avenir");
        assertEquals(test.columnsNamesAndClasses.get(3).getSecond(), String.class);
    }

    @Test
    public void test_init_dataframe_csv3() {
        Dataframe test = new Dataframe("src/test/resources/data2.csv");
        assertEquals(test.data.get(0).get(0), "Quintela");
        assertEquals(test.data.get(1).get(0), "David");
        assertEquals(test.data.get(2).get(0), 42);
        assertEquals(test.data.get(3).get(0), "Retraite");

        assertEquals(test.data.get(0).get(1), "Bouvier");
        assertEquals(test.data.get(1).get(1), "Lilou");
        assertEquals(test.data.get(2).get(1), 21);
        assertEquals(test.data.get(3).get(1), "Aucun");

        assertEquals(test.data.get(0).get(2), "Matyasik");
        assertEquals(test.data.get(1).get(2), "Lukasz");
        assertEquals(test.data.get(2).get(2), 22);
        assertEquals(test.data.get(3).get(2), "President");

        assertEquals(test.data.get(0).get(3), "Barneaud");
        assertEquals(test.data.get(1).get(3), "Esteban");
        assertEquals(test.data.get(2).get(3), 21);
        assertEquals(test.data.get(3).get(3), "Agent secret");
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
    public void test_iloc_rows_with_integer() {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Nom");
        columnNames.add("Nombre");
        columnNames.add("Nombre2");
        columnNames.add("Float");
        columnNames.add("Date");
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test2");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("3");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("4f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, columnNames);

        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(1);
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_rows_with_integer_out_of_bound() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(10);
    }

    @Test
    public void test_iloc_rows_with_integer_negative() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(-1);
    }

    @Test
    public void test_iloc_rows_with_integer_list() {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Nom");
        columnNames.add("Nombre");
        columnNames.add("Nombre2");
        columnNames.add("Float");
        columnNames.add("Date");
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("3");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("4f");
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, columnNames);

        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new int[]{1, 2});
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_rows_with_empty_integer_list() {
        Dataframe expected = DataframeTestMother.DataframeTestMother();

        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new int[]{});
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_rows_with_integer_list_out_of_bound() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new int[]{1, 3, 10});
    }

    @Test
    public void test_iloc_rows_with_integer_list_negative() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new int[]{1, 3, -1});
    }

    @Test
    public void test_iloc_rows_with_boolean_mask() {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Nom");
        columnNames.add("Nombre");
        columnNames.add("Nombre2");
        columnNames.add("Float");
        columnNames.add("Date");
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test2");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("3");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("4f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, columnNames);

        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new Boolean[]{false, true, false});
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_rows_with_boolean_mask2() {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Nom");
        columnNames.add("Nombre");
        columnNames.add("Nombre2");
        columnNames.add("Float");
        columnNames.add("Date");
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, columnNames);

        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new Boolean[]{true, false, true});
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_rows_with_boolean_mask_of_too_much_size() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new Boolean[]{false, true, false, true, false, true, false, true});
    }

    @Test
    public void test_iloc_rows_with_boolean_mask_empty() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        Dataframe iloc = test.iloc(new Boolean[]{});

        System.out.println(iloc.afficheData());
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_both_with_integer() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        ArrayList<String> D1 = new ArrayList<>();
        D1.add("4f");
        data.add(D1);

        Dataframe expected = new Dataframe(data, test.getColumnNames());

        Dataframe iloc = test.iloc(3, 1);
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_both_with_integer_out_of_bound() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(10, 1);
    }

    @Test
    public void test_iloc_both_with_integer_out_of_bound2() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(1, 10);
    }

    @Test
    public void test_iloc_both_with_integer_negative() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(-1, 1);
    }

    @Test
    public void test_iloc_both_with_integer_negative2() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(1, -1);
    }

    @Test
    public void test_iloc_both_with_integer_list() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test3");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("8f");
        data.add(D1);
        data.add(D4);

        Dataframe expected = new Dataframe(data, test.getColumnNames());

        Dataframe iloc = test.iloc(new int[]{0, 2}, new int[]{0, 3});
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_both_with_partialy_empty_integer_list() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        D4.add("8f");
        data.add(D1);
        data.add(D4);

        Dataframe expected = new Dataframe(data, test.getColumnNames());

        Dataframe iloc = test.iloc(new int[]{}, new int[]{0, 3});
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_both_with_partialy_empty_integer_list2() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());

        Dataframe iloc = test.iloc(new int[]{0, 2}, new int[]{});
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_both_with_empty_integer_list() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe expected = DataframeTestMother.DataframeTestMother();

        Dataframe iloc = test.iloc(new int[]{}, new int[]{});
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_both_with_integer_list_out_of_bound() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new int[]{1, 3, 10}, new int[]{1, 2});
    }

    @Test
    public void test_iloc_both_with_integer_list_out_of_bound2() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe iloc = test.iloc(new int[]{1, 3}, new int[]{1, 20});
    }

    @Test
    public void test_iloc_both_with_boolean_mask() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test3");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("8f");
        data.add(D1);
        data.add(D4);


        Dataframe expected = new Dataframe(data, test.getColumnNames());

        Dataframe iloc = test.iloc(new Boolean[]{true, false, true}, new Boolean[]{true, false, false, true, false});
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_both_with_boolean_mask_partialy_empty() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        D4.add("8f");
        data.add(D1);
        data.add(D4);

        Dataframe expected = new Dataframe(data, test.getColumnNames());

        Dataframe iloc = test.iloc(new Boolean[]{}, new Boolean[]{true, false, false, true, false});
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_both_with_boolean_partialy_empty2() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());

        Dataframe iloc = test.iloc(new Boolean[]{true, false, true}, new Boolean[]{});
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), iloc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_iloc_both_with_empty_boolean() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        Dataframe expected = new Dataframe(data, test.getColumnNames());

        Dataframe iloc = test.iloc(new Boolean[]{}, new Boolean[]{});
        assertTrue(expected.equals(iloc));
    }

    @Test
    public void test_loc_on_label() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        D1.add("Test3");
        data.add(D1);

        Dataframe expected = new Dataframe(data, test.getColumnNames());


        Dataframe loc = test.loc("Nom");
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), loc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(loc));
    }

    @Test
    public void test_loc_on_label_inexistant() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe loc = test.loc("Nom2");
    }

    @Test
    public void test_loc_on_label_array() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        D4.add("8f");
        data.add(D1);
        data.add(D4);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        Dataframe loc = test.loc(new String[]{"Nom", "Float"});
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), loc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(loc));
    }

    @Test
    public void test_loc_on_label_array_inexistant() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe loc = test.loc(new String[]{"Nom", "Float2"});
    }

    @Test
    public void test_loc_on_label_array_empty() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe expected = DataframeTestMother.DataframeTestMother();
        Dataframe loc = test.loc(new String[]{});
        assertTrue(expected.equals(loc));
    }

    @Test
    public void test_loc_on_boolean_array() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        D4.add("8f");
        data.add(D1);
        data.add(D4);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        Dataframe loc = test.loc(new Boolean[]{true, false, false, true, false});
        for (int i = 0; i < expected.data.size(); i++) {
            for (int j = 0; j < expected.data.get(i).size(); j++) {
                assertEquals(expected.data.get(i).get(j), loc.data.get(i).get(j));
            }
        }
        assertTrue(expected.equals(loc));
    }

    @Test
    public void test_loc_on_boolean_array_out_of_bound() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe loc = test.loc(new Boolean[]{true, false, false, true, false, true});
    }

    @Test
    public void test_loc_on_boolean_array_empty() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Dataframe expected = new Dataframe(data, test.getColumnNames());
        Dataframe loc = test.loc(new Boolean[]{});
        assertTrue(expected.equals(loc));
    }

    @Test
    public void test_loc_on_both_labels() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("3");
        data.add(D1);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        Dataframe loc = test.loc("Nombre", "Test2");
        assertTrue(expected.equals(loc));
    }

    @Test
    public void test_loc_on_both_labels_inexistant() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe loc = test.loc("Nombre7", "Test2");
    }

    @Test
    public void test_loc_on_both_labels_inexistant2() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe loc = test.loc("Nombre", "Test7");
    }

    @Test
    public void test_loc_on_both_boolean_array() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("3");
        data.add(D1);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        Dataframe loc = test.loc(new Boolean[]{false, true, false, false, false}, new Boolean[]{false, true, false});
        assertTrue(expected.equals(loc));
    }

    @Test
    public void test_loc_on_both_boolean_array_out_of_bound() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe loc = test.loc(new Boolean[]{false, true, false, false, false}, new Boolean[]{false, true, false, false});
    }

    @Test
    public void test_loc_on_both_boolean_array_empty() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Dataframe expected = new Dataframe(data, test.getColumnNames());
        Dataframe loc = test.loc(new Boolean[]{}, new Boolean[]{});
        assertTrue(expected.equals(loc));
    }

    @Test
    public void test_loc_on_both_boolean_array_partialy_empty() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("2");
        D1.add("3");
        D1.add("4");
        data.add(D1);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        Dataframe loc = test.loc(new Boolean[]{false, true, false, false, false}, new Boolean[]{});
        assertTrue(expected.equals(loc));
    }

    @Test
    public void test_loc_on_both_boolean_array_partialy_empty2() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        Dataframe loc = test.loc(new Boolean[]{}, new Boolean[]{true, false, true});
        assertTrue(expected.equals(loc));
    }

    @Test
    public void test_linesWithColumnStringEqual() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnStringEqual("Date", "7/4/2024");

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("3");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnStringEqual2() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnStringEqual("Nom", "Test3");

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnStringEqual3() {
        ArrayList<String> columnNames = DataframeTestMother.DataframeTestMother().getColumnNames();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("3");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        D3.add("28");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("9/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);
        Dataframe test = new Dataframe(data, columnNames);

        for(ArrayList<String> line : data) {
            line.remove(1);
        }

        Dataframe output = test.linesWithColumnStringEqual("Date", "7/4/2024");
        Dataframe expected = new Dataframe(data, test.getColumnNames());

        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnStringEqual_bad_column(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnStringEqual("Date2", "7/04/2024");
    }

    @Test
    public void test_linesWithColumnStringEqual_bad_column2(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnStringEqual("Nombre", "7/04/2024");
    }

    @Test
    public void test_linesWithColumnStringEqual_inexistant(){
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnStringEqual("Nom", "Michel");

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnIntegerEqual() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnIntegerEqual("Nombre", 3);

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test2");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("3");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("4f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnIntegerEqual2() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnIntegerEqual("Nombre2", 23);

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("3");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnIntegerEqual3() {
        ArrayList<String> columnNames = DataframeTestMother.DataframeTestMother().getColumnNames();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("3");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        D3.add("28");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);
        Dataframe test = new Dataframe(data, columnNames);

        for(ArrayList<String> line : data) {
            line.remove(2);
        }

        Dataframe output = test.linesWithColumnIntegerEqual("Nombre2", 23);
        Dataframe expected = new Dataframe(data, test.getColumnNames());

        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnIntegerEqual_bad_column() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnIntegerEqual("Nombre8", 3);
    }

    @Test
    public void test_linesWithColumnIntegerEqual_bad_column2() {
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnIntegerEqual("Nom", 3);
    }

    @Test
    public void test_linesWithColumnIntegerEqual_inexistant(){
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnIntegerEqual("Nombre", 5);

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnIntegerGreater() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnIntegerGreater("Nombre", 2);

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("3");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("4f");
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        System.out.println(expected.afficheData());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnIntegerToMuchGreater() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnIntegerGreater("Nombre", 6);

        ArrayList<ArrayList<String>> data = new ArrayList<>();

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnIntegerGreater_bad_column(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnIntegerGreater("Date2", 2);
    }

    @Test
    public void test_linesWithColumnIntegerGreater_bad_column2(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnIntegerGreater("Date", 2);
    }

    @Test
    public void test_linesWithColumnIntegerLess() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnIntegerLess("Nombre", 4);

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("3");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnIntegerLessToMuch() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnIntegerLess("Nombre", 2);

        ArrayList<ArrayList<String>> data = new ArrayList<>();

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnIntegerLess_bad_column(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnIntegerLess("Date2", 2);
    }

    @Test
    public void test_linesWithColumnIntegerLess_bad_column2(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnIntegerLess("Date", 2);
    }

    @Test
    public void test_linesWithColumnFloatEqual() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnFloatEqual("Float", 8f);

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnFloatEqual2() {
        ArrayList<String> columnNames = DataframeTestMother.DataframeTestMother().getColumnNames();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("3");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        D3.add("28");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        D4.add("4f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("9/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);
        Dataframe test = new Dataframe(data, columnNames);

        for(ArrayList<String> line : data) {
            line.remove(0);
        }

        Dataframe output = test.linesWithColumnFloatEqual("Float", 4f);
        Dataframe expected = new Dataframe(data, test.getColumnNames());

        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnFloat_bad_column(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnFloatEqual("Date2", 2f);
    }

    @Test
    public void test_linesWithColumnFloat_bad_column2(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnFloatEqual("Nombre", 2f);
    }

    @Test
    public void test_linesWithColumnFloatGreater() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnFloatGreater("Float", 7.0f);

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnFloatGreater2() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnFloatGreater("Float", 1.0f);

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("3");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnFloatGreater_inexistant() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        Dataframe output = test.linesWithColumnFloatGreater("Float", 9f);
        Dataframe expected = new Dataframe(data, test.getColumnNames());

        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnFloatToMuchGreater(){
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnFloatGreater("Float", 8.0f);

        ArrayList<ArrayList<String>> data = new ArrayList<>();

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnFloatGreater_bad_column(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnFloatGreater("Date2", 2f);
    }

    @Test
    public void test_linesWithColumnFloatGreater_bad_column2(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnFloatGreater("Date", 2f);
    }

    @Test
    public void test_linesWithColumnFloatLess() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnFloatLess("Float", 7.0f);

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("3");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        System.out.println(expected.afficheData());
        System.out.println(output.afficheData());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnFloatLess2() {
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnFloatLess("Float", 9.0f);

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("3");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnFloatToMuchLess(){
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnFloatLess("Float", 1.0f);

        ArrayList<ArrayList<String>> data = new ArrayList<>();

        Dataframe expected = new Dataframe(data, test.getColumnNames());
        assertTrue(expected.equals(output));
    }

    @Test
    public void test_linesWithColumnFloatLess_bad_column(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnFloatLess("Date2", 2f);
    }

    @Test
    public void test_linesWithColumnFloatLess_bad_column2(){
        thrown.expect(IllegalArgumentException.class);
        Dataframe test = DataframeTestMother.DataframeTestMother();
        Dataframe output = test.linesWithColumnFloatLess("Date", 2f);
    }
}

package projetdevops;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Test;


public class PrintDataFrameTest {


    @Test
    public void allLines() {
        Dataframe test = new Dataframe("src/test/resources/data.csv");
        System.out.println();
        System.out.println("Tout Lignes");
        System.out.println();
        String output = test.afficheData(); // Capture the output
        saveToCsv(output, "all_lines.csv");
    }
    @Test
    public void firstLines() {
        Dataframe test = new Dataframe("src/test/resources/data.csv");
        System.out.println();
        System.out.println("First Lignes");
        System.out.println();
        int rowCount = 1;
        String output = test.afficherPremieresLignes(rowCount); // Capture the output for the first 5 rows
        saveToCsv(output, "first_lines.csv");
    }
    @Test
    public void lastLines() {
        Dataframe test = new Dataframe("src/test/resources/data.csv");
        System.out.println();
        System.out.println("Dernieres Lignes");
        System.out.println();
        int rowCount = 1;
        String output = test.afficherDernieresLignes(rowCount); // Capture the output for the last 5 rows
        saveToCsv(output, "last_lines.csv");
    }

    private void saveToCsv(String data, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(data);
            writer.close();
            System.out.println("Output saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}


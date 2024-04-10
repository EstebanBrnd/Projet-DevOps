package projetdevops;

import org.junit.Test;


public class PrintDataFrameTest {


    @Test
    public void allLines() {
        Dataframe test = new Dataframe("src/test/resources/data.csv");
        System.out.println();
        System.out.println("Tout Lignes");
        System.out.println();
        test.afficheData();
    }
    @Test
    public void firstLines() {
        Dataframe test = new Dataframe("src/test/resources/data.csv");
        System.out.println();
        System.out.println("First Lignes");
        System.out.println();
        test.afficherPremieresLignes();
    }
    @Test
    public void lastLines() {
        Dataframe test = new Dataframe("src/test/resources/data.csv");
        System.out.println();
        System.out.println("Dernieres Lignes");
        System.out.println();
        test.afficherDernieresLignes();
    }
}


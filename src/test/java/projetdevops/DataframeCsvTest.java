package projetdevops;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;

public class DataframeCsvTest {

    @Test
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
        assertEquals(test.data.get(2).get(0),"42");
        assertEquals(test.data.get(3).get(0),"Retraite");

        assertEquals(test.data.get(0).get(1),"Bouvier");
        assertEquals(test.data.get(1).get(1),"Lilou");
        assertEquals(test.data.get(2).get(1),"21");
        assertEquals(test.data.get(3).get(1),"Aucun");

        assertEquals(test.data.get(0).get(2),"Matyasik");
        assertEquals(test.data.get(1).get(2),"Lukasz");
        assertEquals(test.data.get(2).get(2),"22");
        assertEquals(test.data.get(3).get(2),"Président");

        assertEquals(test.data.get(0).get(3),"Barneaud");
        assertEquals(test.data.get(1).get(3),"Esteban");
        assertEquals(test.data.get(2).get(3),"21");
        assertEquals(test.data.get(3).get(3),"Agent secret");
    }

}

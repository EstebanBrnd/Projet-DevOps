package projetdevops;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;

public class DataframeCsvTest {
    
    @Test
    public void test_init_dataframe(){
        Dataframe test = new Dataframe("src/test/resources/data.csv");
        assertEquals(test.columnsNamesAndClasses.size(),4);
        assertEquals(test.data.size(),4);
        assertEquals(test.data.get(0).size(),3);
        assertEquals(test.data.get(1).size(),3);
    }

}

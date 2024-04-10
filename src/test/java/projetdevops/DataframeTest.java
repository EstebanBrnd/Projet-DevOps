package projetdevops;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

public class DataframeTest {
    

    
        @Test
        public void test_init_dataframe_tableau(){
            ArrayList<Couple<String,Class>> types = new ArrayList<>();
            types.add(new Couple<String,Class>("test_label1",Integer.class));
            types.add(new Couple<String,Class>("test_label2",String.class));
            Dataframe test = new Dataframe(types);
            assertEquals(test.columnsNamesAndClasses.size(),2);
            assertEquals(test.data.size(),2);
            assertEquals(test.data.get(0).size(),0);
            assertEquals(test.data.get(1).size(),0);
            assertEquals(test.columnsNamesAndClasses.get(0).getFirst(),"test_label1");
            assertEquals(test.columnsNamesAndClasses.get(0).getSecond(),Integer.class);
            assertEquals(test.columnsNamesAndClasses.get(1).getFirst(),"test_label2");
            assertEquals(test.columnsNamesAndClasses.get(1).getSecond(),String.class);
    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
}

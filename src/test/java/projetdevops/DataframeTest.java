package projetdevops;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
        assertTrue(expected.equals(actual));
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
    public void test_select_rows_with_integer_list(){
        assertTrue(true);
    }

    @Test
    public void test_select_rows_with_slice_object(){
        assertTrue(true);
    }

    @Test
    public void test_select_rows_with_boolean_mask(){
        assertTrue(true);
    }

    @Test
    public void test_select_both_with_integer(){
        assertTrue(true);
    }

    @Test
    public void test_select_both_with_integer_list(){
        assertTrue(true);
    }

    @Test
    public void test_select_both_with_slice_object(){
        assertTrue(true);
    }

    @Test
    public void test_select_both_with_boolean_mask(){
        assertTrue(true);
    }

}

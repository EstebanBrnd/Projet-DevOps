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

package projetdevops;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;

public class DataframeTest {
    
    
        @Test
        public void test_init_dataframe(){
            ArrayList<Class> types = new ArrayList<>();
            types.add(Integer.class);
            types.add(String.class);
            Dataframe test = new Dataframe(types);
            assertEquals(test.columnsNamesAndClasses.size(),2);
            assertEquals(test.data.size(),2);
            assertEquals(test.data.get(0).size(),0);
            assertEquals(test.data.get(1).size(),0);
            assertEquals(test.columnsNamesAndClasses.get(0).getFirst(),"0");
            assertEquals(test.columnsNamesAndClasses.get(0).getSecond(),Integer.class);
            assertEquals(test.columnsNamesAndClasses.get(1).getFirst(),"1");
            assertEquals(test.columnsNamesAndClasses.get(1).getSecond(),String.class);
        }
}

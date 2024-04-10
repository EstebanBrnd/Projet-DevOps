package projetdevops;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;

public class DataframeTest {
    
    
        @Test
        public void test_init_dataframe(){
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
        }
}

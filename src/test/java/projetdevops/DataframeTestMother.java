package projetdevops;

import java.util.ArrayList;

public class DataframeTestMother {

    public static Dataframe DataframeTestMother(String type){
        ArrayList<ArrayList<Class>> types = new ArrayList<>();
        types.add(new Couple<String,Class>("0", String.class));
        types.add(new Couple<String,Class>("1", Integer.class));
        types.add(new Couple<String,Class>("2", Integer.class));
        types.add(new Couple<String,Class>("3", Double.class));
        types.add(new Couple<String,Class>("4", String.class));
        Dataframe test = new Dataframe(types);
        if (type.equals("csv")){
            test.data.get(0).add("Test");
            test.data.get(0).add("Test2");
            test.data.get(0).add("Test3");
            test.data.get(1).add(2);
            test.data.get(1).add(3);
            test.data.get(1).add(4);
            test.data.get(2).add(23);
            test.data.get(2).add(23);
            test.data.get(2).add(23);
            test.data.get(3).add(2f);
            test.data.get(3).add(4f);
            test.data.get(3).add(8f);
            test.data.get(4).add("7/4/2024");
            test.data.get(4).add("7/4/2024");
            test.data.get(4).add("7/4/2024");
        }

        return test;
    }
}

package projetdevops;

import java.util.ArrayList;

public class Dataframe {
    ArrayList<Couple<String,Class>> columnsNamesAndClasses;

    ArrayList<ArrayList> data;

    public Dataframe(ArrayList<Class> types){
        columnsNamesAndClasses = new ArrayList<>();
        data = new ArrayList<>();
        Integer i = 0;
        for (Class type : types){
            columnsNamesAndClasses.add(new Couple<String,Class>(i.toString(),type));
            // Ajoute une arraylist de type type à data
            data.add(new ArrayList<Class>());
            i++;
        }
    }
}

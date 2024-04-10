package projetdevops;

import java.util.ArrayList;

public class Dataframe {
    ArrayList<Couple<String,Class>> columnsNamesAndClasses;

    ArrayList<ArrayList> data;

    public Dataframe(ArrayList<Couple<String,Class>> types){
        columnsNamesAndClasses = new ArrayList<>();
        data = new ArrayList<>();
        for (Couple<String,Class> couple : types){
            columnsNamesAndClasses.add(new Couple<String,Class>(couple.getFirst(),couple.getSecond()));
            // Ajoute une arraylist de type type à data
            data.add(new ArrayList<Class>());
        }
    }
}

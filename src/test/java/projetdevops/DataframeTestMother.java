package projetdevops;

import java.util.ArrayList;

public class DataframeTestMother {

    public static Dataframe DataframeTestMother(){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Nom");
        columnNames.add("Nombre");
        columnNames.add("Nombre2");
        columnNames.add("Float");
        columnNames.add("Date");
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("Test");
        D1.add("Test2");
        D1.add("Test3");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("2");
        D2.add("3");
        D2.add("4");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("23");
        D3.add("23");
        D3.add("23");
        ArrayList<String> D4 = new ArrayList<>();
        D4.add("2f");
        D4.add("4f");
        D4.add("8f");
        ArrayList<String> D5 = new ArrayList<>();
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        D5.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        data.add(D4);
        data.add(D5);
        Dataframe test = new Dataframe(data, columnNames);
        return test;
    }
}
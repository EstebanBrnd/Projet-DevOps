package projetdevops;

import java.util.ArrayList;

public class DataframeTestMother {

    public static Dataframe DataframeTestMother(){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Test");
        columnNames.add("Test2");
        columnNames.add("Test3");
        ArrayList<String> D1 = new ArrayList<>();
        D1.add("2");
        D1.add("23");
        D1.add("2f");
        D1.add("7/4/2024");
        ArrayList<String> D2 = new ArrayList<>();
        D2.add("3");
        D2.add("23");
        D2.add("4f");
        D2.add("7/4/2024");
        ArrayList<String> D3 = new ArrayList<>();
        D3.add("4");
        D3.add("23");
        D3.add("8f");
        D3.add("7/4/2024");
        data.add(D1);
        data.add(D2);
        data.add(D3);
        Dataframe test = new Dataframe(data, columnNames);
        return test;
    }
    public static void main(String[] args)
    {
        try {
            Dataframe test = DataframeTestMother();
            test.afficheData();
        } catch (Exception e) {
            System.out.println("Test failed");
        }
    }
}
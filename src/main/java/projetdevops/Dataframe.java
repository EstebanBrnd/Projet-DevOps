package projetdevops;


import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.lang.NumberFormatException;
import java.lang.Integer;
import java.lang.String;
import java.lang.Class;

public class Dataframe {
    ArrayList<Couple<String,Class>> columnsNamesAndClasses;

    ArrayList<ArrayList> data;

    public Dataframe(String filename){
        ArrayList<String> list = extractFile(filename);
        columnsNamesAndClasses = typeInference(list.get(0));
        // Ajout des données dans data
        data = new ArrayList<ArrayList>();
        for (int i = 0; i < columnsNamesAndClasses.size(); i++){
            data.add(new ArrayList());
        }
        for (int i = 0; i < list.size(); i++){
            String[] columns = list.get(i).split(",");
            int j = 0;
            for (String column : columns){
                if (columnsNamesAndClasses.get(j).getSecond() == Integer.class){
                    data.get(j).add(Integer.parseInt(column));
                } else if (columnsNamesAndClasses.get(j).getSecond() == Float.class){
                    data.get(j).add(Float.parseFloat(column));
                } else {
                    data.get(j).add(column);
                }
                j++;
            }
        }
    }

    public void afficheData(){
        for (int i = 0; i < data.size(); i++){
            System.out.println("Colonne " + i + " : " + columnsNamesAndClasses.get(i).getFirst() + " de type " + columnsNamesAndClasses.get(i).getSecond());
            for (int j = 0; j < data.get(i).size(); j++){
                System.out.println("Elt " + j + " : " + data.get(i).get(j));
            }
        }
    }

    public void afficherPremieresLignes() {
        int rowCount = 1;
        for (int i = 0; i < rowCount; i++) {
            System.out.println("Colonne " + i + " : " + columnsNamesAndClasses.get(i).getFirst() + " de type " + columnsNamesAndClasses.get(i).getSecond());
            for (int j = 0; j < data.size(); j++) {
                System.out.println("Elt " + j + " : " + data.get(j).get(i));
            }
        }
    }

    public void afficherDernieresLignes() {
        int rowCount = 1;
        int startingIndex = data.get(0).size() - rowCount; // Calculate the starting index
        for (int i = startingIndex; i < data.get(0).size(); i++) {
            System.out.println("Colonne " + i + " : " + columnsNamesAndClasses.get(i).getFirst() + " de type " + columnsNamesAndClasses.get(i).getSecond());
            for (int j = 0; j < data.size(); j++) {
                System.out.println("Elt " + j + " : " + data.get(j).get(i));
            }
        }
    }

    public ArrayList<String> extractFile(String filename){
        try
		{
			File file = new File(filename);
			FileReader fr = new FileReader(file);  			
			BufferedReader br = new BufferedReader(fr);  
			ArrayList<String> list = new ArrayList<String>();  
			String line;
			while((line = br.readLine()) != null)
			{
				list.add(line);    
			}
			fr.close();    
            return list;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
        throw new IllegalArgumentException("Erreur extraction des données du fichier");
    }

    public ArrayList<Couple<String,Class>> typeInference (String line){
        String[] columns = line.split(",");
        ArrayList<Couple<String,Class>> res = new ArrayList<Couple<String,Class>>();
        int i = 0;
        for (String column : columns){
            try {
                Integer.parseInt(column);
                res.add(new Couple<String,Class>(String.valueOf(i), Integer.class));
            } catch (NumberFormatException e){
                try {
                    Float.parseFloat(column);
                    res.add(new Couple<String,Class>(String.valueOf(i), Float.class));
                } catch (NumberFormatException e2){
                    res.add(new Couple<String,Class>(String.valueOf(i), String.class));
                }
            }
            i++;
        }
        return res;
    }
    

    public Dataframe(ArrayList<Class> types){
        columnsNamesAndClasses = new ArrayList<>();
        data = new ArrayList<>();
        int i = 0;
        for (Class type : types){
            columnsNamesAndClasses.add(new Couple<String,Class>(String.valueOf(i),type));
            // Ajoute une arraylist de type type à data
            data.add(new ArrayList<Class>());
            i++;
        }
    }
}

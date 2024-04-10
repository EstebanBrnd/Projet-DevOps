package projetdevops;


import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.lang.NumberFormatException;
import java.lang.Integer;
import java.lang.Double;
import java.lang.String;
import java.lang.Class;

import projetdevops.Couple;

public class Dataframe {
    ArrayList<Couple<String,Class>> columnsNamesAndClasses;

    ArrayList<ArrayList> data;

    public Dataframe(String filename){
        ArrayList<String> list = extractFile(filename);

        columnsNamesAndClasses = typeInference(list.get(0),list.get(1));
        // Ajout des données dans data
        data = new ArrayList<ArrayList>();
        for (int i = 0; i < columnsNamesAndClasses.size(); i++){
            data.add(new ArrayList());
        }
        for (int i = 1; i < list.size(); i++){
            String[] columns = list.get(i).split(",");
            //System.out.println("Colonne : ");
            int j = 0;
            for (String column : columns){
                // System.out.println("J : " + j + " Column : " + column);
                data.get(j).add(column);
                j++;
            }
        }
        //afficheData();
    }

    public void afficheData(){
        for (int i = 0; i < data.size(); i++){
            System.out.println("Colonne " + i + " : ");
            for (int j = 0; j < data.get(i).size(); j++){
                System.out.println("Elt " + j + " : " + data.get(i).get(j));
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

    public ArrayList<Couple<String,Class>> typeInference (String first_line,String line){
        String[] columns_name = first_line.split(",");
        String[] columns = line.split(",");
        ArrayList<Couple<String,Class>> res = new ArrayList<Couple<String,Class>>();
        int i = 0;
        for (String column : columns){
            try {
                Integer.parseInt(column);
                res.add(new Couple<String,Class>(columns_name[i], Integer.class));
            } catch (NumberFormatException e){
                try {
                    Double.parseDouble(column);
                    res.add(new Couple<String,Class>(columns_name[i], Double.class));
                } catch (NumberFormatException e2){
                    res.add(new Couple<String,Class>(columns_name[i], String.class));
                }
            }
            i++;
        }
        return res;
    }
    

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

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

    public Dataframe iloc(int i){
        ArrayList<Class> types = new ArrayList<>();
        types.add(columnsNamesAndClasses.get(i).getSecond());
        Dataframe iloc = new Dataframe(types);
        for (int j = 0; j < data.get(i).size(); j++){
            iloc.data.get(0).add(data.get(i).get(j));
        }
        return iloc;
    }

    public boolean equals(Dataframe obj) {
        if(this.data.size() != obj.data.size()){
            return false;
        }
        for (int i = 0; i < this.data.size(); i++){
            for (int j = 0; j < this.data.get(i).size(); j++){
                if(!this.data.get(i).get(j).equals(obj.data.get(i).get(j))){
                    return false;
                }
            }
        }
        return true;
    }
}

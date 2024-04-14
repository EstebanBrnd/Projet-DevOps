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
        if(i >= columnsNamesAndClasses.size() ){
            throw new IllegalArgumentException("L'index est supérieur à la taille du dataframe");
        }
        if(i <= 0){
            throw new IllegalArgumentException("L'index est négatif");
        }
        ArrayList<Class> types = new ArrayList<>();
        types.add(columnsNamesAndClasses.get(i).getSecond());
        Dataframe iloc = new Dataframe(types);
        for (int j = 0; j < data.get(i).size(); j++){
            iloc.data.get(0).add(data.get(i).get(j));
        }
        return iloc;
    }

    public Dataframe iloc(int[] integerArray){
        ArrayList<Class> types = new ArrayList<>();
        for (int i : integerArray){
            if(i >= columnsNamesAndClasses.size() ){
                throw new IllegalArgumentException("L'index est supérieur à la taille du dataframe");
            }
            if(i <= 0){
                throw new IllegalArgumentException("L'index est négatif");
            }
            types.add(columnsNamesAndClasses.get(i).getSecond());
        }
        Dataframe iloc = new Dataframe(types);
        int index = 0;
        for (int i : integerArray){
            for (int j = 0; j < data.get(i).size(); j++){
                iloc.data.get(index).add(data.get(i).get(j));
            }
            index ++;
        }
        return iloc;
    }

    public Dataframe iloc(Boolean[] booleans) {
        if(booleans.length > columnsNamesAndClasses.size()){
            throw new IllegalArgumentException("La taille du tableau de booleans doit être inférieure ou égale à la taille du dataframe");
        }

        ArrayList<Class> types = new ArrayList<>();
        for (int i = 0; i < booleans.length; i++){
            if (booleans[i]){
                types.add(columnsNamesAndClasses.get(i).getSecond());
            }
        }
        Dataframe iloc = new Dataframe(types);
        int index = 0;
        for (int i = 0; i < booleans.length; i++){
            if (booleans[i]){
                for (int j = 0; j < data.get(i).size(); j++){
                    iloc.data.get(index).add(data.get(i).get(j));
                }
                index ++;
            }
        }
        return iloc;
    }

    public Dataframe iloc(int i, int j){
        if(i >= columnsNamesAndClasses.size() || j >= data.get(0).size()){
            throw new IllegalArgumentException("L'index est supérieur à la taille du dataframe");
        }
        if( i <= 0 || j <= 0){
            throw new IllegalArgumentException("L'index est négatif");
        }
        ArrayList<Class> types = new ArrayList<>();
        types.add(columnsNamesAndClasses.get(i).getSecond());
        Dataframe iloc = new Dataframe(types);
        iloc.data.get(0).add(data.get(i).get(j));
        return iloc;
    }

    public Dataframe iloc(int[] iS, int[] jS)
    {
        for(int i : iS)
        {
            if(i >= columnsNamesAndClasses.size() ){
                throw new IllegalArgumentException("L'index est supérieur à la taille du dataframe");
            }
            if(i <= 0){
                throw new IllegalArgumentException("L'index est négatif");
            }
        }
        for(int j : jS)
        {
            if(j >= data.get(0).size()){
                throw new IllegalArgumentException("L'index est supérieur à la taille du dataframe");
            }
            if(j <= 0){
                throw new IllegalArgumentException("L'index est négatif");
            }
        }

        ArrayList<Class> types = new ArrayList<>();
        for (int i : iS){
            types.add(columnsNamesAndClasses.get(i).getSecond());
        }
        Dataframe iloc = new Dataframe(types);
        int index = 0;
        for (int i : iS){
            for (int j : jS){
                iloc.data.get(index).add(data.get(i).get(j));
            }
            index ++;
        }
        return iloc;
    }

    public Dataframe iloc(Boolean[] iBooleans, Boolean[] jBooleans) {
        if(iBooleans.length > columnsNamesAndClasses.size() || jBooleans.length > data.get(0).size()){
            throw new IllegalArgumentException("La taille des tableaux de booleans doit être inférieure ou égale à la taille du dataframe");
        }

        ArrayList<Class> types = new ArrayList<>();
        for (int i = 0; i < iBooleans.length; i++){
            if (iBooleans[i]){
                types.add(columnsNamesAndClasses.get(i).getSecond());
            }
        }
        Dataframe iloc = new Dataframe(types);
        int index = 0;
        for (int i = 0; i < iBooleans.length; i++){
            if (iBooleans[i]){
                for (int j = 0; j < data.get(i).size(); j++){
                    if(jBooleans[j])
                        iloc.data.get(index).add(data.get(i).get(j));
                }
                index ++;
            }
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

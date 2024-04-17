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
import java.lang.Float;

import java.io.FileWriter;

public class Dataframe {
    ArrayList<Couple<String,Class>> columnsNamesAndClasses;

    ArrayList<ArrayList> data;
    //dataframe a partir d'un tableau de tableau de class et d'un tableau de nom de colonne

    public Dataframe( ArrayList<ArrayList<String>> data, ArrayList<String> columnNames){
        columnsNamesAndClasses = new ArrayList<>();
        this.data = new ArrayList<>();

        for (int j=0; j < data.size(); j++){
            this.data.add(new ArrayList());
            try {
                Integer.parseInt(data.get(j).get(0));
                columnsNamesAndClasses.add(new Couple<String,Class>(columnNames.get(j), Integer.class));
            } catch (NumberFormatException e){
                try {
                    Float.parseFloat(data.get(j).get(0));
                    columnsNamesAndClasses.add(new Couple<String,Class>(columnNames.get(j), Float.class));
                } catch (NumberFormatException e2){
                    columnsNamesAndClasses.add(new Couple<String,Class>(columnNames.get(j), String.class));
                }
            }
        }
        for (int j=0; j < data.size(); j++){
            for(int i=0; i < data.get(j).size(); i++){
                if (columnsNamesAndClasses.get(j).getSecond() == Integer.class){
                    this.data.get(j).add(Integer.parseInt(data.get(j).get(i)));
                } else if (columnsNamesAndClasses.get(j).getSecond() == Float.class){
                    this.data.get(j).add(Float.parseFloat(data.get(j).get(i)));
                } else {
                    this.data.get(j).add(data.get(j).get(i));
                }
            }

        }
    }

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

    public String afficheData() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            output.append("Colonne ").append(i).append(" : ").append(columnsNamesAndClasses.get(i).getFirst()).append(" de type ").append(columnsNamesAndClasses.get(i).getSecond()).append("\n");
            for (int j = 0; j < data.get(i).size(); j++) {
                output.append("Elt ").append(j).append(" : ").append(data.get(i).get(j)).append("\n");
            }
        }
        return output.toString();
    }


    public String afficherPremieresLignes(int rowCount) {
        if (rowCount > data.get(0).size()){
            throw new IllegalArgumentException("Le nombre de lignes demandé est supérieur au nombre de lignes du dataframe");
        }
        if (rowCount <=0){
            throw new IllegalArgumentException("Le nombre de lignes demandé est négatif ou nul");
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < data.size(); j++) {
                output.append(data.get(j).get(i)).append("\n");
            }
        }
        return output.toString();
    }

    public String afficherDernieresLignes(int rowCount) {
        int startingIndex = data.get(0).size() - rowCount; // Calculate the starting index
        StringBuilder output = new StringBuilder();
        for (int i = startingIndex; i < data.get(0).size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                output.append(data.get(j).get(i)).append("\n");
            }
        }
        return output.toString();
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
                    Float.parseFloat(column);
                    res.add(new Couple<String,Class>(columns_name[i], Float.class));
                } catch (NumberFormatException e2){
                    res.add(new Couple<String,Class>(columns_name[i], String.class));
                }
            }
            i++;
        }
        return res;
    }
    
    public void printFile(String filename, String content){
        // Ajoute le contenu à la fin du fichier
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            myWriter.write(content + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public float mean_colonne(String column){
        int index = -1;
        // Trouve la bonne colonne
        for (int i = 0; i < columnsNamesAndClasses.size(); i++){
            if (columnsNamesAndClasses.get(i).getFirst().equals(column)){
                index = i;
                break;
            }
        }
        // Colonne non trouvée
        if (index == -1){
            throw new IllegalArgumentException("La colonne " + column + " n'existe pas");
        }
        // Calcul de la moyenne
        if (columnsNamesAndClasses.get(index).getSecond() == Integer.class){
            int sum = 0;
            for (int j = 0; j < data.size(); j++){
                sum += (int)data.get(index).get(j);
            }
            return (float)sum/data.size();
        } else if (columnsNamesAndClasses.get(index).getSecond() == Float.class){
            float sum = 0;
            for (int j = 0; j < data.size(); j++){
                sum += (float)data.get(index).get(j);
            }
            return (float)sum/data.size();
        } else {
            throw new IllegalArgumentException("La colonne " + column + " contient des chaines de caractères");
        }

    }

    public ArrayList<Float> mean(){
        ArrayList<Float> res = new ArrayList<Float>();
        for (int i = 0; i < columnsNamesAndClasses.size(); i++){
            if (columnsNamesAndClasses.get(i).getSecond() == Integer.class){
                int sum = 0;
                for (int j = 0; j < data.size(); j++){
                    sum += (int)data.get(i).get(j);
                }
                res.add((float)sum/data.size());
            } else if (columnsNamesAndClasses.get(i).getSecond() == Float.class){
                float sum = 0;
                for (int j = 0; j < data.size(); j++){
                    sum += (float)data.get(i).get(j);
                }
                res.add(sum/data.size());
            } else {
                res.add(null);
            }
        }
        return res;
    }

    public float min_colonne(String column){
        int index = -1;
        for (int i = 0; i < columnsNamesAndClasses.size(); i++){
            if (columnsNamesAndClasses.get(i).getFirst().equals(column)){
                index = i;
                break;
            }
        }
        if (index == -1){
            throw new IllegalArgumentException("La colonne " + column + " n'existe pas");
        }
        if (columnsNamesAndClasses.get(index).getSecond() == Integer.class){
            int min = Integer.MAX_VALUE;
            for (int j = 1; j < data.size(); j++){
                if ((int)data.get(index).get(j) < min){
                    min = (int)data.get(index).get(j);
                }
            }
            return (float)min;
        } else if (columnsNamesAndClasses.get(index).getSecond() == Float.class){
            float min = Float.MAX_VALUE;
            for (int j = 1; j < data.size(); j++){
                if ((float)data.get(index).get(j) < min){
                    min = (float)data.get(index).get(j);
                }
            }
            return min;
        } else {
            throw new IllegalArgumentException("La colonne " + column + " contient des chaines de caractères");
        }

    }

    public ArrayList<Float> min(){
        ArrayList<Float> res = new ArrayList<Float>();
        for (int i = 0; i < columnsNamesAndClasses.size(); i++){
            if (columnsNamesAndClasses.get(i).getSecond() == Integer.class){
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < data.size(); j++){
                    if ((int)data.get(i).get(j) < min){
                        min = (int)data.get(i).get(j);
                    }
                }
                res.add((float)min);
            } else if (columnsNamesAndClasses.get(i).getSecond() == Float.class){
                float min = Float.MAX_VALUE;
                for (int j = 0; j < data.size(); j++){
                    if ((float)data.get(i).get(j) < min){
                        min = (float)data.get(i).get(j);
                    }
                }
                res.add(min);
            } else {
                res.add(null);
            }
        }
        return res;
    }

    public Float max_colonne(String column){
        int index = -1;
        for (int i = 0; i < columnsNamesAndClasses.size(); i++){
            if (columnsNamesAndClasses.get(i).getFirst().equals(column)){
                index = i;
                break;
            }
        }
        if (index == -1){
            throw new IllegalArgumentException("La colonne " + column + " n'existe pas");
        }
        if (columnsNamesAndClasses.get(index).getSecond() == Integer.class){
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < data.size(); j++){
                if ((int)data.get(index).get(j) > max){
                    max = (int)data.get(index).get(j);
                }
            }
            return (float)max;
        } else if (columnsNamesAndClasses.get(index).getSecond() == Float.class){
            float max = Float.MIN_VALUE;
            for (int j = 0; j < data.size(); j++){
                if ((float)data.get(index).get(j) > max){
                    max = (float)data.get(index).get(j);
                }
            }
            return max;
        } else {
            throw new IllegalArgumentException("La colonne " + column + " contient des chaines de caractères");
        }

    }

    public ArrayList<Float> max(){
        ArrayList<Float> res = new ArrayList<Float>();
        for (int i = 0; i < columnsNamesAndClasses.size(); i++){
            if (columnsNamesAndClasses.get(i).getSecond() == Integer.class){
                int max = Integer.MIN_VALUE;
                for (int j = 0; j < data.size(); j++){
                    if ((int)data.get(i).get(j) > max){
                        max = (int)data.get(i).get(j);
                    }
                }
                res.add((float)max);
            } else if (columnsNamesAndClasses.get(i).getSecond() == Float.class){
                float max = Float.MIN_VALUE;
                for (int j = 0; j < data.size(); j++){
                    if ((float)data.get(i).get(j) > max){
                        max = (float)data.get(i).get(j);
                    }
                }
                res.add(max);
            } else {
                res.add(null);
            }
        }
        return res;
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

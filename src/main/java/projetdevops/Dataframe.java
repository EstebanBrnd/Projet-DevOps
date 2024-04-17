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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Dataframe {
    ArrayList<Couple<String,Class>> columnsNamesAndClasses;

    ArrayList<ArrayList> data;
    //dataframe a partir d'un tableau de tableau de class et d'un tableau de nom de colonne

    public ArrayList<String> getColumnNames() {
        ArrayList<String> columnNames = new ArrayList<>();
        for(Couple<String,Class> column : columnsNamesAndClasses){
            columnNames.add(column.getFirst());
        }
        return columnNames;
    }

    public Dataframe concat(Dataframe concat) {
        if(!columnsNamesAndClasses.equals(concat.columnsNamesAndClasses)){
            throw new IllegalArgumentException("Les dataframes n'ont pas les mêmes colonnes");
        }
        for (int i = 0; i < concat.data.size(); i++){
            this.data.get(i).addAll(concat.data.get(i));
        }
        return this;
    }


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

    public Dataframe iloc(int i){
        if(i >= columnsNamesAndClasses.size() ){
            throw new IllegalArgumentException("L'index est supérieur à la taille du dataframe");
        }
        if(i < 0){
            throw new IllegalArgumentException("L'index est négatif");
        }
        ArrayList<ArrayList<String>> donnees = new ArrayList<>();
        for (ArrayList datum : data) {
            ArrayList<String> D1 = new ArrayList<>();
            D1.add(datum.get(i).toString());
            donnees.add(D1);
        }
        ArrayList<String> columnNames = getColumnNames();
        return new Dataframe(donnees,columnNames);
    }

    public Dataframe iloc(int[] integerArray) {
        if (integerArray.length == 0){
            return this;
        }
        for (int i : integerArray) {
            if (i >= columnsNamesAndClasses.size()) {
                throw new IllegalArgumentException("L'index est supérieur à la taille du dataframe");
            }
            if (i < 0) {
                throw new IllegalArgumentException("L'index est négatif");
            }
        }
        Dataframe res = this.iloc(integerArray[0]);
        for (int i = 1; i < integerArray.length; i++) {
            res = res.concat(this.iloc(integerArray[i]));
        }
        return res;
    }

    public Dataframe iloc(Boolean[] booleans) {
        if(booleans.length > columnsNamesAndClasses.size()){
            throw new IllegalArgumentException("La taille du tableau de booleans doit être inférieure ou égale à la taille du dataframe");
        }
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i < booleans.length; i++){
            if (booleans[i]){
                integerArrayList.add(i);
            }
        }
        int[] integerArray = new int[integerArrayList.size()];
        for (int i = 0; i < integerArrayList.size(); i++){
            integerArray[i] = integerArrayList.get(i);
        }
        if(integerArray.length == 0){
            return new Dataframe(new ArrayList<>(), getColumnNames());
        }
        return iloc(integerArray);
    }

    public Dataframe iloc(int i, int j){
        if(i >= columnsNamesAndClasses.size() || j >= data.get(0).size()){
            throw new IllegalArgumentException("L'index est supérieur à la taille du dataframe");
        }
        if( i <= 0 || j <= 0){
            throw new IllegalArgumentException("L'index est négatif");
        }
        ArrayList<ArrayList<String>> donnees = new ArrayList<>();
        ArrayList<String> D1 = new ArrayList<>();
        D1.add(data.get(i).get(j).toString());
        donnees.add(D1);
        ArrayList<String> columnNames = getColumnNames();
        return new Dataframe(donnees,columnNames);
    }

    public Dataframe iloc(int[] iS, int[] jS)
    {
        ArrayList<ArrayList<String>> donnees = new ArrayList<>();
        if(iS.length == 0){
            iS = new int[data.get(0).size()];
            for(int i = 0; i < data.get(0).size(); i++){
                iS[i] = i;
            }
        }
        if(jS.length == 0){
            jS = new int[columnsNamesAndClasses.size()];
            for(int i = 0; i < columnsNamesAndClasses.size(); i++){
                jS[i] = i;
            }
        }
        for(int i : iS)
        {
            if(i >= data.get(0).size() ){
                throw new IllegalArgumentException("L'index est supérieur à la taille du dataframe");
            }
            if(i < 0){
                throw new IllegalArgumentException("L'index est négatif");
            }
        }
        for(int j : jS)
        {
            if(j >= columnsNamesAndClasses.size()){
                throw new IllegalArgumentException("L'index est supérieur à la taille du dataframe");
            }
            if(j < 0){
                throw new IllegalArgumentException("L'index est négatif");
            }
        }

        for(int j : jS)
        {
            ArrayList<String> D1 = new ArrayList<>();
            for(int i : iS)
            {
                D1.add(data.get(j).get(i).toString());
            }
            donnees.add(D1);
        }
        ArrayList<String> columnNames = getColumnNames();
        return new Dataframe(donnees, columnNames);
    }

    public Dataframe iloc(Boolean[] iBooleans, Boolean[] jBooleans) {
        if(iBooleans.length > data.get(0).size() || jBooleans.length > columnsNamesAndClasses.size()){
            throw new IllegalArgumentException("La taille des tableaux de booleans doit être inférieure ou égale à la taille du dataframe");
        }
        ArrayList<Integer> iArrayList = new ArrayList<>();
        ArrayList<Integer> jArrayList = new ArrayList<>();
        for (int i = 0; i < iBooleans.length; i++){
            if (iBooleans[i]){
                iArrayList.add(i);
            }
        }
        for (int i = 0; i < jBooleans.length; i++){
            if (jBooleans[i]){
                jArrayList.add(i);
            }
        }
        int[] iArray = new int[iArrayList.size()];
        for (int i = 0; i < iArrayList.size(); i++){
            iArray[i] = iArrayList.get(i);
        }
        int[] jArray = new int[jArrayList.size()];
        for (int i = 0; i < jArrayList.size(); i++){
            jArray[i] = jArrayList.get(i);
        }
        if(iArray.length == 0 && jArray.length == 0){
            return new Dataframe(new ArrayList<>(), getColumnNames());
        }
        return iloc(iArray, jArray);
    }

    public Dataframe loc(String label){
        for(String columnName : getColumnNames()){
            if (columnName.equals(label)){
                return iloc(new int[]{}, new int[]{getColumnNames().indexOf(label)});
            }
        }
        throw new IllegalArgumentException("La colonne n'existe pas");
    }

    public Dataframe loc(String[] labels){
        ArrayList<Integer> indexes = new ArrayList<>();
        if(labels.length == 0){
            return this;
        }
        for(String label : labels){
            if(!getColumnNames().contains(label)){
                throw new IllegalArgumentException("La colonne" + label + "n'existe pas");
            }
                indexes.add(getColumnNames().indexOf(label));
        }
        int[] indexesArray = new int[indexes.size()];
        for (int i = 0; i < indexes.size(); i++){
            indexesArray[i] = indexes.get(i);
        }
        return iloc(new int[]{}, indexesArray);
    }

    public Dataframe loc(Boolean[] booleans){
        if(booleans.length > columnsNamesAndClasses.size()){
            throw new IllegalArgumentException("La taille du tableau de booleans doit être inférieure ou égale à la taille du dataframe");
        }
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < booleans.length; i++){
            if(booleans[i]){
                indexes.add(i);
            }
        }
        int[] indexesArray = new int[indexes.size()];
        for (int i = 0; i < indexes.size(); i++){
            indexesArray[i] = indexes.get(i);
        }
        if(indexesArray.length == 0){
            return new Dataframe(new ArrayList<>(), getColumnNames());
        }
        return iloc(new int[]{},indexesArray);
    }

    public Dataframe loc(String column, String row){
        if(!data.get(0).contains(row)){
            throw new IllegalArgumentException("La ligne" + row + "n'existe pas");
        }
        int index = data.get(0).indexOf(row);
        return loc(column).iloc(new int[]{index},new int[]{});
    }

    public Dataframe loc(Boolean[] columnBooleans, Boolean[] rowBooleans){
        return iloc(rowBooleans,columnBooleans);
    }

    public boolean equals(Dataframe obj) {
        if(this.data.size() != obj.data.size()){
            return false;
        }
        for (int i = 0; i < this.data.size(); i++){
            if(this.data.get(i).size() != obj.data.get(i).size()){
                return false;
            }
            if(!this.columnsNamesAndClasses.get(i).getFirst().equals(obj.columnsNamesAndClasses.get(i).getFirst())){
                return false;
            }
            if(!this.columnsNamesAndClasses.get(i).getSecond().equals(obj.columnsNamesAndClasses.get(i).getSecond())){
                return false;
            }
            for (int j = 0; j < this.data.get(i).size(); j++){
                if(!this.data.get(i).get(j).equals(obj.data.get(i).get(j))){
                    return false;
                }
            }
        }
        return true;
    }

    public Dataframe linesWithColumnStringEqual(String columnName, String value) {
        if (columnName.isEmpty()) {
            throw new IllegalArgumentException("Column name cannot be empty");
        }
        int columnIndex = -1;
        for (int i = 0; i < columnsNamesAndClasses.size(); i++) {
            if (columnsNamesAndClasses.get(i).getFirst().equals(columnName)) {
                columnIndex = i;
                break;
            }
        }
        if (columnIndex == -1) {
            throw new IllegalArgumentException("Empty dataframe");
        }

        ArrayList<ArrayList<String>> donnees = new ArrayList<>();
        for (int i = 0; i < data.get(0).size(); i++) {
            if (data.get(columnIndex).get(i).equals(value)) {
                ArrayList<String> row = new ArrayList<>();
                for (int j = 0; j < data.size(); j++) {
                    row.add(data.get(j).get(i).toString());
                }
                donnees.add(row);
            }
        }
        ArrayList<String> columnNames = getColumnNames();
        return new Dataframe(donnees,columnNames);
    }

    public Dataframe linesWithColumnIntegerEqual(String columnName, Integer value) {
        if (columnName.isEmpty()) {
            throw new IllegalArgumentException("Column name cannot be empty");
        }
        int columnIndex = -1;
        for (int i = 0; i < columnsNamesAndClasses.size(); i++) {
            if (columnsNamesAndClasses.get(i).getFirst().equals(columnName)) {
                columnIndex = i;
                break;
            }
        }
        if (columnIndex == -1) {
            throw new IllegalArgumentException("Empty dataframe");
        }

        ArrayList<ArrayList<String>> donnees = new ArrayList<>();
        for (int i = 0; i < data.get(0).size(); i++) {
            Object columnValueObject = data.get(columnIndex).get(i);
            Integer columnValue = null;
            if (columnValueObject instanceof Integer) {
                columnValue = (Integer) columnValueObject;
            }
            if (columnValue == null) {
                throw new IllegalArgumentException("Could not read a value from dataframe");
            }
            if (columnValue == value) {
                ArrayList<String> row = new ArrayList<>();
                for (int j = 0; j < data.size(); j++) {
                    row.add(data.get(j).get(i).toString());
                }
                donnees.add(row);
            }
        }
        ArrayList<String> columnNames = getColumnNames();
        return new Dataframe(donnees,columnNames);
    }
    public Dataframe linesWithColumnIntegerGreater(String columnName, Integer value) {
        if (columnName.isEmpty()) {
            throw new IllegalArgumentException("Column name cannot be empty");
        }
        int columnIndex = -1;
        for (int i = 0; i < columnsNamesAndClasses.size(); i++) {
            if (columnsNamesAndClasses.get(i).getFirst().equals(columnName)) {
                columnIndex = i;
                break;
            }
        }
        if (columnIndex == -1) {
            throw new IllegalArgumentException("Empty dataframe");
        }

        ArrayList<ArrayList<String>> donnees = new ArrayList<>();
        for (int i = 0; i < data.get(0).size(); i++) {
            Object columnValueObject = data.get(columnIndex).get(i);
            Integer columnValue = null;
            if (columnValueObject instanceof Integer) {
                columnValue = (Integer) columnValueObject;
            }
            if (columnValue == null) {
                throw new IllegalArgumentException("Could not read a value from dataframe");
            }
            if (columnValue > value) {
                ArrayList<String> row = new ArrayList<>();
                for (int j = 0; j < data.size(); j++) {
                    row.add(data.get(j).get(i).toString());
                }
                donnees.add(row);
            }
        }
        ArrayList<String> columnNames = getColumnNames();
        return new Dataframe(donnees,columnNames);
    }

    public Dataframe linesWithColumnIntegerLess(String columnName, Integer value) {
        if (columnName.isEmpty()) {
            throw new IllegalArgumentException("Column name cannot be empty");
        }
        int columnIndex = -1;
        for (int i = 0; i < columnsNamesAndClasses.size(); i++) {
            if (columnsNamesAndClasses.get(i).getFirst().equals(columnName)) {
                columnIndex = i;
                break;
            }
        }
        if (columnIndex == -1) {
        throw new IllegalArgumentException("Empty dataframe");
        }

        ArrayList<ArrayList<String>> donnees = new ArrayList<>();
        for (int i = 0; i < data.get(0).size(); i++) {
            Object columnValueObject = data.get(columnIndex).get(i);
            Integer columnValue = null;
            if (columnValueObject instanceof Integer) {
                columnValue = (Integer) columnValueObject;
            }
            if (columnValue == null) {
                throw new IllegalArgumentException("Could not read a value from dataframe");
            }
            if (columnValue < value) {
                ArrayList<String> row = new ArrayList<>();
                for (int j = 0; j < data.size(); j++) {
                    row.add(data.get(j).get(i).toString());
                }
                donnees.add(row);
            }
        }
        ArrayList<String> columnNames = getColumnNames();
        return new Dataframe(donnees,columnNames);
    }
    public Dataframe linesWithColumnFloat(String columnName, Float value, String comparisonOperator) {
        if (columnName.isEmpty()) {
            throw new IllegalArgumentException("Column name cannot be empty");
        }
        if (!comparisonOperator.equals("<") && !comparisonOperator.equals(">") && !comparisonOperator.equals("=")) {
            throw new IllegalArgumentException("Invalid comparison operator. Use '<', '>', or '='.");
        }

        int columnIndex = -1;
        for (int i = 0; i < columnsNamesAndClasses.size(); i++) {
            if (columnsNamesAndClasses.get(i).getFirst().equals(columnName)) {
                columnIndex = i;
                break;
            }
        }
        if (columnIndex == -1) {
            throw new IllegalArgumentException("Empty dataframe");
        }
        ArrayList<ArrayList<String>> donnees = new ArrayList<>();
        for (int i = 0; i < data.get(0).size(); i++) {
            Object columnValueObject = data.get(columnIndex).get(i);
            Float columnValue = null;
            if (columnValueObject instanceof Float) {
                columnValue = (Float) columnValueObject;
            } else if (columnValueObject instanceof String) {
                try {
                    columnValue = Float.parseFloat((String) columnValueObject);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
            if (columnValue == null) {
                throw new IllegalArgumentException("Could not read a value from dataframe");
            }
            boolean conditionMet = false;
            switch (comparisonOperator) {
                case "<":
                    conditionMet = columnValue < value;
                    break;
                case ">":
                    conditionMet = columnValue > value;
                    break;
                case "=":
                    conditionMet = Float.compare(columnValue, value) == 0;
                    break;
            }
            if (conditionMet) {
                ArrayList<String> row = new ArrayList<>();
                for (int j = 0; j < data.size(); j++) {
                    row.add(data.get(j).get(i).toString());
                }
                donnees.add(row);
            }
        }
        ArrayList<String> columnNames = getColumnNames();
        return new Dataframe(donnees,columnNames);
    }
}

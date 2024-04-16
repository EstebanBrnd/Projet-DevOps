package projetdevops;

public class Demo {

    public static void main(String[] args) {
        System.out.println("Récupérons un fichier");
        Dataframe test = new Dataframe("src/test/resources/data2.csv");

        System.out.println("Affichons toutes les personnes");
        System.out.println(test.afficheData());

        System.out.println("Affichons la 1ere personne :");
        System.out.println(test.afficherPremieresLignes(1));
        System.out.println("Affichons la derniere personne :");
        System.out.println(test.afficherDernieresLignes(1));

        System.out.println("Voyons quelle est la moyenne d'age");
        System.out.println(test.mean_colonne("Age"));

        System.out.println("Quel est le plus grand age de la liste ?");
        System.out.println(test.max_colonne("Age"));

    }
    
}

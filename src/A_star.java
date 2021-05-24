import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class A_star {


    public String benchmark;
    public int heuristique1(int i,LinkedList<String> clauses){
        /* CETTE HEURISTIQUE h(x) CALCULE LE NOMBRE DE CLAUSES OU X APPARET */
        /* pour ce faire nous allons récupérer le benchmark et vérifier pour chaque clause si i ou -i est présent */
        String litteral= String.valueOf(i);
        String nlitteral= String.valueOf(-i);
        int cpt=0;
        for( i=0;i<clauses.size();i++){
            String line=clauses.get(i);
            if((line !=null) && ( line.contains(litteral) || line.contains(nlitteral))){cpt++;}
        }
        return cpt;
    }
    public LinkedList<String> benchmark_content( String lien){
        /* CETTE FONCTION RETOURNE LES CLAUSES DU FICHIER DANS UNE LISTE DE CHAINES */
        LinkedList <String> list= new LinkedList<>();
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(lien));
            String line = reader.readLine();
            list.add(line);
            while (line != null){
                line=reader.readLine();
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void print_list(LinkedList<String> lst){
        int i =0;
        while (i<lst.size()){
            System.out.println(lst.get(i));
            i++;
        }
    }

    public LinkedList<String> delete_exta_lines(LinkedList<String> lst ){
        /* LE FICHIER CNF PEUT CONTENIR DES COMMENTAIRES EN PLUS DES CLAUSES, RIEN NE SERT DE GARDER LES COMMENTAIRES*/
        for(int i=0;i<lst.size();i++){
            String line= lst.get(i);
            if(line != null){
            if(line.contains("c") || line.contains("p")){
                lst.remove(i);
            }
             }}
        return lst;
    }

}

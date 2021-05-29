import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;


public class A_star {

    private LinkedList<noeud> ouverte;
    private LinkedList<noeud> ferme;
    public LinkedList<String> benchmark; // contient les clauses du benchmark introduit
    private LinkedList<Integer> not_sat; // vecteur initialisé à 0 pour exprimer non-sat des clauses puis toute clause satisfaire sera mise a 1
    private noeud racine;
    // SARRA LAKSACI


    public A_star(String path){
        ouverte=new LinkedList<>();
        ferme=new LinkedList<>();
        not_sat = new LinkedList<>();
        benchmark=new LinkedList<>();
        benchmark=benchmark_content(path);
        benchmark=delete_exta_lines(benchmark);
        racine =new noeud();
        for (int i=0;i<325;i++){
            not_sat.set(i,0);
        }
    }

    public int heuristiqueDLIS(int i){
        /* CETTE HEURISTIQUE h(x) CALCULE LE NOMBRE DE CLAUSES OU X APPARET */
        /* pour ce faire nous allons récupérer le benchmark et vérifier pour chaque clause si i ou -i est présent */
        String litteral= String.valueOf(i);
        String nlitteral= String.valueOf(-i);
        int cpt=0;
        for( i=0;i<benchmark.size();i++){
            String line=benchmark.get(i);
            if((line !=null) && ( line.contains(litteral) || line.contains(nlitteral))){cpt++;}
        }
        return cpt;
    }

    public int heuristiqueBOHM(int i){
        int alpha=1;
        int beta=2;
        int x;  x=3;   return x;  }

    public int heuristique3(){     int x;  x=3;   return x;  }

    public void algorithme(noeud racine){
        int num=1;
        ouverte.add(racine);
        while (ouverte.size()>0)
        { noeud n = ouverte.get(0); ouverte.remove(0); ferme.add(n);
         if(n.getRestant().size()>0){ //si le noeud a des successeurs
         LinkedList<noeud> liste = creer_fils(n,num); // créer les successeurs
          if (liste.size()>0) {
              ouverte.addAll(liste); // insérer les successeurs dans ouverte
                    ordonner_ouverte(ouverte); // ordoner ouverte selon f
                    verif_succes(n.getFils());
          }}}

        //
    }

    public LinkedList<noeud> creer_fils(noeud n,int num){
        LinkedList<noeud> liste=new LinkedList<>();
        for(int i = 0;i<n.getRestant().size();i++){
            int decision=n.getRestant().get(i);
            noeud nouveau = new noeud( n,decision);
            for(int j=0;j<n.getRestant().size();j++)
                { if(j!=i){nouveau.addRestant(j);}}
              switch (num) { // appeler heuristique choisit
                case 1 -> nouveau.setH(heuristiqueDLIS(decision));
                case 2 -> nouveau.setH(heuristiqueBOHM(decision));
                case 3 -> nouveau.setH(heuristique3());
                            }
            liste.add(nouveau);
        }
        n.setFils(liste); //relier fils au père
        return liste;
    }



    public LinkedList<noeud> ordonner_ouverte(LinkedList<noeud> o)  {
        int x,y;
        if(o.size()==1){return o;}
        else{
            int i = 0;
        }return (o);
    }

    public int nbr_clauses_SAT (int decision){
        String litteral= String.valueOf(decision);
        int cpt=0;
        for (String line : benchmark) {
            if ((line != null) && (line.contains(litteral))) {
                cpt++;
            }
        }
        return cpt;
    }

    public void verif_succes (LinkedList<noeud> liste){
        for (noeud n : liste){
            int verif= nbr_clauses_SAT(n.getDecision());
            if(verif==325){ ArrayList<Integer> solution= SolutionRetourArriere(n); }
        }
    }
    
    public ArrayList<Integer> SolutionRetourArriere(noeud n){
        ArrayList<Integer> solution = new ArrayList<>(75);
        noeud avant=n;
        while(avant !=null){
            int x = avant.getDecision();
            if(avant.getDecision()>0){ solution.set(x, 1);}
            if(avant.getDecision()<0){ solution.set(x, 0);}
            avant=avant.getPere();
        }
        return solution;
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

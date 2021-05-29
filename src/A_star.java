import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

// SARRA LAKSACI


public class A_star {

    private LinkedList<noeud> ouverte;
    private LinkedList<noeud> ferme;
    public LinkedList<String> benchmark; // contient les clauses du benchmark introduit
    private LinkedList<Integer> not_sat; // vecteur initialisé à 0 pour exprimer non-sat des clauses puis toute clause satisfaire sera mise a 1
    private noeud racine;
    ArrayList<Integer> solution;


    public A_star(String path){
        ouverte=new LinkedList<>();
        ferme=new LinkedList<>();
        not_sat = new LinkedList<>();
        benchmark=new LinkedList<>();
        benchmark=benchmark_content(path);
        benchmark=delete_exta_lines(benchmark);
        solution=new ArrayList<Integer>();
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

    public boolean algorithme(noeud racine){
        int num=1;
        ouverte.add(racine);
        while (ouverte.size()>0)
        { noeud n = ouverte.get(0); ouverte.remove(0); ferme.add(n);
         if(n.getRestant().size()>0){ //si le noeud a des successeurs
         LinkedList<noeud> liste = creer_fils(n,num); // créer les successeurs
          if (liste.size()>0) {
              ouverte.addAll(liste); // insérer les successeurs dans ouverte
                    ordonner_ouverte(ouverte,0,ouverte.size()); // ordoner ouverte selon f
                    boolean succes= verif_succes(n.getFils());
                    if(succes){ solution= SolutionRetourArriere(n); return true;}
          }}} return false;

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


    // tri fusion
    public void ordonner_ouverte(LinkedList<noeud> o,int l,int r)  {
        int mid;
        if(r>l){
            mid=l+(r-l)/2;
            ordonner_ouverte(o,l,mid);
            ordonner_ouverte(o,mid+1,r);
            merge(o,l,mid,r);
        }
    }
    public void merge(LinkedList<noeud> o,int l,int m,int r){
        int n1 = m - l + 1;
        int n2 = r - m;

        ArrayList<noeud> L=new ArrayList<>(n1);
        ArrayList<noeud> R=new ArrayList<>(n1);

        for (int i = 0; i < n1; i++)
            L.set(i, o.get(l + i));
        for (int j = 0; j < n2; j++)
            R.set(j, o.get(m + 1 + j));

        int i = 0;
        int j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L.get(i).getF() <= R.get(j).getF()) { o.set(k, L.get(i));    i++;  }
            else {  o.set(k, R.get(j));    j++; }
            k++;    }

        while (i < n1) { o.set(k, L.get(i));  i++;  k++;  }
        while (j < n2) { o.set(k, R.get(j));  j++;  k++;  }
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

    public boolean verif_succes (LinkedList<noeud> liste){
        boolean flag=false;
        for (noeud n : liste){
            int verif= nbr_clauses_SAT(n.getDecision());
            if(verif==325){ flag=true; }
        }
        return flag;
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

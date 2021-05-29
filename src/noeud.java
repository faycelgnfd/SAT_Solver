import java.util.ArrayList;
import java.util.LinkedList;

// SARRA LAKSACI

public class noeud {


    private int f;
    private int h;
    private int g;
    private noeud pere=null;
    private LinkedList<noeud> fils;
    private int decision; // represente la décision prise exemple value= -2 cela signifie qu'on a attribué à x2 la valeur 0
    private ArrayList<Integer> restant;

    public noeud (noeud pere,int decision){ //tout noeud a un pere
        f=0;
        g=pere.getG()+1;
        h=0;
        this.decision=decision;
        this.pere=pere;
        fils=new LinkedList<>();
        restant=new ArrayList<>();
        for(int i=0;i<75;i++){
            restant.set(i, 1);
        }
    }

    public noeud (){ // excepté la racine
        f=0;
        g=0;
        h=0;
        decision=0;
        this.pere=null;
        fils=new LinkedList<>();
        restant=new ArrayList<>();
        for(int i=0;i<75;i++){
            restant.add(+i);
            restant.add(-i);
        }    }


    public void setH(int h) {   this.h = h; this.f=this.g+this.h; }
    public int getG() {      return g;   }
    public int getF() {     return f;  }
    public noeud getPere() {     return pere;  }
    public LinkedList<noeud> getFils() {   return fils;  }
    public void setFils(LinkedList<noeud> fils) {  this.fils = fils; }
    public int getDecision() {  return decision;  }
    public void setDecision(int decision) {this.decision = decision; }
    public ArrayList<Integer> getRestant() {     return restant;  }
    public void addRestant(int x){ restant.add(x);}
}

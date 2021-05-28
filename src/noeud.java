import java.util.LinkedList;

public class noeud {

    // SARRA LAKSACI
    private int f;
    private int h;
    private int g;
    private noeud pere=null;
    private LinkedList<noeud> fils;
    private int decision; // represente la décision prise exemple value= -2 cela signifie qu'on a attribué à x2 la valeur 0

    public noeud (noeud pere){ //tout noeud a un pere
        f=0;
        g=0;
        h=0;
        decision=0;
        this.pere=pere;
        fils=new LinkedList<>();
    }
    public noeud (){ // excepté la racine
        f=0;
        g=0;
        h=0;
        decision=0;
        this.pere=null;
        fils=new LinkedList<>();
    }

    public noeud(int h,int g){
        this.h=h;
        this.g=g;
        f=h+g;
    }

}

public class noeud {
    private int f;
    private int h;
    private int g;


    public noeud(int h,int g){
        this.h=h;
        this.g=g;
        f=h+g;
    }

}

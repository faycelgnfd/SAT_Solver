import java.util.LinkedList;

public class Tests {

    public static void main( String [] args) {


        A_star A =new A_star();
        String lien= "C:/Users/Dell/OneDrive/Bureau/LKSC/USTHB/S8/RC/UBCSAT/example1.cnf";
        LinkedList<String> list=A.benchmark_content(lien);
        list= A.delete_exta_lines(list);
        A.print_list(list);
        System.out.println(A.heuristique1(3,list));
    }
}

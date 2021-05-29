import java.util.LinkedList;

public class Tests {

    public static void main( String [] args) {



        String lien= "C:/Users/Dell/OneDrive/Bureau/LKSC/USTHB/S8/RC/UBCSAT/example1.cnf";
        A_star A =new A_star(lien);
        LinkedList<String> list=A.benchmark_content(lien);
        list= A.delete_exta_lines(list);
        A.print_list(list);
        System.out.println(A.heuristiqueDLIS(3));
    }
}

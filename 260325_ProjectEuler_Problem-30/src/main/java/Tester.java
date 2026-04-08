import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Solver solver = new Solver();

        for(int i = 0; i < 10_000_000; i++) {
            if(solver.solve(i)) {
                list.add(i);
            }
        }

        System.out.println(list);
    }
}

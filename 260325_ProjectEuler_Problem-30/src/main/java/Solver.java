import java.util.ArrayList;

public class Solver {
    public boolean solve(int number){
        ArrayList<Integer> digits = new ArrayList<>();
        int temp = number;

        while (temp > 0) {
            digits.add(number % 10);
            temp /= 10;
        }

        int result = 0;
        for(int num : digits){
            if(num != 1){
                result += num^4;
            }
        }

        return result == number;
    }
}
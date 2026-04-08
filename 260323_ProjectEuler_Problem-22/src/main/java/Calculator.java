public class Calculator {
    public int calculateWorth(String name){
        int result = 0;

        for (int i = 0; i < name.length(); i++) {
            char part = name.charAt(i);
            int worth = part - 'A' + 1;
            result += worth;
        }

        System.out.print(result);

        return result;
    }
}

public class Stack {

    private int[] values;
    public int index;

    public Stack(int amount) {
        this.values = new int[amount];
        this.index = -1;
    }

    public boolean isEmpty() {
        return index == -1;
    }

    public boolean isFull() {
        return values.length - 1 == index;
    }

    public void push(int value){
        if(!isFull()){
            this.index++;
            this.values[index] = value;
        } else {
            throw new RuntimeException("Stack is full");
        }
    }

    public int pop(){
        if(!isEmpty()){
            return values[index--];
        } else {
            throw new RuntimeException("Stack is empty");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < index; i++){
            sb.append("%02d %2d\n", i, values[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Stack stack = new Stack(10);

        stack.push(1);
        stack.push(23);
        stack.push(34);

        System.out.println(stack.toString());
    }
}

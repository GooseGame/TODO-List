public class Main {
    public static void main(String[] args) {
        DB.connect();
        while (true) Iteration.newIteration();
    }
}

import java.util.*;
import java.io.*;

public class Round {
    public static void main(String args[]) {
        Vector<Integer> input = new Vector<Integer>();
        int numCities;
        int numCars;
        try {
            File inputFile = new File(args[0]);
            Scanner scanner = new Scanner(inputFile);
            numCities = scanner.nextInt();
            numCars = scanner.nextInt();
            for (int i = 0; i < numCars; i++) {
                input.add(scanner.nextInt());
            }
            Solver solver = new BFSolver();
            State initial = new RoundState(input, null, numCities);
            State result = solver.solve(initial);
            movesNum(result);
            result.getCity();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    private static void movesNum(State s) {
        int count = 0;
        State index  = new RoundState(s);
        while (index.getPrevious() != null) {
            count++;
            index = index.getPrevious();

        }
        System.out.print(count);
        System.out.print(" ");
    }
}

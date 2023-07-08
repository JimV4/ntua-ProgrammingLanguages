import java.util.*;
import java.io.*;
public class QSsort {
    public static void main (String args[]) {

        Solver solver = new BFSolver();
        List<Integer> initialQueue = new ArrayList<Integer>();
        List<Integer> initialStack = new ArrayList<Integer>();
        String initialSequence = "";

        // take input from file
        try {
            File inputFile = new File(args[0]);
            Scanner scanner = new Scanner(inputFile);
            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                initialQueue.add(scanner.nextInt());
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        State initial = new QSsortState(initialQueue, initialStack, initialSequence, null);
        State result = solver.solve(initial);
        if (result == null) {
            System.out.println("empty");
        }
        else {
            //System.out.println(result.printState());
            result.printState();
        }
    }
}

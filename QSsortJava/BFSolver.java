import java.util.*;

public class BFSolver implements Solver {
    @Override
    public State solve(State initial) {
        Queue<State> remaining = new ArrayDeque<State>();
        Set<State> seen = new HashSet<State>();
        remaining.add(initial);
        seen.add(initial);
        if (initial.isFinal()) return null;
        while (!remaining.isEmpty()) {
            State s = remaining.remove();
            if (s.isFinal()) return s;
            for (State n : s.next()) {
                if (!seen.contains(n)) {
                    remaining.add(n);
                    seen.add(n);
                    n.printState();
                }
            }
        }
        return null;
    }
}

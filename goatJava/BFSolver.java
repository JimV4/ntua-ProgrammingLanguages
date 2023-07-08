import java.util.*;

public class BFSolver implements Solver {
    @Override
    public State solve(State initial) {
        Queue<State> remaining = new ArrayDeque<State>();
        Set<State> seen = new HashSet<>();
        remaining.add(initial);
        seen.add(initial);
        while (!remaining.isEmpty()) {
            State s = remaining.remove();
            if (s.isFinal()) return s;
            for (State n : s.next()) {
                if (!seen.contains(n) && !n.isBad()) {
                    remaining.add(n);
                    seen.add(n);
                }
            }
        }
        return null;
    }
}

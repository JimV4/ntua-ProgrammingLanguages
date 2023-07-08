import java.util.*;

public class GoatState implements State {
    // false = west, true = east
    private boolean man, wolf, goat, cabbage;
    private State previous;

    public GoatState(boolean m, boolean w, boolean g, boolean c, State p) {
        man = m; wolf = w; goat = g; cabbage = c;
        previous = p;
    }

    @Override
    public boolean isFinal() {
        return man && wolf && goat && cabbage;
    }

    @Override
    public boolean isBad() {
        return wolf == goat && man != goat
            || goat == cabbage && man != cabbage;
    }

    @Override
    public Collection<State> next() {
        Collection<State> states = new ArrayList<>();
        states.add(new GoatState(!man, wolf, goat, cabbage, this));
        if (man == wolf)
            states.add(new GoatState(!man, !wolf, goat, cabbage, this));
        if (man == goat)
            states.add(new GoatState(!man, wolf, !goat, cabbage, this));
        if (man == cabbage)
            states.add(new GoatState(!man, wolf, goat, !cabbage, this));
        return states;
    }

    @Override
    public State getPrevious() {
        return previous;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("State: ");
        sb.append("man=").append(man ? "e" : "w");
        sb.append(", wolf=").append(wolf ? "e" : "w");
        sb.append(", goat=").append(goat ? "e" : "w");
        sb.append(", cabbage=").append(cabbage ? "e" : "w");
        return sb.toString();
    }
}

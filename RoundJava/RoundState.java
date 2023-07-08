import java.util.*;

public class RoundState implements State {
    private Vector<Integer> cars = new Vector<Integer>();
    private State previous;
    private int index;  // which car moved to get to this state from the previous one
    private int numCities;

    // Constructor
    public RoundState(Vector v, State p, int n) {
        cars = v;
        previous = p;
        numCities = n;
    }

    RoundState(State s) {
        RoundState s2 = (RoundState) s;
        cars = s2.cars;
        previous = s2.previous;
        numCities = s2.numCities;
    }

    @Override
    public boolean isFinal() {
        int val = cars.get(0);
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i) != val) return false;
        }
        return true;
    }

    @Override
    public boolean isBad() {
        try {
            State a = this.getPrevious();
            RoundState prev = (RoundState) a;
            State a2 = prev.getPrevious();
            RoundState prev2 = (RoundState) a2;
            for (int i = 0; i < cars.size(); i++) {
                if (this.cars.get(i) != prev.cars.get(i) && prev.cars.get(i) != prev2.cars.get(i))
                    return true;
            }
            return false;
        }
        catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public State getPrevious() {
        return previous;
    }

    @Override
    public Collection<State> next() {
        int a;
        Collection<State> states = new ArrayList<State>();
        int numCars = cars.size();
        for (int i = 0; i < numCars; i++) {
            Vector<Integer> newVector = new Vector<Integer>(cars);
            if (newVector.get(i) == numCities - 1 && newVector.get(i) != 0) {
                newVector.set(i, 0);
            }
            else {
                a = newVector.get(i);
                newVector.set(i, a + 1);
            }
            State newState = new RoundState(newVector, this, this.numCities);
            states.add(newState);

            /*Vector<Integer> newVector2 = new Vector<Integer>(cars);
            if (newVector2.get(i) == 0) {
                newVector2.set(i, numCities - 1);
            }
            else {
                a = newVector2.get(i);
                newVector2.set(i, a - 1);
            }
            State newState2 = new RoundState(newVector2, this, this.numCities);
            states.add(newState2);*/

            //newState.printState();
            //newState2.printState();
        }
        return states;
    }

    @Override
    public void getCity() {
        System.out.println(cars.get(0));
    }

    private boolean vectorEquality(Vector<Integer> v) {
        try {
            boolean result = true;
            for (int i = 0; i < cars.size(); i++) {
                if (v.get(i) != cars.get(i)) {
                    result = false;
                    break;
                }
            }
            return result;
        }
        catch (IndexOutOfBoundsException e) {
            if (cars.size() == v.size())
                return true;
            else
                return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundState other = (RoundState) o;
        return this.vectorEquality(other.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }

    @Override
    public void printState() {
        for (int i = 0; i < cars.size(); i++) {
            System.out.print(cars.get(i));
            System.out.print(" ");
        }
        System.out.println("");
    }
}

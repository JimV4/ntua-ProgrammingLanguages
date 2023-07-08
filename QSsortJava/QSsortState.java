import java.util.*;

public class QSsortState implements State {
    private List<Integer> queue = new ArrayList<Integer>();
    private List<Integer> stack = new ArrayList<Integer>();
    private String sequence;

    private State previous;

    public QSsortState(List<Integer> q, List<Integer> s, String seq, State prev) {
        queue = q; stack = s; sequence = seq; previous = prev;
    }

    /*@Override
    public String printState() {
        return sequence;
    }*/

    @Override
    public void printState() {
        System.out.print(sequence);
        System.out.print(" Queue: ");
        for (int i = 0; i < queue.size(); i++) {
            System.out.print(" ");
            System.out.print(queue.get(i));
        }
        System.out.print(" Stack: ");
        for (int i = 0; i < stack.size(); i++) {
            System.out.print(" ");
            System.out.print(stack.get(i));
        }
        System.out.println("");
    }



    @Override
    public boolean isFinal() {
        boolean sorted = true;
        try {
            for (int i = 0; i < queue.size() - 1; i++) {
                if (queue.get(i) > queue.get(i + 1)) {
                    sorted = false;
                    break;
                }
            }
            return sorted && stack.isEmpty();
        }
        catch(IndexOutOfBoundsException e) {
            return true;
        }
    }

    @Override
    public State getPrevious() {
        return previous;
    }

    @Override
    public Collection<State> next() {
        Collection<State> states = new ArrayList<State>();

        try {
            // Q move
            String newSequence = sequence + 'Q';
            List<Integer> newQueue = new ArrayList<Integer>(this.queue);
            int element = newQueue.remove(0);
            List<Integer> newStack = new ArrayList<Integer>(this.stack);
            newStack.add(element);
            states.add(new QSsortState(newQueue, newStack, newSequence, this));

            // S move
            String newSequence2 = sequence + 'S';
            List<Integer> newStack2 = new ArrayList<Integer>(this.stack);
            int element2 = newStack2.remove(newStack2.size() - 1);
            List<Integer> newQueue2 = new ArrayList<Integer>(this.queue);
            newQueue2.add(element2);
            states.add(new QSsortState(newQueue2, newStack2, newSequence2, this));
        }
        catch(EmptyStackException e) {
            return states;
        }
        catch(NoSuchElementException e) {
            return states;
        }
        catch(IndexOutOfBoundsException e) {
            return states;
        }

        return states;
    }

    private boolean queueEquality(List<Integer> q) {
        try {
            boolean result = true;
            for (int i = 0; i < queue.size(); i++) {
                if (q.get(i) != queue.get(i)) {
                    result = false;
                    break;
                }
            }
            return result;
        }
        catch (IndexOutOfBoundsException e) {
            if (queue.size() == q.size())
                return true;
            else
                return false;
        }
    }

    private boolean stackEquality(List<Integer> s) {
        try {
            boolean result = true;
            for (int i = 0; i < stack.size(); i++) {
                if (s.get(i) != stack.get(i)) {
                    result = false;
                    break;
                }
            }
            return result;
        }
        catch (IndexOutOfBoundsException e) {
            if (queue.size() == s.size())
                return true;
            else
                return false;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QSsortState other = (QSsortState) o;
        //return queue.equals(other.queue) && stack.equals(other.stack);
        return this.queueEquality(other.queue) && this.stackEquality(other.stack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queue, stack);
    }
}

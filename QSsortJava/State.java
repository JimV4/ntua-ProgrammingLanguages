import java.util.Collection;

public interface State {
    public boolean isFinal();
    public Collection<State> next();
    public State getPrevious();
    //public String printState();
    public void printState();
}

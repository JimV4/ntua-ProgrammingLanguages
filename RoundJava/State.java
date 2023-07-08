import java.util.Collection;

public interface State {
    public boolean isFinal();
    public boolean isBad();
    public Collection<State> next();
    public State getPrevious();
    public void getCity();
    public void printState();
}

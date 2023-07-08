public class Goat {
    public static void main(String args[]) {
        Solver solver = new BFSolver();
        State initial = new GoatState(false, false, false, false, null);
        State result = solver.solve(initial);
        if (result == null) {
            System.out.println("No solution found.");
        }
        else {
            printSolution(result);
        }
    }

    private static void printSolution(State s) {
        if (s.getPrevious() != null) {
            printSolution(s.getPrevious());
        }
        System.out.println(s);
    }
}
Αν η ερωτηση δεν ειναι required και ειναι ερωτηση συμπληρωσης τι θα εμφανιστει στα στατιστικα?

Το λογισμικό inteliQ ανααπτύχθηκε με σκοπό την δημιουργία "έξυπνων" ερωτηματολογίων και την συμπλήρωση αυτών. Ως έξυνπο χαρακτηριζεται μπλα μπλα... 
Για το λογισμικο διακρινουμε 2 κατηγοριες χρηστων, τον διαχειριστη, ο οποίος εχει δικαιωματα δημιουργιας ερωτηματολογιου, εμφανισης στατιστικων και 
δυνατοτητα θεασης ιστορικου απαντησεων, και τον απλο χρηστη, που απανταει τα ερωτηματολογια.
Οι λειτουργιες που εκτελει ο διαχειριστης πραγματοποιουνται με διαδραστικη αλληλεπιδραση με το λογισμικο μεσω περιβαλλοντος Frontend και μεσω εντολων
σε CLI (Command Line Interface).
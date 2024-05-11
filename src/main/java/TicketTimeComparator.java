import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket o1, Ticket o2) {
        int timeOne = o1.getTimeTo() - o1.getTimeFrom();
        int timeTwo = o2.getTimeTo() - o2.getTimeFrom();
        if (timeOne < timeTwo) {
            return -1;
        } else if (timeOne > timeTwo) {
            return 1;
        } else {
            return 0;
        }
    }
}

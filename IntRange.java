import java.util.ArrayList;
import java.util.Iterator;

public class IntRange {
    ArrayList<Integer> list;

    public IntRange(int start, int end) {
        this.list = new ArrayList<Integer>();
        for (; start <= end; start++) {
            this.list.add(start);
        }
    }

    public Iterator<Integer> iterator() {
        return list.iterator();
    }

    public int size() {
        return list.size();
    }
}

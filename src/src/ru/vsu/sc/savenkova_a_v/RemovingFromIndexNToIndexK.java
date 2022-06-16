package src.ru.vsu.sc.savenkova_a_v;

public class RemovingFromIndexNToIndexK {

    public void removeFromNumberNToNumberK(int fromIndex, int toIndex, java.util.LinkedList<Double> list) throws LinkedList.LinkedListException {

        if ((fromIndex < 0 || fromIndex > list.size()) || (toIndex < 0 || toIndex > list.size())) {
            throw new LinkedList.LinkedListException("The index is incorrect: out of list bounds.");
        } else if (toIndex < fromIndex) {
            throw new LinkedList.LinkedListException("The second number cannot be greater than the first.");
        }

        if ((toIndex == fromIndex) && (fromIndex == 0)) {
            list.removeFirst();
        } else if ((toIndex == fromIndex) && (fromIndex == list.size() - 1)) {
            list.removeLast();
        } else if (toIndex == fromIndex) {
            list.remove(toIndex);
        } else if ((fromIndex == 0) && (toIndex == list.size() - 1)) {
            for (int i = list.size() - 1; i >= 0; i--) {
                list.remove(i);
            }
        } else {
            for (int i = toIndex; i >= fromIndex; i--) {
                list.remove(i);
            }
        }
    }
}

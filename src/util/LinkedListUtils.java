package util;



import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LinkedListUtils {

    public static LinkedList<Double> doubleArrayToList(double[] array) {
        LinkedList<Double> list = new LinkedList<Double>();
        for (double j : array) {
            list.addLast(j);
        }
        return list;
    }

    public static double[] doubleListToArray(LinkedList<Double> list) throws src.ru.vsu.sc.savenkova_a_v.LinkedList.LinkedListException {
        double[] array = new double[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static LinkedList<Double> readLListFromJTable(JTable table) {
        StringBuilder sb = new StringBuilder();
        TableModel tableModel = table.getModel();

        int numberOfColumns = tableModel.getColumnCount();

        for (int i = 0; i < numberOfColumns; i++) {
            sb.append(tableModel.getValueAt(0, i).toString());
            sb.append(" ");
        }

        return convertToList(sb.toString());
    }

    private static LinkedList<Double> convertToList(String unsortedList) {
        LinkedList<Double> sortedList = new LinkedList<>();

        if (unsortedList != null) {
            Scanner scn = new Scanner(unsortedList);
            scn.useDelimiter("(\\s|,)+");

            while (scn.hasNext())
                if (!scn.hasNextInt()) {
                    return null;
                } else {
                    sortedList.addLast((double) scn.nextInt());
                }
        } else {
            return null;
        }

        return sortedList;
    }

    public static int[] toIntArray(String str) {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter("(\\s|[,;])+");
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextInt());
        }

        // из List<Integer> можно получить Integer[]
        Integer[] arr = list.toArray(new Integer[0]);
        // Integer[] -> int[]
        return ArrayUtils.toPrimitive(arr);
    }

    public static LinkedList<Integer> readLListFromFile(String fileName) throws FileNotFoundException {
        Scanner scn = new Scanner(new File(fileName));
        String unsortedList;
        unsortedList = scn.nextLine();

        return convertToLList(unsortedList);
    }

    private static LinkedList<Integer> convertToLList(String unsortedList) {
        LinkedList<Integer> sortedList = new LinkedList<>();

        if (unsortedList != null) {
            Scanner scn = new Scanner(unsortedList);
            scn.useDelimiter("(\\s|,)+");

            while (scn.hasNext())
                if (!scn.hasNextInt()) {
                    return null;
                } else {
                    sortedList.addLast(scn.nextInt());
                }
        } else {
            return null;
        }

        return sortedList;
    }

    public static int[] convertToIntArray(LinkedList<Integer> LList) {
        int[] arr = new int[LList.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = LList.get(i);
        }

        return arr;
    }

    public static void fillArrayRandomNumbers(int[] arr) {
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
    }
}

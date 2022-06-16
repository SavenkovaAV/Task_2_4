package util;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ListUtils {
    public static List<Integer> readListFromJTable(JTable table) {
        StringBuilder sb = new StringBuilder();
        TableModel tableModel = table.getModel();

        int numberOfColumns = tableModel.getColumnCount();

        for (int i = 0; i < numberOfColumns; i++) {
            sb.append(tableModel.getValueAt(0, i).toString());
            sb.append(" ");
        }

        return convertToList(sb.toString());
    }

    public static List<Integer> readListFromFile(String fileName) throws FileNotFoundException {
        Scanner scn = new Scanner(new File(fileName));
        String unsortedList;
        unsortedList = scn.nextLine();

        return convertToList(unsortedList);
    }

    private static List<Integer> convertToList(String unsortedList) {
        List<Integer> sortedList = new ArrayList<>();

        if (unsortedList != null) {
            Scanner scn = new Scanner(unsortedList);
            scn.useDelimiter("(\\s|,)+");

            while (scn.hasNext())
                if (!scn.hasNextInt()) {
                    return null;
                } else {
                    sortedList.add(scn.nextInt());
                }
        } else {
            return null;
        }

        return sortedList;
    }

    public static void writeListToFile(String fileName, List<Integer> list) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(fileName);

        String res = convertListToString(list);
        out.print(res);
        out.close();
    }

    public static void printListInConsole(List<Integer> list) {
        String res = convertListToString(list);
        System.out.print(res);
    }

    public static void fillArrayRandomNumbers(int[] arr) {
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
    }

    public static int[] convertToIntArray(List<Integer> list) {
        int[] arr = new int[list.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    public static String convertListToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();

        int numOfMembers = list.size();

        for (Integer i : list) {
            numOfMembers--;
            sb.append(i);

            if (numOfMembers != 0) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}

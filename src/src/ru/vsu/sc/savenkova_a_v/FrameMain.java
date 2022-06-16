package src.ru.vsu.sc.savenkova_a_v;

import util.JTableUtils;
import util.LinkedListUtils;
import util.ListUtils;
import util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FrameMain extends JFrame{
    private JPanel PanelMain;
    private JTable tableSource;
    private JButton buttonLoadFromFile;
    private JButton buttonSaveFile;
    private JButton buttonFillWithRandomNumbers;
    private JButton buttonRemoveFromList;
    private JTable tableResult;
    private JButton buttonSaveResult;
    private JTable tableIndexN;
    private JTable tableIndexK;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;

    public FrameMain() {

        this.setTitle("Task 2");
        this.setContentPane(PanelMain);
        this.setBounds(600, 250, 300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableSource, 40, true, true, false, true, 25, 5);
        JTableUtils.initJTableForArray(tableResult, 40, true, true, false, true, 25, 5);
        JTableUtils.initJTableForArray(tableIndexN, 50, false, false, false, false);
        JTableUtils.initJTableForArray(tableIndexK, 50, false, false, false, false);

        tableSource.setRowHeight(30);
        tableResult.setRowHeight(30);
        tableIndexN.setRowHeight(50);
        tableIndexK.setRowHeight(50);

        DefaultTableModel defaultTableModel = (DefaultTableModel) tableSource.getModel();
        defaultTableModel.setRowCount(10);
        tableSource.setModel(defaultTableModel);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        buttonLoadFromFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserOpen.showOpenDialog(PanelMain) == JFileChooser.APPROVE_OPTION) {
                    LinkedList<Integer> List = LinkedListUtils.readLListFromFile(fileChooserOpen.getSelectedFile().getPath());
                    JTableUtils.writeArrayToJTable(tableSource, LinkedListUtils.convertToIntArray(List));
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        buttonSaveFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(PanelMain) == JFileChooser.APPROVE_OPTION) {
                    List<Integer> list = ListUtils.readListFromJTable(tableSource);

                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ListUtils.writeListToFile(file, list);
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        buttonFillWithRandomNumbers.addActionListener(actionEvent -> {
            int[] arr = new int[tableSource.getColumnCount()];
            ListUtils.fillArrayRandomNumbers(arr);
            JTableUtils.writeArrayToJTable(tableSource, arr);
        });

        buttonSaveResult.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(PanelMain) == JFileChooser.APPROVE_OPTION) {
                    List<Integer> list = ListUtils.readListFromJTable(tableResult);

                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ListUtils.writeListToFile(file, list);
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        buttonRemoveFromList.addActionListener(actionEvent -> {
            try {
                RemovingFromIndexNToIndexK removingFromIndexNToIndexK = new RemovingFromIndexNToIndexK();

                double[] array = JTableUtils.readDoubleArrayFromJTable(tableSource);
                assert array != null;
                LinkedList<Double> list = LinkedListUtils.doubleArrayToList(array);
                int[] indexN = JTableUtils.readIntArrayFromJTable(tableIndexN);
                int[] indexK = JTableUtils.readIntArrayFromJTable(tableIndexK);

                removingFromIndexNToIndexK.removeFromNumberNToNumberK(indexN[0], indexK[0], list);

                double[] result = LinkedListUtils.doubleListToArray(list);
                JTableUtils.writeArrayToJTable(tableResult,result);
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });
    }

}

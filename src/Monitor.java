import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class Monitor {

    private final int COLUMNS = 10;
    private final int ROWS = 10;
    private final char[][] dados = new char[ROWS][COLUMNS];
    private JFrame frame;
    private JTable tableMonitor;

    public Monitor() {
        initDados();
        initTela();
    }

    public static void main(String[] args) {
        new Monitor();
    }

    public void out(int row, int col, char c) {
        this.dados[row][col] = c;
    }

    private void initDados() {
        final char b = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                dados[i][j] = b;
            }
        }
    }

    private void initTela() {
        frame = new JFrame("Monitor");
        frame.setLayout(new BorderLayout());
        tableMonitor = new JTable(new TableModel());
        final JScrollPane painelPrincipal = new JScrollPane(tableMonitor);
        frame.getContentPane().add(painelPrincipal, BorderLayout.CENTER);
        frame.setMinimumSize(new Dimension(500, 350));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    class TableModel extends DefaultTableModel {

        private static final long serialVersionUID = 1L;

        @Override
        public int getRowCount() {
            return ROWS;
        }

        @Override
        public String getValueAt(int row, int column) {
            if (dados == null) {
                return "";
            }
            return String.valueOf(dados[row][column]);
        }

        @Override
        public int getColumnCount() {
            return COLUMNS;
        }

        @Override
        public String getColumnName(int column) {
            return "";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    public void repaint() {
        frame.revalidate();
        frame.repaint();
        tableMonitor.revalidate();
        tableMonitor.repaint();
    }

}
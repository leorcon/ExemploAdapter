package visual;

import javax.swing.table.DefaultTableModel;

public class TableModelNonEditable extends DefaultTableModel {

    public TableModelNonEditable(String[] tabelaResultadosColunas, int i) {
        super(tabelaResultadosColunas, i);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}

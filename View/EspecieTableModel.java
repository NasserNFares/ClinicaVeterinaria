package view;

import java.util.List;
import Model.Especie;
import Model.EspecieDAO;

public class EspecieTableModel extends GenericTableModel {

    public EspecieTableModel(List vDados) {
        super(vDados, new String[]{"ID", "Nome"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class; // ID é do tipo int
            case 1:
                return String.class;  // Nome é do tipo String
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Especie especie = (Especie) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return especie.getId();
            case 1:
                return especie.getNome();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Especie especie = (Especie) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                // Normalmente o ID não é alterado, mas se precisar de lógica, pode adicionar aqui
                break;
            case 1:
                especie.setNome((String) aValue); // Atualiza o nome
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

        EspecieDAO.getInstance().update(especie);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1; // Apenas a coluna "Nome" é editável
    }
}

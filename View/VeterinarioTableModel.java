package view;

import java.util.List;
import Model.Veterinario;
import Model.VeterinarioDAO;

public class VeterinarioTableModel extends GenericTableModel {

    public VeterinarioTableModel(List<Veterinario> vDados) {
        super(vDados, new String[]{"Nome", "CRMV", "Especialidade"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class; // Nome
            case 1:
                return String.class; // CRMV
            case 2:
                return String.class; // Especialidade
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veterinario veterinario = (Veterinario) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return veterinario.getNome();
            case 1:
                return veterinario.getCrmv();
            case 2:
                return veterinario.getEspecialidade();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Veterinario veterinario = (Veterinario) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                veterinario.setNome((String) aValue);
                break;
            case 1:
                veterinario.setCrmv((String) aValue);
                break;
            case 2:
                veterinario.setEspecialidade((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

        VeterinarioDAO.getInstance().update(veterinario);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; // Todas as células são editáveis
    }
}

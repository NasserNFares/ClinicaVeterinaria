package view;

import java.util.List;
import Model.Animal;
import Model.AnimalDAO;


public class AnimalTableModel extends GenericTableModel {

    public AnimalTableModel(List vDados){
        super(vDados, new String[]{"Nome", "Data Nascimento", "Raça", "Espécie", "Cliente"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class; 
            case 1:
                return Integer.class; 
            case 2:
                return String.class; 
            case 3:
                return Integer.class; 
            case 4:
                return Integer.class; 
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal animal = (Animal) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return animal.getNome();
            case 1:
                return animal.getDataNascimento();
            case 2:
                return animal.getRaca();
            case 3:
                return animal.getIdEspecie();
            case 4:
                return animal.getIdCliente();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Animal animal = (Animal) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                animal.setNome((String) aValue);
                break;
            case 1:
                animal.setDataNascimento((Integer) aValue);
                break;
            case 2:
                animal.setRaca((String) aValue);
                break;
            case 3:
                animal.setIdEspecie((Integer) aValue);
                break;
            case 4:
                animal.setIdCliente((Integer) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

        AnimalDAO.getInstance().update(animal);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}

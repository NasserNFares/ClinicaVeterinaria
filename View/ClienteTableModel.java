package view;

import java.util.List;
import Model.Cliente;
import Model.ClienteDAO;

/**
 *
 * @author Prof. Dr. Plinio Vilela - prvilela@unicamp.br
 */
public class ClienteTableModel extends GenericTableModel {

    public ClienteTableModel(List vDados){
        super(vDados, new String[]{"Nome","Endereço", "email", "Telefone"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;                
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente client = (Cliente) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return client.getNome();
            case 1:
                return client.getEndereco();
            case 2:
                return client.getEmail();
            case 3:
                return client.getTelefone(); 
                               
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }    
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cliente client = (Cliente) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                client.setNome((String)aValue);
                break;
            case 1:
                client.setEndereco((String)aValue);
                break;
            case 2:
               client.setEmail((String)aValue);
                break;
            case 3:
                client.setTelefone((String)aValue);
                break;

            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        ClienteDAO.getInstance().update(client);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //if (columnIndex == 0) return false;
        return true;
    }      
    
}

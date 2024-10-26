package controller;

import javax.swing.JTable;
import javax.swing.JTextField;
import Model.Cliente;
import Model.ClienteDAO;
import Model.Animal;
import Model.AnimalDAO;
import Model.EspecieDAO;
import Model.VeterinarioDAO;
import view.GenericTableModel;
import view.ClienteTableModel;
import view.AnimalTableModel;
import view.EspecieTableModel;
import view.VeterinarioTableModel;
import java.util.ArrayList; 

/**
 *
 * @author nasse
 */
public class Controller {
    private static Cliente clienteSelecionado = null;
    private static Animal animalSelecionado = null;
    private static JTextField clienteSelecionadoTextField = null;
    private static JTextField animalSelecionadoTextField = null;

    public static void setTextFields(JTextField cliente, JTextField animal) {
        clienteSelecionadoTextField = cliente;
        animalSelecionadoTextField = animal;
    }

    public static void setTableModel(JTable table, GenericTableModel tableModel) {
        table.setModel(tableModel);
    }

    public static Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public static void setSelected(Object selected) {
        if (selected instanceof Cliente) {
            clienteSelecionado = (Cliente) selected;
            clienteSelecionadoTextField.setText(clienteSelecionado.getNome());
            animalSelecionadoTextField.setText("");

        } else if (selected instanceof Animal) {
            animalSelecionado = (Animal) selected;
            animalSelecionadoTextField.setText(animalSelecionado.getNome());
        }
    }

    // jRadioButtons
    public static void jRadioButtonClientesSelecionado(JTable table) {
        setTableModel(table, new ClienteTableModel(ClienteDAO.getInstance().retrieveAll()));
    }

    public static boolean jRadioButtonAnimaisSelecionados(JTable table) {
        if (getClienteSelecionado() != null) {
            setTableModel(table, new AnimalTableModel(AnimalDAO.getInstance().retrieveByIdCliente(Controller.getClienteSelecionado().getId())));
            return true;
        } else {
            setTableModel(table, new AnimalTableModel(new ArrayList<>()));
            return false;
        }
    }

    public static void jRadioButtonEspecieSelecionado(JTable table) {
        setTableModel(table, new EspecieTableModel(EspecieDAO.getInstance().retrieveAll()));
    }

    public static void jRadioButtonVeterinariosSelecionado(JTable table) {
        setTableModel(table, new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAll())); 
    }
}

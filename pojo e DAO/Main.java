package Model;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Cliente c1 = new Cliente (1, "Nasser", "av aclimacao", "65999080055", "nasser.fares@hotmai.com");
        //Animal a1 = new Animal(1, "Clara", 2024, "vira-lata");
        // Animal a2 = new Animal(2, "totó", 2021, "pug");
        //ClienteDAO.getInstance().create("Nasser Nasser Fares", "Condominio Morar Mais", "nasser.fares@hotmail.com", "65999080055");

       ;; Cliente c2 = ClienteDAO.getInstance().retrieveById(2);

       // AnimalDAO.getInstance().create("Minie", 2015, "golden retriever",1, c2.getId());

        //Veterinario v1 = VeterinarioDAO.getInstance().create("Claudio", "123", "macacos");
        //System.out.println(VeterinarioDAO.getInstance().retrieveById(v1.getIdVeterinario()));


        //System.out.println(ClienteDAO.getInstance().retrieveAll());
        //System.out.println(AnimalDAO.getInstance().retrieveByIdCliente(c2.getId()));

    }
}

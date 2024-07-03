package View;

import Model.Agenda;
import Model.Endereco;
import Util.TecladoUtil;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;


public class AgendaView {

    private static boolean sair = false;
    private static MetodosView metodo = new MetodosView();


    public static void main(String[] args) {

        Agenda minhaAgenda = new Agenda("Sol", "(51) 99969-4999", new Endereco("Rua A", "RS", "Porto Alegre"), LocalDate.of(2005, 11, 16));
        minhaAgenda.adicionarContato(new Agenda("Maria", "(51) 74776-5432", new Endereco("Rua B", "RJ", "Rio de Janeiro"), LocalDate.of(2008, 07, 20)));
        minhaAgenda.adicionarContato(new Agenda("Mateus", "(33) 91765-1234", new Endereco("Rua C", "MG", "Belo Horizonte"), LocalDate.of(1995, 07, 25)));
        minhaAgenda.adicionarContato(minhaAgenda);

        while (!sair) {
            metodo.menu();
            int opcao = TecladoUtil.lerInteiro("Selecione uma opcao:");
            executaAcao(opcao);
        }
    }

    private static void executaAcao(int opcao) {
        try {
            switch (opcao) {
                case 1:
                    metodo.criarContato();
                    break;
                case 2:
                    metodo.removerContato();
                    break;
                case 3:
                    metodo.editarContato();
                    break;
                case 4:
                    metodo.listarContatos();
                    break;
                case 5:
                    metodo.listarAniversariantes();
                    break;
                case 6:
                    metodo.filtrarContatosPorLetra();
                    break;
                case 7:
                    sair = true;
                    break;
                default:
                    System.out.println("Opcao invalida!!");
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

package View;

import Model.Agenda;
import Model.Endereco;
import Util.TecladoUtil;

import java.time.LocalDate;
import java.util.List;

public class MetodosView {
    private Agenda minhaAgenda = new Agenda();
    private String linha = "----------------------";


    public MetodosView() {
    }

    private void printfMenu(String mensagem) {
        int larguraTotal = 40;
        int espacosAntes = (larguraTotal - mensagem.length()) / 2;
        int espacosDepois = larguraTotal - espacosAntes - mensagem.length();
        String espacosAntesStr = String.format("%" + espacosAntes + "s", "");
        String espacosDepoisStr = String.format("%" + espacosDepois + "s", "");

        System.out.printf("%s%s%s%n", espacosAntesStr, mensagem, espacosDepoisStr);
    }

    public void menu() {
        printfMenu(linha);
        printfMenu("--Menu--");
        printfMenu(linha);
        printfMenu("1 - Criar Contato");
        printfMenu("2 - Remover Contato");
        printfMenu("3 - Alterar contato");
        printfMenu("4 - Listar contato");
        printfMenu("5 - Listar Aniversariantes");
        printfMenu("6 - Listar por filtro");
        printfMenu("7 - sair");
        printfMenu(linha);
    }

    public void criarContato() {
        printfMenu(linha);
        printfMenu("Criando contato");
        printfMenu("---------");
        printfMenu("Dados pessoais: ");
        String nome = TecladoUtil.lerString("Digite o nome do Contato: ");
        String telefone = TecladoUtil.lerString("Digite o numero do telefone: ");
        printfMenu("---------");
        printfMenu("Data de Nascimento: ");
        int anoNascimento = TecladoUtil.lerInteiro("Digite o ano de Nascimento");
        int mesNascimento = TecladoUtil.lerInteiro("Digite o mes de Nascimento");
        int diaNascimento = TecladoUtil.lerInteiro("Digite o dia do Nascimento");
        printfMenu("---------");
        printfMenu("Dados do Endereco: ");
        String nomeLogradouro = TecladoUtil.lerString("Digite o Logradouro do Contato: ");
        String nomeCidade = TecladoUtil.lerString("Digite a Cidade do Contato: ");
        String nomeUf = TecladoUtil.lerString("Digite a Uf do Contato: ");
        Agenda novoContato = new Agenda(nome, telefone, new Endereco(nomeLogradouro, nomeUf, nomeCidade), LocalDate.of(anoNascimento, mesNascimento, diaNascimento));
        minhaAgenda.adicionarContato(novoContato);
        printfMenu(nome.toUpperCase() + " CADASTRADO!");
    }
    public void editarContato() {
        MetodosView metodosView = new MetodosView();
        printfMenu(linha);
        printfMenu("Editar contato");
        metodosView.listarContatos();
        int idEditar = TecladoUtil.lerInteiro("Digite o ID do Contato que deseja editar: ");
        printfMenu("---------");
        printfMenu("Dados dos pessoais: ");
        String nome = TecladoUtil.lerString("Digite o nome do Contato: ");
        String telefone = TecladoUtil.lerString("Digite o numero do telefone: ");
        printfMenu("---------");
        printfMenu("Data de Nascimento: ");
        int anoNascimento = TecladoUtil.lerInteiro("Digite o ano de Nascimento");
        int mesNascimento = TecladoUtil.lerInteiro("Digite o mes de Nascimento");
        int diaNascimento = TecladoUtil.lerInteiro("Digite o dia do Nascimento");
        printfMenu("---------");
        printfMenu("Dados do Endereco: ");
        String nomeLogradouro = TecladoUtil.lerString("Digite o Logradouro do Contato: ");
        String nomeCidade = TecladoUtil.lerString("Digite a Cidade do Contato: ");
        String nomeUf = TecladoUtil.lerString("Digite a Uf do Contato: ");
        minhaAgenda.editarContato(idEditar, nome, telefone, new Endereco(nomeLogradouro, nomeUf, nomeCidade), LocalDate.of(anoNascimento, mesNascimento, diaNascimento));
        printfMenu(nome.toUpperCase() + " CADASTRADO EDITADO!");
    }
    public void removerContato() {
        MetodosView metodosView = new MetodosView();
        printfMenu(linha);
        printfMenu("remover contato");
        metodosView.listarContatos();
        int idExcluir = TecladoUtil.lerInteiro("Digite o ID do Contato que deseja excluir: ");
        minhaAgenda.removerContato(idExcluir);
        printfMenu("Removendo...");
    }
    public void listarContatos(){
        printfMenu(linha);
        printfMenu("Lista de Contatos: ");
        minhaAgenda.listarContatos();
    }
    public void filtrarContatosPorLetra(){
        printfMenu(linha);
        printfMenu("Filtrar contatos");
        String palavraFiltrar = TecladoUtil.lerString("Digite a palavra que deseja buscar:");
        List<Agenda> filtrados = minhaAgenda.listarContatosPorLetra(palavraFiltrar);
        for (Agenda contato : filtrados) {
            System.out.println(contato);
        }
    }

    public void listarAniversariantes(){
        printfMenu(linha);
        printfMenu("Lista de Aniversariantes");
        List<Agenda> aniversariantes = minhaAgenda.listarAniversariantesDoMes();
        for (Agenda contato : aniversariantes) {
            System.out.println(contato);
        }
    }


}

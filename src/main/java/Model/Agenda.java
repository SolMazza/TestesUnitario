package Model;

import Model.Endereco;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Agenda implements Comparable<Agenda> {
    private static int sequence = 1;

    private int id;
    private String nome;
    private String telefone;
    private Endereco endereco;
    private LocalDate dataNascimento;
    private static List<Agenda> lista = new ArrayList<>();

    static {
        lista.add(new Agenda("Leona M", "(51) 99966-5555", new Endereco("Rua nova, 12", "CE", "Fortaleza"), LocalDate.of(2008, 5, 20)));
    }

    public Agenda() {
    }

    public Agenda(String nome, String telefone, Endereco endereco, LocalDate dataNascimento) {
        this.id = sequence++;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    public void adicionarContato(Agenda agenda) {
        lista.add(agenda);
    }

    public void removerContato(int id) {
        lista.removeIf(contato -> contato.getId() == id);
    }

    public List<Agenda> listarContatos() {
        lista.sort((a, b) -> a.getNome().compareTo(b.getNome()));
        lista.forEach(contato -> System.out.println("|ID: "+ contato.getId() +"| Nome:  " + contato.getNome()));
        return new ArrayList<>(lista);
    }

    public List<Agenda> listarContatosPorLetra(String letraProcurada) {
        lista.sort((a, b) -> a.getNome().compareTo(b.getNome()));

        List<Agenda> contatosFiltrados =
                lista.stream()
                        .filter(contato -> contato.getNome().contains(letraProcurada))
                        .collect(Collectors.toList());

        return contatosFiltrados;
    }

    public List<Agenda> listarAniversariantesDoMes() {

        int mesAtual = LocalDate.now().getMonthValue();

        List<Agenda> aniversariantesDoMes =
                lista.stream()
                        .filter(contato -> contato.dataNascimento.getMonthValue() == mesAtual)
                        .sorted(Comparator.comparing(Agenda::getDataNascimento))
                        .collect(Collectors.toList());

        return aniversariantesDoMes;
    }

    public void editarContato(int id, String novoNome, String novoTelefone, Endereco novoEndereco, LocalDate novaDataNascimento) {
        for (Agenda contato : lista) {
            if (contato.getId() == id) {
                contato.setNome(novoNome);
                contato.setTelefone(novoTelefone);
                contato.setEndereco(novoEndereco);
                contato.setDataNascimento(novaDataNascimento);
                break;
            }
        }
    }


    public static List<Agenda> getLista() {
        return lista;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agenda agenda = (Agenda) o;
        return id == agenda.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Agenda agenda) {
        return this.nome.compareToIgnoreCase(agenda.getNome());
    }

    @Override
    public String toString() {
        return "Contato {" +
                " id =" + id +
                ", nome ='" + nome + '\'' +
                ", telefone ='" + telefone + '\'' +
                ", Cidade =" + endereco.getCidade() +
                ", data de Nascimento =" + dataNascimento +
                '}';
    }
}
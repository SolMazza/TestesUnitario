import Model.Agenda;
import Model.Endereco;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TesteAgenda {

    private Agenda minhaAgenda;

    @BeforeAll
    public void setUp() {
        minhaAgenda = new Agenda("Sol", "(51) 99969-4999", new Endereco("Rua A", "RS", "Porto Alegre"), LocalDate.of(2005, 11, 16));
        minhaAgenda.adicionarContato(new Agenda("Maria", "(51) 74776-5432", new Endereco("Rua B", "RJ", "Rio de Janeiro"), LocalDate.of(2008, 7, 20)));
        minhaAgenda.adicionarContato(new Agenda("Mateus", "(33) 91765-1234", new Endereco("Rua C", "MG", "Belo Horizonte"), LocalDate.of(1995, 9, 25)));
        minhaAgenda.adicionarContato(minhaAgenda);
    }

    @Test
    public void testListarAniversarianteDoMes() {

        int mesAtual = LocalDate.now().getMonthValue();

        List<Agenda> listaDeAniversariantes = minhaAgenda.listarAniversariantesDoMes();
        assertNotNull(listaDeAniversariantes);
        for (Agenda contato : listaDeAniversariantes) {
            assertEquals(contato.getDataNascimento().getMonthValue(), mesAtual, "Erro na filtragem, contatos de outros meses");
        }
    }


    @Test
    public void testListarContatosOrdenadoPorNome() {
        minhaAgenda.listarContatos();

        List<Agenda> listaDeContatos = minhaAgenda.getLista();
        assertNotNull(listaDeContatos);

        assertEquals("Maria", listaDeContatos.get(1).getNome());
        assertEquals("Mateus", listaDeContatos.get(2).getNome());
        assertEquals("Sol", listaDeContatos.get(3).getNome());
    }

    @Test
    public void testBuscarContatosPorLetra() {
        String letraProcurada = "Ma";
        List<Agenda> listaFiltrada = minhaAgenda.listarContatosPorLetra(letraProcurada);

        for (Agenda contato : listaFiltrada) {
            assertTrue(contato.getNome().contains(letraProcurada),
                    "Erro na filtragem: o contato " + contato.getNome() + " não contém a letra " + letraProcurada);
        }
    }


    @Test
    void testEditarContato() {
        int idContato = minhaAgenda.getLista().get(0).getId();

        String novoNome = "Luísa Mazzarolo";
        String novoTelefone = "(51) 12345-6789";
        Endereco novoEndereco = new Endereco("Nova rua", "RS", "Gravataí");
        LocalDate novaDataNascimento = LocalDate.of(2007, 06, 21);

        minhaAgenda.editarContato(idContato, novoNome, novoTelefone, novoEndereco, novaDataNascimento);

        Agenda contatoEditado = minhaAgenda.getLista().stream()
                .filter(contato -> contato.getId() == idContato)
                .findFirst()
                .orElse(null);

        assertNotNull(contatoEditado);

        assertEquals(novoNome, contatoEditado.getNome());
        assertEquals(novoTelefone, contatoEditado.getTelefone());
        assertEquals(novoEndereco.getLogradouro(), contatoEditado.getEndereco().getLogradouro());
        assertEquals(novoEndereco.getUf(), contatoEditado.getEndereco().getUf());
        assertEquals(novoEndereco.getCidade(), contatoEditado.getEndereco().getCidade());
        assertEquals(novaDataNascimento, contatoEditado.getDataNascimento());
    }


    @Test
    void testaAdicionarContato() {
        int tamanhoLista = minhaAgenda.getLista().size();

        Agenda novoContato = new Agenda("Tati", "(51) 98765-4321", new Endereco("Rua D", "RS", "Porto Alegre"), LocalDate.of(2000, 6, 15));
        minhaAgenda.adicionarContato(novoContato);
        int novoTamanho = minhaAgenda.getLista().size();

        assertEquals(tamanhoLista + 1, novoTamanho, "Não adicionou o contato à lista");
        assertTrue(minhaAgenda.getLista().contains(novoContato));
    }

    @Test
    void testaRemoverContato() {
        int tamanhoLista = minhaAgenda.getLista().size();

        Agenda contatoParaRemover = minhaAgenda.getLista().get(1);
        minhaAgenda.removerContato(contatoParaRemover.getId());
        int novoTamanho = minhaAgenda.getLista().size();


        assertEquals(tamanhoLista - 1, novoTamanho, "Não removeu a lista");
        assertFalse(minhaAgenda.getLista().contains(contatoParaRemover));
    }
}

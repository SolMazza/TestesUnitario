package Model;

public class Endereco {
    private String logradouro;
    private String uf;
    private String cidade;

    public Endereco() {
    }

    public Endereco(String logradouro, String uf, String cidade) {
        this.logradouro = logradouro;
        this.uf = uf;
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }


    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}

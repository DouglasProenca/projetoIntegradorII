package model;

/**
 *
 * @author Douglas
 */
public class Cep {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private String complemento;

    public Cep() {
    }

    public Cep(String cep, String logradouro, String bairro, String localidade, String uf, String complemento) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.complemento = complemento; 
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    public String getComplemento() {
        return complemento;
    }
}

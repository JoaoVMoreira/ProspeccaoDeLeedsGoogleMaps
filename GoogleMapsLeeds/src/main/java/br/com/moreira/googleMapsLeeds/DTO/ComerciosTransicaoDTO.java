package br.com.moreira.googleMapsLeeds.DTO;

public class ComerciosTransicaoDTO {
    private String nome;
    private String segmento;
    private String cidade;
    private String contato;
    private String site;

    public ComerciosTransicaoDTO(String nome, String segmento, String cidade, String contato, String site){
        this.nome = nome;
        this.cidade = cidade;
        this.site = site;
        this.segmento = segmento;
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}

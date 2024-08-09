package br.com.moreira.googleMapsLeeds.DTO;

public class ComerciosTransicaoDTO {
    private String id;
    private String nome;
    private String segmento;
    private String cidade;
    private String contato;
    private String site;
    private boolean possuiWpp;

    public ComerciosTransicaoDTO(String nome, String segmento, String cidade, String contato, String site){
        this.nome = nome;
        this.cidade = cidade;
        this.site = site;
        this.segmento = segmento;
        this.contato = contato;
    }

    public ComerciosTransicaoDTO(String id, String nome, String segmento, String cidade, String contato, String site){
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.site = site;
        this.segmento = segmento;
        this.contato = contato;
    }

    public ComerciosTransicaoDTO(String id, String nome, String segmento, String cidade, String contato, String site, boolean possuiWpp) {
        this.id = id;
        this.nome = nome;
        this.segmento = segmento;
        this.cidade = cidade;
        this.contato = contato;
        this.site = site;
        this.possuiWpp = possuiWpp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isPossuiWpp() {
        return possuiWpp;
    }

    public void setPossuiWpp(boolean possuiWpp) {
        this.possuiWpp = possuiWpp;
    }
}

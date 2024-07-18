package br.com.moreira.googleMapsLeeds.model;


import br.com.moreira.googleMapsLeeds.DTO.PlaceDetailsDTO;

import javax.persistence.*;


@Entity
@Table(name = "db_comercios_transicao")
public class ComerciosTransicaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "c_nome")
    private String nome;
    @Column(name = "c_segmento")
    private String segmento;
    @Column(name = "c_contato", unique = true)
    private String contato;
    @Column(name = "c_site", unique = true)
    private String site;
    @Column(name = "ctt_realizado")
    private Boolean cttRealizado;
    @Column(name = "c_cidade")
    private String cidade;
    @Column(name = "possui_wpp")
    private Boolean possuiWpp;

    //Construtores
    public ComerciosTransicaoModel(){

    }

    public ComerciosTransicaoModel(PlaceDetailsDTO data){
        this.nome = data.getResult().getName();
        this.segmento = data.getResult().getTypes().get(1);
        this.contato = data.getResult().getPhoneNumber();
        this.site = data.getResult().getSite();
        this.cttRealizado = false;

        var city = data.getResult().getAddresComponents().get(4).getLong_name();
        this.cidade = city;

        this.possuiWpp = false;
    }

    public ComerciosTransicaoModel(String id, String nome, String segmento, String contato, String site, Boolean cttRealizado, String cidade, Boolean possuiWpp){
        this.id = id;
        this.nome = nome;
        this.segmento = segmento;
        this.contato = contato;
        this.site = site;
        this.cttRealizado = cttRealizado;
        this.cidade = cidade;
        this.possuiWpp = possuiWpp;
    }

    //Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSegmento(){
        return segmento;
    }

    public void setSegmento(String segmento){
        this.segmento = segmento;
    }

    public String getContato(){
        return contato;
    }

    public void setContato(String contato){
        this.contato = contato;
    }

    public String getSite(){
        return site;
    }

    public void setSite(String site){
        this.site = site;
    }

    public Boolean getCttRealizado(){
        return cttRealizado;
    }

    public void setCttRealizado(Boolean cttRealizado){
        this.cttRealizado = cttRealizado;
    }

    public String getCidade(){
        return cidade;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    public Boolean getPossuiWpp(){
        return possuiWpp;
    }

    public void setPossuiWpp(Boolean possuiWpp){
        this.possuiWpp = possuiWpp;
    }

}

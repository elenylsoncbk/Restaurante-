package oo.Composicao.Desafio.Trabalho2;

public class Refeicao {

    private String descricao;
    private PratoPrincipal pratoPrincipal;
    private Acompanhamento acompanhamento;
    private Salada salada;

    public Refeicao() {
    }
    public Refeicao(String dNome, PratoPrincipal pratoPrincipal, Acompanhamento acompanhamento, Salada salada) {
        this.descricao = dNome;
        this.salada = salada;
        this.acompanhamento = acompanhamento;
        this.pratoPrincipal = pratoPrincipal;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPratoPrincipal(PratoPrincipal pratoPrincipal) {
        this.pratoPrincipal = pratoPrincipal;
    }

    public void setAcompanhamento(Acompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public void setSalada(Salada salada) {
        this.salada = salada;
    }

    public String getDescricao() {
        return descricao;
    }

    public Salada getSalada() {
        return salada;
    }

    public Acompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public PratoPrincipal getPratoPrincipal() {
        return pratoPrincipal;
    }
}
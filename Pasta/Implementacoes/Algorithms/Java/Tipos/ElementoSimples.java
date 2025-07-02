package Tipos;

public class ElementoSimples {
    private ElementoSimples esquerda;
    private ElementoSimples direita;
    private ElementoSimples pai;
    private Character id;
    private boolean visitado = false;

    public ElementoSimples(ElementoSimples pai, Character id) {
        this.pai = pai;
        this.id = id;
    }

    public ElementoSimples getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(ElementoSimples esquerda) {
        this.esquerda = esquerda;
    }

    public ElementoSimples getDireita() {
        return direita;
    }

    public void setDireita(ElementoSimples direita) {
        this.direita = direita;
    }

    public ElementoSimples getPai() {
        return pai;
    }

    public Character getId() {
        return id;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public String printElementoAmigavel() {
        String esq = (esquerda != null) ? esquerda.id.toString() : "null";
        String dir = (direita != null) ? direita.id.toString() : "null";
        return id + " -> [Esq: " + esq + ", Dir: " + dir + "]";
    }
}

public class Elemento {
    private Elemento esquerda;
    private Elemento direita;
    private Elemento pai;
    private Character id;
    private boolean visitado = false;

    public Elemento(Elemento pai, Character id) {
        this.pai = pai;
        this.id = id;
    }

    public Elemento getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Elemento esquerda) {
        this.esquerda = esquerda;
    }

    public Elemento getDireita() {
        return direita;
    }

    public void setDireita(Elemento direita) {
        this.direita = direita;
    }

    public Elemento getPai() {
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
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        if (esquerda != null || direita != null) {
            sb.append(" -> [Esq: ");
            if (esquerda != null) {
                sb.append(esquerda.id);
            }
            if (direita != null) {
                if (esquerda != null) {
                    sb.append(", Dir: ");
                }
                sb.append(direita.id);
            }
            sb.append("]");
        }
        return sb.toString();
    }
}

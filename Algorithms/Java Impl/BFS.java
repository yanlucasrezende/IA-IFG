import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    public static void main(String[] args) {
        Elemento raiz = inicializaGrafo();

        System.out.println("Elemento raiz: " + raiz.printElementoAmigavel());

        String resultadoEsquerda = buscaLarguraEsq(raiz, 'G');
        String resultadoDireita = buscaLarguraDir(raiz, 'G');

        //Imprime o resultado da busca em largura
        //um priorizando a esquerda e o outro a direita
        //durante a busca do resultado
        System.out.println(resultadoEsquerda);
        System.out.println(resultadoDireita);
    }

    public static String buscaLarguraEsq(Elemento raiz, Character objetivo) {
        if (raiz == null) {
            return "Árvore vazia";
        }

        if (raiz.getId() == objetivo) {
            return "Objetivo encontrado na raiz: " + raiz.getId();
        }

        boolean encontrado = false;
        StringBuilder caminho = new StringBuilder("");
        String solucao = null;

        Queue<Elemento> fila = new LinkedList<>();

        if (raiz.getEsquerda() != null) {
            fila.offer(raiz.getEsquerda());
        }

        if (raiz.getDireita() != null) {
            fila.offer(raiz.getDireita());
        }

        if (fila.isEmpty()) {
            return "Objetivo não encontrado pois a está árvore vazia!";
        }

        while (!fila.isEmpty()) {
            Elemento atual = fila.poll();
            if (atual == null) {
                continue;
            }
            caminho.append(atual.getPai().getId() + " -> " + atual.getId());
            caminho.append("\n");

            if (atual.getId() == objetivo) {
                encontrado = true;
                solucao = caminhoElementoParaRaiz(atual);
                break;
            }
            caminho.append(atual.getId() + " -> " + atual.getPai().getId());
            caminho.append("\n");

            if (atual.getEsquerda() != null) {
                fila.offer(atual.getEsquerda());
            }

            if (atual.getDireita() != null) {
                fila.offer(atual.getDireita());
            }

        }
        if (!encontrado) {
            return "Objetivo não encontrado!";
        }
        return "Caminho: \n" + caminho.toString() + "\n" +
                "Solução: " + solucao;
    }

    public static String buscaLarguraDir(Elemento raiz, Character objetivo) {
        if (raiz == null) {
            return "Árvore vazia";
        }

        if (raiz.getId() == objetivo) {
            return "Objetivo encontrado na raiz: " + raiz.getId();
        }

        boolean encontrado = false;
        StringBuilder caminho = new StringBuilder("");
        String solucao = null;

        Queue<Elemento> fila = new LinkedList<>();

        if (raiz.getDireita() != null) {
            fila.offer(raiz.getDireita());
        }

        if (raiz.getEsquerda() != null) {
            fila.offer(raiz.getEsquerda());
        }

        if (fila.isEmpty()) {
            return "Objetivo não encontrado pois a está árvore vazia!";
        }

        while (!fila.isEmpty()) {
            Elemento atual = fila.poll();
            if (atual == null) {
                continue;
            }
            caminho.append(atual.getPai().getId() + " -> " + atual.getId());
            caminho.append("\n");

            if (atual.getId() == objetivo) {
                encontrado = true;
                solucao = caminhoElementoParaRaiz(atual);
                break;
            }
            caminho.append(atual.getId() + " -> " + atual.getPai().getId());
            caminho.append("\n");

            if (atual.getDireita() != null) {
                fila.offer(atual.getDireita());
            }

            if (atual.getEsquerda() != null) {
                fila.offer(atual.getEsquerda());
            }
        }
        if (!encontrado) {
            return "Objetivo não encontrado!";
        }
        return "Caminho: \n" + caminho.toString() + "\n" +
                "Solução: " + solucao;
    }

    public static String caminhoElementoParaRaiz(Elemento elemento) {
        if (elemento == null) {
            return "Caminho não encontrado!";
        }
        List<Elemento> caminho = new ArrayList<>();

        caminho.add(elemento);
        Elemento pai = elemento.getPai();

        while (pai != null) {
            caminho.add(pai);
            pai = pai.getPai();
        }

        caminho = caminho.reversed();
        StringBuilder sb = new StringBuilder();

        for (Elemento e : caminho) {
            sb.append(e.getId()).append(" -> ");
        }
        // Remover a última seta
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 4);
        }
        return sb.toString();
    }

    public static Elemento inicializaGrafo() {
        // Inicio
        Elemento s = new Elemento(null, 'S');
        // 2 nivel
        Elemento a = new Elemento(s, 'A');
        Elemento b = new Elemento(s, 'B');
        // 3 nivel
        Elemento d = new Elemento(a, 'D');
        Elemento c = new Elemento(a, 'C');
        Elemento e = new Elemento(b, 'E');
        Elemento f = new Elemento(b, 'F');
        // 4 nivel
        Elemento g1 = new Elemento(f, 'G');
        Elemento g2 = new Elemento(d, 'G');

        s.setDireita(a);
        s.setEsquerda(b);

        a.setDireita(c);
        a.setEsquerda(d);

        b.setDireita(e);
        b.setEsquerda(f);

        f.setEsquerda(g1);

        d.setDireita(g2);

        return s;
    }

    private static class Elemento {
        private Elemento esquerda;
        private Elemento direita;
        private Elemento pai;
        private Character id;

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
}
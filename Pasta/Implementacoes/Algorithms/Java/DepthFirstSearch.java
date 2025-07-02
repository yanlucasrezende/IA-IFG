import Tipos.*;

public class DepthFirstSearch {
    public static int QTD_ELEMENTOS = 0;

    public static void main(String[] args) {

        Grafo grafo = Util.inicializaGrafoSimples();

        ElementoSimples raiz = grafo.getRaiz();
        QTD_ELEMENTOS = grafo.getQtdeElementos();

        System.out.println("\nQuantidade de elementos: " + grafo.getQtdeElementos());
        System.out.println("Elemento raiz DFS: " + raiz.printElementoAmigavel() + '\n');

        Character objetivo = 'H';

        
        String resultadoEsquerda = buscaProfundidadeEsq(raiz, objetivo);
        grafo = Util.inicializaGrafoSimples();
        raiz = grafo.getRaiz();
        QTD_ELEMENTOS = grafo.getQtdeElementos();
        String resultadoDireita = buscaProfundidadeDir(raiz, objetivo);

        System.out.println("############### Prioridade esquerda ############### \n");
        System.out.println(resultadoEsquerda);
        System.out.println("\n################################################### \n");

        System.out.println("############### Prioridade direita ############### \n");
        System.out.println(resultadoDireita);
        System.out.println("\n################################################### \n");
    }

    public static String buscaProfundidadeEsq(ElementoSimples raiz, Character objetivo) {
        if (raiz == null) {
            return "Árvore vazia";
        }

        if (raiz.getId() == objetivo) {
            return "Objetivo encontrado na raiz: " + raiz.getId();
        }
        boolean encontrado = false;
        StringBuilder caminho = new StringBuilder("");
        String solucao = null;

        ElementoSimples elemento = raiz.getEsquerda() != null ? raiz.getEsquerda() : raiz.getDireita();

        if (elemento == null) {
            return "Objetivo não encontrado pois a árvore está vazia!";
        }

        caminho.append(elemento.getPai().getId() + " vai " + elemento.getId());
        caminho.append("\n");

        int qtdeVisitados = 0;
        while (elemento != null && !encontrado && qtdeVisitados != QTD_ELEMENTOS) {
            if (!elemento.isVisitado()) {
                qtdeVisitados++;
            }
            if (elemento.getId() == objetivo) {
                encontrado = true;
                solucao = Util.caminhoElementoParaRaiz(elemento);
                break;
            }
            elemento.setVisitado(true);

            ElementoSimples esq = elemento.getEsquerda();
            ElementoSimples dir = elemento.getDireita();
            ElementoSimples pai = elemento.getPai();

            if (esq != null && !esq.isVisitado()) {
                caminho.append(elemento.getId() + " vai " + esq.getId());
                caminho.append("\n");
                elemento = esq;
                continue;
            }
            if (dir != null && !dir.isVisitado()) {
                caminho.append(elemento.getId() + " vai " + dir.getId());
                caminho.append("\n");
                elemento = dir;
                continue;
            }
            if (pai != null) {
                caminho.append(elemento.getId() + " volta " + pai.getId());
                caminho.append("\n");
                elemento = pai;
                continue;
            }
            elemento = null;
        }
        if (!encontrado) {
            return "Objetivo não encontrado! \n" +
                    "Caminho: \n" + caminho.toString();
        } else {
            return "Caminho: \n" + caminho.toString() + "\n" +
                    "Solução: " + solucao;
        }
    }

    public static String buscaProfundidadeDir(ElementoSimples raiz, Character objetivo) {
        if (raiz == null) {
            return "Árvore vazia";
        }

        if (raiz.getId() == objetivo) {
            return "Objetivo encontrado na raiz: " + raiz.getId();
        }
        boolean encontrado = false;
        StringBuilder caminho = new StringBuilder("");
        String solucao = null;

        ElementoSimples elemento = raiz.getDireita() != null ? raiz.getDireita() : raiz.getEsquerda();

        if (elemento == null) {
            return "Objetivo não encontrado pois a árvore está vazia!";
        }

        caminho.append(elemento.getPai().getId() + " vai " + elemento.getId());
        caminho.append("\n");

        int qtdeVisitados = 0;
        while (elemento != null && !encontrado && qtdeVisitados != QTD_ELEMENTOS) {
            if (!elemento.isVisitado()) {
                qtdeVisitados++;
            }
            if (elemento.getId() == objetivo) {
                encontrado = true;
                solucao = Util.caminhoElementoParaRaiz(elemento);
                break;
            }
            elemento.setVisitado(true);

            ElementoSimples esq = elemento.getEsquerda();
            ElementoSimples dir = elemento.getDireita();
            ElementoSimples pai = elemento.getPai();

            if (dir != null && !dir.isVisitado()) {
                caminho.append(elemento.getId() + " vai " + dir.getId());
                caminho.append("\n");
                elemento = dir;
                continue;
            }

            if (esq != null && !esq.isVisitado()) {
                caminho.append(elemento.getId() + " vai " + esq.getId());
                caminho.append("\n");
                elemento = esq;
                continue;
            }

            if (pai != null) {
                caminho.append(elemento.getId() + " volta " + pai.getId());
                caminho.append("\n");
                elemento = pai;
                continue;
            }
            elemento = null;
        }
        if (!encontrado) {
            return "Objetivo não encontrado! \n" +
                    "Caminho: \n" + caminho.toString();
        } else {
            return "Caminho: \n" + caminho.toString() + "\n" +
                    "Solução: " + solucao;
        }
    }
}
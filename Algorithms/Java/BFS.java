import java.util.LinkedList;
import java.util.Queue;

import Tipos.*;

public class BFS {

    public static void main(String[] args) {
        Grafo grafo = Util.inicializaGrafoSimples();
        ElementoSimples raiz = grafo.getRaiz();

        System.out.println("\nElemento raiz: " + raiz.printElementoAmigavel() + '\n');

        Character objetivo = 'H';

        String resultadoEsquerda = buscaLarguraEsq(raiz, objetivo);
        String resultadoDireita = buscaLarguraDir(raiz, objetivo);

        // Imprime o resultado da busca em largura
        // um priorizando a esquerda e o outro a direita
        // durante a busca do resultado
        System.out.println("Prioridade esquerda: " + resultadoEsquerda + "\n");
        System.out.println("Prioridade direita: " + resultadoDireita + "\n");
    }

    public static String buscaLarguraEsq(ElementoSimples raiz, Character objetivo) {
        if (raiz == null) {
            return "Árvore vazia";
        }

        if (raiz.getId() == objetivo) {
            return "Objetivo encontrado na raiz: " + raiz.getId();
        }

        boolean encontrado = false;
        StringBuilder caminho = new StringBuilder("");
        String solucao = null;

        Queue<ElementoSimples> fila = new LinkedList<>();

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
            ElementoSimples atual = fila.poll();
            if (atual == null) {
                continue;
            }
            caminho.append(atual.getPai().getId() + " vai " + atual.getId());
            caminho.append("\n");

            if (atual.getId() == objetivo) {
                encontrado = true;
                solucao = Util.caminhoElementoParaRaiz(atual);
                break;
            }
            caminho.append(atual.getId() + " volta " + atual.getPai().getId());
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

    public static String buscaLarguraDir(ElementoSimples raiz, Character objetivo) {
        if (raiz == null) {
            return "Árvore vazia";
        }

        if (raiz.getId() == objetivo) {
            return "Objetivo encontrado na raiz: " + raiz.getId();
        }

        boolean encontrado = false;
        StringBuilder caminho = new StringBuilder("");
        String solucao = null;

        Queue<ElementoSimples> fila = new LinkedList<>();

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
            ElementoSimples atual = fila.poll();
            if (atual == null) {
                continue;
            }
            caminho.append(atual.getPai().getId() + " vai " + atual.getId());
            caminho.append("\n");

            if (atual.getId() == objetivo) {
                encontrado = true;
                solucao = Util.caminhoElementoParaRaiz(atual);
                break;
            }
            caminho.append(atual.getId() + " volta " + atual.getPai().getId());
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

}
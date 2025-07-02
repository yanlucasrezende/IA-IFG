package Workspace.Java.Algoritmos;

import java.util.LinkedList;
import java.util.Queue;

import Workspace.Java.Tipos.*;

public class BreadthFirstSearch {

    public static void main(String[] args) {
        Grafo grafo = Util.inicializaGrafoSimples();
        ElementoSimples raiz = grafo.getRaiz();

        System.out.println("\nElemento raiz BFS: " + raiz.printElementoAmigavel() + '\n');

        Character objetivo = 'H';

        String resultadoEsquerda = buscaLarguraEsq(raiz, objetivo);
        grafo = Util.inicializaGrafoSimples();
        raiz = grafo.getRaiz();
        String resultadoDireita = buscaLarguraDir(raiz, objetivo);

        System.out.println("############### Prioridade esquerda ############### \n");
        System.out.println(resultadoEsquerda);
        System.out.println("\n################################################### \n");

        System.out.println("############### Prioridade direita ############### \n");
        System.out.println(resultadoDireita);
        System.out.println("\n################################################### \n");
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

# search.py
# ---------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


"""
In search.py, you will implement generic search algorithms which are called by
Pacman agents (in searchAgents.py).
"""

import util
from game import Directions
from typing import List

class SearchProblem:
    """
    This class outlines the structure of a search problem, but doesn't implement
    any of the methods (in object-oriented terminology: an abstract class).

    You do not need to change anything in this class, ever.
    """

    def getStartState(self):
        """
        Returns the start state for the search problem.
        """
        util.raiseNotDefined()

    def isGoalState(self, state):
        """
          state: Search state

        Returns True if and only if the state is a valid goal state.
        """
        util.raiseNotDefined()

    def getSuccessors(self, state):
        """
          state: Search state

        For a given state, this should return a list of triples, (successor,
        action, stepCost), where 'successor' is a successor to the current
        state, 'action' is the action required to get there, and 'stepCost' is
        the incremental cost of expanding to that successor.
        """
        util.raiseNotDefined()

    def getCostOfActions(self, actions):
        """
         actions: A list of actions to take

        This method returns the total cost of a particular sequence of actions.
        The sequence must be composed of legal moves.
        """
        util.raiseNotDefined()




def tinyMazeSearch(problem: SearchProblem) -> List[Directions]:
    """
    Returns a sequence of moves that solves tinyMaze.  For any other maze, the
    sequence of moves will be incorrect, so only use this for tinyMaze.
    """
    s = Directions.SOUTH
    w = Directions.WEST
    return  [s, s, w, s, w, w, s, w]

def depthFirstSearch(problem: SearchProblem) -> List[Directions]:
    # Lista de movimentos que o pacman fara, optei para retornar todo o caminho percorrido
    # ao inves de apenas o caminho final
    moves: List[Directions] = []
    visited = set()

    def dfs(state) -> bool:
        # Se é o objetivo retorna True
        if problem.isGoalState(state):
            return True

        # Para cada sucessor
        for successor, action, _ in problem.getSuccessors(state):
            if successor not in visited:
                visited.add(successor)
                # 1) Anotamos a ação de ir para o sucessor
                moves.append(action)

                # 2) Chamada recursivamente para o sucessor
                if dfs(successor):
                    return True

                # 3) Se não achou objetivo naquele ramo, voltamos
                moves.append(Directions.REVERSE[action])

        return False

    # Inicialização: marca o start, roda a recursão e retorna todos os movimentos
    start = problem.getStartState()
    visited.add(start)
    dfs(start)
    return moves


def breadthFirstSearch(problem: SearchProblem) -> List[Directions]:
    # Lista de movimentos que o pacman fara, optei para retornar todo o caminho percorrido
    # ao inves de apenas o caminho final
    moves: List[Directions] = []

    visited = set()
    frontier = util.Queue()
    start = problem.getStartState()
    visited.add(start)
    frontier.push(start)
    # armazena o caminho (lista de ações) do início até cada estado
    paths = {start: []}
    current = start

    while not frontier.isEmpty():
        state = frontier.pop()
        path_to_state = paths[state]

        # voltar de 'current' para o início
        for action in reversed(paths[current]):
            moves.append(Directions.REVERSE[action])
        # ir do início até 'state'
        for action in path_to_state:
            moves.append(action)
        current = state

        if problem.isGoalState(state):
            return moves

        for successor, action, _ in problem.getSuccessors(state):
            if successor not in visited:
                visited.add(successor)
                frontier.push(successor)
                paths[successor] = path_to_state + [action]

    return moves

def uniformCostSearch(problem: SearchProblem) -> List[Directions]:
    # Lista de movimentos que o pacman fara, optei para retornar todo o caminho percorrido
    # ao inves de apenas o caminho final
    moves: List[Directions] = []

    # Fronteira como fila de prioridade (custo mínimo primeiro)
    frontier = util.PriorityQueue()
    start = problem.getStartState()
    frontier.push(start, 0)

    # Dicionários para armazenar o melhor caminho e custo até cada estado
    paths = {start: []}    # estado -> lista de ações do start até ele
    costs = {start: 0}     # estado -> custo acumulado até ele

    # Ponteiro para o estado onde Pacman "está" atualmente, para calcular retrocessos
    current = start

    # Conjunto de estados já expandidos (graph search)
    visited = set()

    while not frontier.isEmpty():
        state = frontier.pop()              # estado de menor custo
        path_to_state = paths[state]        # caminho (lista de ações) até esse estado

        # 1) Voltar de `current` para o start
        for action in reversed(paths[current]):
            moves.append(Directions.REVERSE[action])
        # 2) Ir do start até `state`
        for action in path_to_state:
            moves.append(action)
        current = state

        # Se atingimos o objetivo, devolvemos a lista completa de movimentos
        if problem.isGoalState(state):
            return moves

        # Marca como expandido e gera sucessores
        visited.add(state)
        for successor, action, stepCost in problem.getSuccessors(state):
            newCost = costs[state] + stepCost
            # Se encontramos um caminho mais barato até `successor`, atualizamos
            if successor not in costs or newCost < costs[successor]:
                costs[successor] = newCost
                paths[successor] = path_to_state + [action]
                frontier.push(successor, newCost)

    # Caso não ache solução, retorna lista vazia
    return moves


def nullHeuristic(state, problem=None) -> float:
    """
    A heuristic function estimates the cost from the current state to the nearest
    goal in the provided SearchProblem.  This heuristic is trivial.
    """
    return 0

def aStarSearch(problem: SearchProblem, heuristic=nullHeuristic) -> List[Directions]:
    """Search the node that has the lowest combined cost and heuristic first."""
    "*** YOUR CODE HERE ***"
    util.raiseNotDefined()

# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch

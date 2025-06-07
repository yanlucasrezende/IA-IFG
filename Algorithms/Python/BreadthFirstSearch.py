from collections import deque

# S
# ├─ A
# │  ├─ C
# │  └─ D
# │     └─ G
# └─ B
#    ├─ E
#    └─ F
#       └─ G

# Representação de grafo como dicionário de listas de adjacência
graph = {
    'S': ['A', 'B'],
    'A': ['C', 'D'],
    'B': ['E', 'F'],
    'C': [],
    'D': ['G'],
    'E': [],
    'F': ['G'],
    'G': []
}

def bfs_traversal(graph, start):
    """
    Realiza uma busca em largura (BFS) para obter a ordem de visita dos nós.
    Retorna a lista de nós na ordem em que foram explorados.
    """
    visited = set([start])
    queue = deque([start])
    order = []

    while queue:
        node = queue.popleft()
        order.append(node)
        for neighbor in graph.get(node, []):
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)
    return order

def bfs_path(graph, start, goal):
    """
    Realiza uma BFS para encontrar o caminho mais curto (em número de arestas)
    entre 'start' e 'goal'. Retorna a lista de nós no caminho, ou None se não houver caminho.
    """
    visited = set([start])
    queue = deque([[start]])  # fila de caminhos

    while queue:
        path = queue.popleft()
        node = path[-1]
        if node == goal:
            return path
        for neighbor in graph.get(node, []):
            if neighbor not in visited:
                visited.add(neighbor)
                new_path = path + [neighbor]
                queue.append(new_path)
    return None

# Exemplo de uso:
print("Ordem de visita (BFS):", bfs_traversal(graph, 'S'))
print("Menor caminho de 'S' a 'G':", bfs_path(graph, 'S', 'G'))

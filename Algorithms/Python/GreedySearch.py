import pandas as pd
import math

# Definição do problema: Entrega de vacinas em zonas rurais
# Cada ponto de entrega (clinica) tem coordenadas (x, y).
clinicas = {
    'Depósito': (0, 0),
    'Clínica A': (2, 3),
    'Clínica B': (5, 2),
    'Clínica C': (6, 6),
    'Clínica D': (8, 3),
    'Clínica E': (1, 7),
}

def distancia(p1, p2):
    return math.hypot(p1[0] - p2[0], p1[1] - p2[1])

def greedy_nearest_neighbor(inicio, pontos):
    nao_visitados = set(pontos.keys()) - {inicio}
    rota = [inicio]
    atual = inicio

    while nao_visitados:
        proximo = min(nao_visitados, key=lambda p: distancia(pontos[atual], pontos[p]))
        rota.append(proximo)
        nao_visitados.remove(proximo)
        atual = proximo

    rota.append(inicio)  # retornar ao depósito
    return rota

rota = greedy_nearest_neighbor('Depósito', clinicas)

# Preparando DataFrame para exibição
dados = []
for i, local in enumerate(rota):
    dados.append({'Step': i+1, 'Local': local, 'Coordenadas': clinicas[local]})

df_rota = pd.DataFrame(dados)
print("Rota de Entrega de Vacinas (Greedy Search)", df_rota)

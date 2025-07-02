from typing import List, Tuple

def torre_hanoi_dfs() -> None:
    
    piece_names = {
        1: "menor",
        2: "média",
        3: "grande",
        4: "maior"
    }

    moves: List[Tuple[str, str, str]] = []

    # Função recursiva para resolver a Torre de Hanoi com dfs
    def hanoi(n: int, source: str, target: str, auxiliary: str) -> None:
        if n == 0:
            return
        # 1) Mover n-1 discos de source para auxiliary
        hanoi(n - 1, source, auxiliary, target)
        # 2) Mover o disco n de source para target
        name = piece_names[n]
        moves.append((name, source, target))
        print(f"Mova a peça {name} do bastão {source} para o bastão {target}")
        # 3) Mover n-1 discos de auxiliary para target
        hanoi(n - 1, auxiliary, target, source)

    print("Iniciando resolução da Torre de Hanoi com 4 discos\n")
    hanoi(4, 'A', 'C', 'B')
    print(f"\nTotal de movimentos: {len(moves)}")

if __name__ == "__main__":
    torre_hanoi_dfs()

import sys
from constraint import Problem, AllDifferentConstraint


def add_adjacency_constraints(problem, adjacency_list):
    for state, neighbors in adjacency_list.items():
        for neighbor in neighbors:
            problem.addConstraint(lambda x, y: x != y, (state, neighbor))


def solve():
    problem = Problem()

    domain = ["Grün", "Rot", "Blau"]
    states = [
        "Baden-Württemberg", "Bayern", "Saarland", "Hessen", "Thüringen", "Sachsen",
        "Nordrhein-Westfalen", "Niedersachsen", "Sachsen-Anhalt", "Brandenburg",
        "Mecklenburg-Vorpommern", "Schleswig-Holstein", "Bremen", "Hamburg", "Berlin",
        "Rheinland-Pfalz"
    ]
    adjacency_list = {
        "Baden-Württemberg": ["Bayern", "Hessen", "Rheinland-Pfalz"],
        "Bayern": ["Baden-Württemberg", "Hessen", "Thüringen", "Sachsen"],
        "Saarland": ["Rheinland-Pfalz"],
        "Hessen": ["Baden-Württemberg", "Bayern", "Rheinland-Pfalz", "Nordrhein-Westfalen"],
        "Thüringen": ["Bayern", "Sachsen", "Sachsen-Anhalt", "Niedersachsen"],
        "Sachsen": ["Bayern", "Thüringen", "Sachsen-Anhalt", "Brandenburg"],
        "Nordrhein-Westfalen": ["Hessen", "Rheinland-Pfalz"],
        "Niedersachsen": ["Bremen", "Hamburg", "Schleswig-Holstein", "Mecklenburg-Vorpommern", "Brandenburg",
                          "Sachsen-Anhalt", "Thüringen"],
        "Sachsen-Anhalt": ["Niedersachsen", "Thüringen", "Sachsen", "Brandenburg"],
        "Brandenburg": ["Sachsen", "Sachsen-Anhalt", "Mecklenburg-Vorpommern", "Berlin"],
        "Mecklenburg-Vorpommern": ["Schleswig-Holstein", "Niedersachsen", "Brandenburg"],
        "Schleswig-Holstein": ["Niedersachsen", "Mecklenburg-Vorpommern"],
        "Bremen": ["Niedersachsen"],
        "Hamburg": ["Niedersachsen", "Schleswig-Holstein"],
        "Berlin": ["Brandenburg"]
    }

    problem.addVariables(states, domain)

    add_adjacency_constraints(problem, adjacency_list)

    solutions = problem.getSolutions()
    return solutions


def main():
    solutions = solve()
    for solution in solutions:
        print(solution)


if __name__ == "__main__":
    main()

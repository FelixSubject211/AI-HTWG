from constraint import Problem, AllDifferentConstraint


def packungsproblem(groesse_grosses_rechteck, kleine_rechtecke):
    problem = Problem()

    for i, (width, height) in enumerate(kleine_rechtecke):
        x_range = range(0, groesse_grosses_rechteck[0] - min(width, height) + 1)
        y_range = range(0, groesse_grosses_rechteck[1] - min(width, height) + 1)
        positions = [(x, y, z, i) for x in x_range for y in y_range for z in ["h", "v"]]
        problem.addVariable(i, positions)

    for i in range(1, len(kleine_rechtecke)):
        for j in range(1, len(kleine_rechtecke)):
            if i != j:
                problem.addConstraint(
                    lambda i, j: overlab(i, j),
                    [i, j]
                )

    return [problem.getSolution()]

def overlab(i, j):
    print(i[3])
    ix = i[0]
    iy = i[1]
    idx = kleine_rechtecke[i[3]][0] if i[2] == 'v' else kleine_rechtecke[i][1]
    idy = kleine_rechtecke[i[3]][0] if i[2] == 'v' else kleine_rechtecke[i][0]

    jx = j[0]
    jy = j[1]
    jdx = kleine_rechtecke[j[3]][0] if j[2] == 'v' else kleine_rechtecke[j][1]
    jdy = kleine_rechtecke[j[3]][1] if j[2] == 'v' else kleine_rechtecke[j][0]

    if ix < jx + jdx and ix + idx > jx and iy < jy + jdy and iy + idy > jy:
        return True
    else:
        return False

groesse_grosses_rechteck = (7, 8)
kleine_rechtecke = [(6, 4), (8, 1), (4, 1), (5, 2), (2, 2), (3, 2)]

solutions = packungsproblem(groesse_grosses_rechteck, kleine_rechtecke)
for solution in solutions:
    print(solution)

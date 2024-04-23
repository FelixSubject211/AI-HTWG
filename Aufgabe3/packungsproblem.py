from constraint import Problem, AllDifferentConstraint


def packungsproblem(groesse_grosses_rechteck, kleine_rechtecke):
    problem = Problem()

    for i, (width, height) in enumerate(kleine_rechtecke):
        print(i)
        positions = []
        x_range = range(0, groesse_grosses_rechteck[0] - width + 1)
        y_range = range(0, groesse_grosses_rechteck[1] - height + 1)
        if x_range.stop > 0 and y_range.stop > 0:
            positions += [(x, y, "h", i, width, height) for x in x_range for y in y_range]

        x_range = range(0, groesse_grosses_rechteck[0] - height + 1)
        y_range = range(0, groesse_grosses_rechteck[1] - width + 1)
        if x_range.stop > 0 and y_range.stop > 0:
            positions += [(x, y, "v", i, width, height) for x in x_range for y in y_range]

        print(positions)
        problem.addVariable(i, positions)

    for i1, klein1 in enumerate(kleine_rechtecke):
        for i2, klein2 in enumerate(kleine_rechtecke):
            if i1 != i2:
                problem.addConstraint(
                    lambda i, j: not overlab(i, j),
                    [i1, i2]
                )

    return [problem.getSolution()]


def overlab(k1, k2):
    (k1_x, k1_y, k1_a, k1_i, k1_w, k1_h) = k1
    (k2_x, k2_y, k2_a, k2_i, k2_w, k2_h) = k2



    if k1_a == "h" and k2_a == "h":
        overlapH = k1_x + k1_w < k2_x or k2_x + k2_w < k1_x
        overlapV = k1_y + k1_h < k2_y or k2_y + k2_h < k1_y
        return overlapH and overlapV
    if k1_a == "h" and k2_a == "v":
        overlapH = k1_x + k1_w < k2_x or k2_x + k2_h < k1_x
        overlapV = k1_y + k1_h < k2_y or k2_y + k2_w < k1_y
        return overlapH and overlapV
    if k1_a == "v" and k2_a == "h":
        overlapH = k1_x + k1_h < k2_x or k2_x + k2_w < k1_x
        overlapV = k1_y + k1_w < k2_y or k2_y + k2_h < k1_y
        return overlapH and overlapV
    if k1_a == "v" and k2_a == "v":
        overlapH = k1_x + k1_h < k2_x or k2_x + k2_h < k1_x
        overlapV = k1_y + k1_w < k2_y or k2_y + k2_w < k1_y
        return overlapH and overlapV


groesse_grosses_rechteck = (7, 8)
kleine_rechtecke = [(6, 4), (8, 1), (4, 1), (5, 2), (2, 2), (3, 2)]

solutions = packungsproblem(groesse_grosses_rechteck, kleine_rechtecke)
for solution in solutions:
    print(solution)

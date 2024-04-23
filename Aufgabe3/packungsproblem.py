from constraint import Problem, AllDifferentConstraint


def packungsproblem(groesse_grosses_rechteck, kleine_rechtecke):
    problem = Problem()

    for i, (width, height) in enumerate(kleine_rechtecke):
        #print(i)
        positions = []
        x_range = range(0, groesse_grosses_rechteck[0] - width + 1)
        y_range = range(0, groesse_grosses_rechteck[1] - height + 1)
        if x_range.stop > 0 and y_range.stop > 0:
            positions += [(x - 1, y - 1, "h", i, width, height) for x in x_range for y in y_range]

        x_range = range(0, groesse_grosses_rechteck[0] - height + 1)
        y_range = range(0, groesse_grosses_rechteck[1] - width + 1)
        if x_range.stop > 0 and y_range.stop > 0:
            positions += [(x - 1, y - 1, "v", i, width, height) for x in x_range for y in y_range]

        #print(positions)
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

    delta1x = k1_w if k1_a == "h" else k1_h
    delta1y = k1_h if k1_a == "h" else k1_w

    delta2x = k2_w if k2_a == "h" else k2_h
    delta2y = k2_h if k2_a == "h" else k2_w

    return rectangles_overlap(k1_x, k1_y, delta1x, delta1y, k2_x, k2_y, delta2x, delta2y)


def rectangles_overlap(k1_x, k1_y, delta1_x, delta1_y, k2_x, k2_y, delta2_x, delta2_y):
    k1_bottom_right_x = k1_x + delta1_x
    k1_bottom_right_y = k1_y + delta1_y
    k2_bottom_right_x = k2_x + delta2_x
    k2_bottom_right_y = k2_y + delta2_y

    if (k1_x < k2_bottom_right_x and
            k1_bottom_right_x > k2_x and
            k1_y < k2_bottom_right_y and
            k1_bottom_right_y > k2_y):
        return True
    else:
        return False


groesse_grosses_rechteck = (7, 8)
kleine_rechtecke = [(6, 4), (8, 1), (4, 1), (5, 2), (2, 2), (3, 2)]

solutions = packungsproblem(groesse_grosses_rechteck, kleine_rechtecke)
for solution in solutions:
    print(solution)

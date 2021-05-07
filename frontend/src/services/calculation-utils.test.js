import {sum} from "./calculation-utils";


test("Add to numbers", () => {
    //GIVEN
    const a = 4;
    const b = 5;
    //WHEN
    const result = sum(a, b);

    //THEN
    expect(result).toBe(9)
});

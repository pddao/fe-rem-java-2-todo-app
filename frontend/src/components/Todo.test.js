import renderer from 'react-test-renderer';
import Todo from "./Todo";

it("snapshot test todo", () => {
    //GIVEN
    const todo = {
        id: "1234",
        description: "Hallo"
    }

    //WHEN
    const tree = renderer.create(<Todo todo={todo}
                                       onAdvance={() => console.log("Advanced")}
                                       onDelete={() => console.log("On Delete")}
    />).toJSON();

    //THEN
    expect(tree).toMatchSnapshot();
});

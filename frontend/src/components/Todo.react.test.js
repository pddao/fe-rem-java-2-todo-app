import { render, fireEvent } from '@testing-library/react'
import Todo from "./Todo";

test('todo item should show contain description text', () => {
    const todoData = {
        id: "some-id",
        description: "shopping"
    }

    const {getByText} = render(
        <Todo
        todo={todoData}
        onAdvance={() => console.log("advanced")}
        onDelete={() => console.log("delete")}
    />)

    const element = getByText("shopping", {exact: false});

    expect(element).toBeInTheDocument()

})


test('todo item should have delete button', () => {
    const todoData = {
        id: "some-id",
        description: "shopping"
    }

    const handleDelete = jest.fn()

    const {getByText} = render(
        <Todo
            todo={todoData}
            onAdvance={() => console.log("advanced")}
            onDelete={handleDelete}
        />)

    const deleteButtonElement = getByText("Delete");

    fireEvent.click(deleteButtonElement)
    expect(handleDelete).toHaveBeenCalledTimes(1)

})

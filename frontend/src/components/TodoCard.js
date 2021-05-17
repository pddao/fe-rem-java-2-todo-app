export function TodoCard({todo, updateToDo, deleteToDo}) {
  return (
    <div>
      <img
        src="https://i.pinimg.com/736x/3f/71/af/3f71af751fdea9f90a94157cc4523c35.jpg"
        alt="Supersüßes Babycapybara"
      />
      <p> {todo.description} </p>
        <button onClick={()=>updateToDo(todo)} disabled={todo.status === "DONE"}>advance</button>
        <button onClick={()=>deleteToDo(todo)}>delete</button>
    </div>
  );
}

export default TodoCard;

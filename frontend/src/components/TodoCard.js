export function TodoCard({todo, updateToDo}) {
  return (
    <div>
      <img
        src="https://i.pinimg.com/736x/3f/71/af/3f71af751fdea9f90a94157cc4523c35.jpg"
        alt="Supersüßes Babycapybara"
      />
      <p> {todo.description} </p>
        <button onClick={()=>updateToDo(todo)}>advance</button>
    </div>
  );
}

export default TodoCard;

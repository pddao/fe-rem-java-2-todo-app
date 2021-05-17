import { useState } from "react";

function AddNewTodo({ addCurrywurst }) {
  const [todoDescription, setTodoDescription] = useState("");

  return (
    <div>
      <form onSubmit={(event)=>{
          event.preventDefault()
          addCurrywurst( todoDescription )
          setTodoDescription("")
      }} >

        <input type="text"
               value={todoDescription}
               onChange={(event)=>setTodoDescription(event.target.value)}/>
        <button type="submit">
          add
        </button>
      </form>
    </div>
  );
}

export default AddNewTodo;

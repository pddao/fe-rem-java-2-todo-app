import { useState } from "react";

function AddNewTodo({ addCurrywurst }) {
  const [todoDescription, setTodoDescription] = useState("");

  return (
    <div>
      <form>
        <input type="text" value={todoDescription} />
        <button onClick={addCurrywurst({ todoDescription })} type="submit">
          add
        </button>
      </form>
    </div>
  );
}

export default AddNewTodo;

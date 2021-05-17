import "./App.css";
import MainBoard from "./components/MainBoard";
import AddNewTodo from "./components/AddNewTodo";
import { useState } from "react";
import * as apiservice from "./service/apiservice";

function App() {
  const [todos, setTodos] = useState([]);

  const addTodo = (description) => {
    const newTodoDto = { description, status: "OPEN" };
    apiservice.postNewTodo(newTodoDto).then((newTodo) => {
      const updatedTodos = [...todos, newTodo];
      setTodos(updatedTodos);
    });
  };

  return (
    <div className="App">
      <MainBoard />
      <AddNewTodo addCurrywurst={addTodo} />
    </div>
  );
}

export default App;

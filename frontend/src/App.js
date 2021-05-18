import MainBoard from "./components/MainBoard";
import AddNewTodo from "./components/AddNewTodo";
import {useEffect, useState} from "react";
import * as apiservice from "./service/apiservice";
import Header from "./components/Header";
import styled from "styled-components/macro";


function App() {
  const [todos, setTodos] = useState([]);

  useEffect(()=>{
    loadData()
  },[])

  const addTodo = (description) => {
    const newTodoDto = { description, status: "OPEN" };
    apiservice.postNewTodo(newTodoDto).then((newTodo) => {
      const updatedTodos = [...todos, newTodo];
      setTodos(updatedTodos);
    });
  };

  const loadData = ()=>{
    apiservice.loadTodos().then((data)=>setTodos(data))
  }

 const updateToDo = (todo)=>{
      const updatedTodo = todo
    if(todo.status === "OPEN"){
        updatedTodo.status = "IN_PROGRESS"
      apiservice.updateTodo(updatedTodo)
    }else if(todo.status === "IN_PROGRESS"){
        updatedTodo.status = "DONE"
      apiservice.updateTodo(updatedTodo)
    }

    const updatedTodos = todos.map((todo) =>
        todo.id === updatedTodo.id ? updatedTodo : todo)
     setTodos(updatedTodos)
 }

 const deleteToDo = (todo)=>{
      apiservice.deleteTodo(todo)
     const updatedTodos = todos.filter((item) => todo.id !== item.id)
     setTodos(updatedTodos)
 }




  return (
    <AppToDo>
        <Header />
      <MainBoard todos ={todos} updateToDo ={updateToDo} deleteToDo = {deleteToDo}/>
      <AddNewTodo addCurrywurst={addTodo} />
    </AppToDo>
  );
}

const AppToDo = styled.div`
  margin: 0;
  width: 100%;
  min-height: 100vh;
  background: #ffdf6b;
  display: grid;
  grid-template-rows: auto 1fr auto;
  
`

export default App;

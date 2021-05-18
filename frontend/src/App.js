import MainBoard from "./components/MainBoard";
import AddNewTodo from "./components/AddNewTodo";
import Header from "./components/Header";
import styled from "styled-components/macro";
import useTodos from "./hooks/useTodos";

function App() {
    const {todos, updateToDo, deleteToDo, addTodo} = useTodos();

    return (
        <AppToDo>
            <Header/>
            <MainBoard todos={todos} updateToDo={updateToDo} deleteToDo={deleteToDo}/>
            <AddNewTodo addCurrywurst={addTodo}/>
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

import TodoCard from "./TodoCard";
import styled from "styled-components/macro";

function Board({title, todos, updateToDo, deleteToDo}) {
    return (
        <BoardStyle>
            <h2>
                {title}
            </h2>

            {todos.map((todo)=>(<TodoCard key ={todo.id}
                                          todo ={todo}
                                          updateToDo ={updateToDo}
                                          deleteToDo = {deleteToDo}/>))}


        </BoardStyle>


    )
}



const BoardStyle = styled.div`
  padding: 5%;
  h2{
    text-align: center;
  }
`

export default Board;
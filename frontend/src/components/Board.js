import TodoCard from "./TodoCard";
import styled from "styled-components/macro";
import PropTypes from "prop-types";


Board.propTypes={
    title: PropTypes.string.isRequired,
    todos: PropTypes.arrayOf(PropTypes.shape({id: PropTypes.string, description: PropTypes.string, status: PropTypes.string})).isRequired,
    updateToDo: PropTypes.func.isRequired,
    deleteToDo: PropTypes.func.isRequired,

}

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
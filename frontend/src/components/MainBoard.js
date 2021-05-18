import Board from "./Board";
import styled from "styled-components/macro";
import PropTypes from 'prop-types';

MainBoard.propTypes={
        todos: PropTypes.arrayOf(PropTypes.shape({id: PropTypes.string, description: PropTypes.string, status: PropTypes.string})).isRequired,
        updateToDo: PropTypes.func.isRequired,
        deleteToDo: PropTypes.func.isRequired,
}

function MainBoard({todos, updateToDo, deleteToDo}) {
    return(
        <MainBoardStyle>
            <Board title="To Do"
                   todos={todos.filter((todo)=>todo.status === "OPEN")}
                   updateToDo ={updateToDo}
                   deleteToDo = {deleteToDo}/>
            <Board title="In Progress"
                   todos={todos.filter((todo)=>todo.status === "IN_PROGRESS")}
                   updateToDo ={updateToDo}
                   deleteToDo = {deleteToDo}/>
            <Board title="Done"
                   todos={todos.filter((todo)=>todo.status === "DONE")}
                   updateToDo ={updateToDo}
                   deleteToDo = {deleteToDo}/>
        </MainBoardStyle>
    )
}

const MainBoardStyle = styled.div`
  margin: 1%;
  gap: 1em;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  height: 100%;
`

export default MainBoard;

import Board from "./Board";


function MainBoard({todos, updateToDo}) {

    return(
        <div>
            <Board title="To Do" todos={todos.filter((todo)=>todo.status === "OPEN")} updateToDo ={updateToDo}/>
            <Board title="In Progress" todos={todos.filter((todo)=>todo.status === "IN_PROGRESS")} updateToDo ={updateToDo}/>
            <Board title="Done" todos={todos.filter((todo)=>todo.status === "DONE")} updateToDo ={updateToDo}/>
        </div>

    )
}



export default MainBoard;
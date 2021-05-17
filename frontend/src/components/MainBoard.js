
import Board from "./Board";


function MainBoard({todos, updateToDo, deleteToDo}) {

    return(
        <div>
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


        </div>

    )
}



export default MainBoard;
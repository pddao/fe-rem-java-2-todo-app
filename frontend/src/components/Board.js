import TodoCard from "./TodoCard";

function Board({title, todos, updateToDo}) {
    return (
        <div>
            <h2>
                {title}
            </h2>

            {todos.map((todo)=>(<TodoCard key ={todo.id} todo ={todo} updateToDo ={updateToDo}/>))}


        </div>


    )
}

export default Board;
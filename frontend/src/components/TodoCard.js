import styled from "styled-components/macro";
import PropTypes from "prop-types";

TodoCard.propTypes = {
    todo: PropTypes.shape({id: PropTypes.string, description: PropTypes.string, status: PropTypes.string}).isRequired,
    updateToDo: PropTypes.func.isRequired,
    deleteToDo: PropTypes.func.isRequired,
}

export function TodoCard({todo, updateToDo, deleteToDo}) {
    return (
        <ToDoCardStyle>
            <img
                src="https://i.pinimg.com/736x/3f/71/af/3f71af751fdea9f90a94157cc4523c35.jpg"
                alt="Supersüßes Babycapybara"
            />
            <p>{todo.description}</p>
            <div className="buttons">
                <button onClick={() => updateToDo(todo)} disabled={todo.status === "DONE"}>advance</button>
                <button onClick={() => deleteToDo(todo)}>delete</button>
            </div>
        </ToDoCardStyle>
    );
}

const ToDoCardStyle = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  background: #ff79cd;
  border-radius: 10px;
  margin-bottom: 1em;
  padding: 5%;
  box-shadow: 10px 10px 1em gray;
  color: white;
  
  p{
    font-weight: 900;
  }
  
  img{
    width: 15vw;
    border-radius: 10px;
  }
  
  button{
  padding: 5%;
  }
  
  .buttons{
    display: flex;
    width: 50%;
    justify-content: space-around;
  }
`

export default TodoCard;

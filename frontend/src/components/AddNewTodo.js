import { useState } from "react";
import styled from "styled-components/macro";
import PropTypes from "prop-types";

AddNewTodo.propTypes={
    addCurrywurst: PropTypes.func.isRequired,
}

function AddNewTodo({ addCurrywurst }) {
  const [todoDescription, setTodoDescription] = useState("");

  return (
    <AddToDoStyle>
      <form onSubmit={(event)=>{
          event.preventDefault()
          addCurrywurst( todoDescription )
          setTodoDescription("")
      }} >

        <input type="text"
               value={todoDescription}
               onChange={(event)=>setTodoDescription(event.target.value)}/>
        <button type="submit">
          add
        </button>
      </form>
    </AddToDoStyle>
  );
}

const AddToDoStyle = styled.div`
  width: 100%;
  form {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 1em;
  }
  
  input{
    width: 80%;
    line-height: 28px;
    margin-right: 1%;
  }
  
  button{
    width: 5%;
  }
`

export default AddNewTodo;

function AddNewTodo({ addCurrywurst }) {
  return (
    <div>
      <form>
        <input type="text" value={todoDescription} />
        <button onClick={addCurrywurst} type="submit">
          add
        </button>
      </form>
    </div>
  );
}

export default AddNewTodo;

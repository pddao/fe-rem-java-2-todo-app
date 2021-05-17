import axios from "axios";

function loadTodos() {
  return axios.get("api/todo").then((response) => response.data);
}

function postNewTodo(newTodo) {
  return axios.post("api/todo", { newTodo }).then((response) => response.data);
}

function deleteTodo(todo) {
  return axios.delete("api/todo/" + todo.id);
}

function updateTodo(todo) {
  return axios
    .put("api/todo" + todo.id, todo)
    .then((response) => response.data);
}

function getTodo(id) {
  return axios.get("api/todo/" + id).then((response) => response.data);
}

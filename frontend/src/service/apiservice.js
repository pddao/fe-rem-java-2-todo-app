import axios from "axios";

export function loadTodos() {
  return axios.get("/api/todo/").then((response) => response.data);
}

export function postNewTodo(newTodo) {
  return axios.post("/api/todo/",  newTodo).then((response) => response.data);
}

export function deleteTodo(todo) {
  return axios.delete("/api/todo/" + todo.id);
}

export function updateTodo(todo) {
  return axios
    .put("/api/todo/" + todo.id, todo)
    .then((response) => response.data);
}

export function getTodo(id) {
  return axios.get("/api/todo/" + id).then((response) => response.data);
}

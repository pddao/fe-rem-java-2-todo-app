import axios from "axios";

function loadTodos() {
  return axios.get("api/todo").then((response) => response.data);
}

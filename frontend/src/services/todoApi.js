import axios from 'axios'

export const getTodos = () =>
  axios.get('api/todo').then((response) => response.data)

export const postTodo = (newTodoDto) =>
  axios.post('api/todo', newTodoDto).then((response) => response.data)

export const deleteTodo = (todo) => axios.delete('api/todo/' + todo.id)

export const putTodo = (todo) =>
  axios.put('api/todo/' + todo.id, todo).then((response) => response.data)

export const getTodo = (id) =>
  axios.get(`/api/todo/${id}`).then((response) => response.data)

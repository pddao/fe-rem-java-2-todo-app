import { useEffect, useState } from 'react'
import AddNewTodo from './components/AddNewTodo'
import AppHeader from './components/AppHeader'
import Boards from './components/Boards'
import PageLayout from './components/PageLayout'
import TodoDetails from './components/TodoDetails'
import { advanceStatus } from './services/advanceStatus'
import * as todoApi from './services/todoApi'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'

export default function App() {
  const [todos, setTodos] = useState([])

  useEffect(() => {
    todoApi.getTodos().then((loadedTodos) => setTodos(loadedTodos))
  }, [])

  const addTodo = (description) => {
    const newTodoDto = { description, status: 'OPEN' }
    todoApi.postTodo(newTodoDto).then((newTodo) => {
      const updatedTodos = [...todos, newTodo]
      setTodos(updatedTodos)
    })
  }

  const deleteTodo = (todoToDelete) => {
    todoApi.deleteTodo(todoToDelete).then(() => {
      const updatedTodos = todos.filter((todo) => todo.id !== todoToDelete.id)
      setTodos(updatedTodos)
    })
  }

  const advanceTodo = (todoToAdvance) => {
    const advancedTodo = {
      ...todoToAdvance,
      status: advanceStatus(todoToAdvance.status),
    }
    todoApi.putTodo(advancedTodo).then((updatedTodo) => {
      const updatedTodos = todos.map((todo) =>
        todo.id === updatedTodo.id ? updatedTodo : todo
      )
      setTodos(updatedTodos)
    })
  }

  return (
    <Router>
      <Switch>
        <Route exact path="/">
          <PageLayout>
            <AppHeader />
            <Boards
              todos={todos}
              onDelete={deleteTodo}
              onAdvance={advanceTodo}
            />
            <AddNewTodo onAdd={addTodo} />
          </PageLayout>
        </Route>
        <Route path="/todo/:id">
          <TodoDetails />
        </Route>
      </Switch>
    </Router>
  )
}

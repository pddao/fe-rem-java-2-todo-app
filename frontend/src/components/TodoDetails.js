import React, { useEffect, useState } from 'react'
import Todo from './Todo'
import { useParams } from 'react-router-dom'
import { getTodo } from '../services/todoApi'

export default function TodoDetails() {
  const { id } = useParams()
  const [todo, setTodo] = useState(null)

  useEffect(() => {
    getTodo(id).then(setTodo)
  }, [id])

  return <>{todo && <Todo todo={todo} detailView />}</>
}

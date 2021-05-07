import styled from 'styled-components/macro'

import { Button, CardContent } from '@material-ui/core'
import Card from '@material-ui/core/Card'
import CardActions from '@material-ui/core/CardActions'
import CardMedia from '@material-ui/core/CardMedia'
import makeStyles from '@material-ui/core/styles/makeStyles'
import { Link } from 'react-router-dom'

const useStyles = makeStyles(() => ({
  media: {
    height: 0,
    paddingTop: '56.25%',
  },
}))

export default function Todo({ todo, onDelete, onAdvance, detailView }) {
  const classes = useStyles()

  return (
    <Card>
      <CardMedia
        image="https://cdn.shibe.online/shibes/8c463b877b520f4e5de642d0b94325b7e9d3e262.jpg"
        title="Paella dish"
        className={classes.media}
      />

      <CardContent>
        <Description>Description: {todo.description}</Description>
      </CardContent>
      {!detailView && (
        <CardActions>
          <Button color="secondary" onClick={() => onDelete(todo)}>
            Delete
          </Button>

          <Button color="primary" component={Link} to={`/todo/${todo.id}`}>
            Details
          </Button>

          {onAdvance && (
            <Button color="primary" onClick={() => onAdvance(todo)}>
              Advance
            </Button>
          )}
        </CardActions>
      )}
    </Card>
  )
}

const Description = styled.span`
  grid-column: span 2;
  font-weight: 600;
`

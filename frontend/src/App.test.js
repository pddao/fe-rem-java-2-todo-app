import {render, screen} from '@testing-library/react'
import App from './App'
import {rest} from 'msw'
import {setupServer} from 'msw/node'
import {waitFor} from "@testing-library/dom";


const server = setupServer(
    rest.get('/api/todo', (req, res, ctx) => {
        const body = [
            {
                id: 'first-id',
                description: 'First todo',
                status: 'OPEN'
            },
            {
                id: 'second-id',
                description: 'Second todo',
                status: 'IN_PROGRESS'
            }
        ];
        const bodyAsJson = ctx.json(body);
        return res(bodyAsJson)
    })
)

beforeAll(() => server.listen())
afterEach(() => server.resetHandlers())
afterAll(() => server.close())


test('renders all todos from backend', async () => {
    render(<App/>)

    await waitFor(() => screen.getByText('First todo', {exact: false}))

    const firstTodoElement = screen.getByText('First todo', {exact: false})
    expect(firstTodoElement).toBeInTheDocument()

    const secondTodoElement = screen.getByText('Second todo', {exact: false})
    expect(secondTodoElement).toBeInTheDocument()
})

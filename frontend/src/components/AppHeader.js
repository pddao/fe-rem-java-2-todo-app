import styled from 'styled-components/macro'

export default function AppHeader() {
  return (
    <Header>
      <h1>Super Kanban</h1>
    </Header>
  )
}

const Header = styled.header`
  padding: 0 16px;
  background: #222;
  color: var(--accent);
`

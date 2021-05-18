import styled from 'styled-components'
function Header(){
    return(
        <Title>
            <img src="https://i.pinimg.com/originals/f2/f0/e0/f2f0e067ce9b8de1cdd98c9ee9605b55.png" alt=""/>
            <h1>Super Cabykanban-Babyboard 😍 </h1>
            <div></div>
        </Title>
    )
}

const Title = styled.div`
  margin:0;
  width: 100%;
  background: #23049d;
  color: white;
  display: grid;
  grid-template-columns: 1fr auto 1fr;
  align-items: center;
  h1 {
    margin:0;
    padding: 1em;
  }
  img{
    height: 7em;
  }
`


export default Header;
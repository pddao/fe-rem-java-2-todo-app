import { createGlobalStyle } from 'styled-components'

export default createGlobalStyle`
  :root {
    --accent: deeppink; 
  }

  * {
    box-sizing: border-box;
  }

  html, body {
    margin: 0;
    font-family: sans-serif;
  }

  input, button {
    font-size: 1em;
    font-family: inherit;
  }

  button {
    cursor: pointer;
  }
`

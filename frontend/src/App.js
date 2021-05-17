import './App.css';
import MainBoard from "./components/MainBoard";
import AddNewTodo from "./components/AddNewTodo";


function App() {
    return (
        <div className="App">
            <MainBoard/>
            <AddNewTodo/>
        </div>
    );
}

export default App;

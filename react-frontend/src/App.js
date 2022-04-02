import './App.css';
import { BrowserRouter as Router, Switch, Route} from "react-router-dom";
import Test from "./components/Test";
import MainPage from "./pages/MainPage";

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route exact path="/" component={MainPage}/>
          <Route path="/test" component={Test}/>
        </Switch>
      </Router>
    </div>
  );
}

export default App;

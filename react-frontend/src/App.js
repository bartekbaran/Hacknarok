import './App.css';
import {BrowserRouter as Router, Switch, Route} from "react-router-dom";
import MainPage from "./pages/MainPage";
import Navbar from "./components/Navbar";
import Authentication from "./pages/Authentication";
import SearchBar from "./components/MenuBar";
import {useState} from "react";
import {useCycle} from "framer-motion";

function App() {
  const [searchBarContent, setSearchBarContent] = useState("")
  const [isAuthenticating, setIsAuthenticating] = useCycle(true, false)

  return (
    <div className="App">
      <Router>
        <Navbar {...{isAuthenticating, setIsAuthenticating}}/>
        {isAuthenticating && (<SearchBar {...{setSearchBarContent}}/>)}
        <Switch>
          <Route exact path="/" component={MainPage}/>
          <Route path="/authentication" component={Authentication}/>
        </Switch>
      </Router>
    </div>
  );
}

export default App;

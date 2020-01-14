import React, { useState } from "react";
import facade from "./apiFacade";
import WelcomePage from "./components/Welcome.jsx";
import LogInScreen from "./components/LogInScreen.jsx";
import AllMovie from "./components/AllMovie.jsx";
import SimpleMovie from "./components/SimpleMovie.jsx";
import Admin from "./components/Admin.jsx";
import "bootstrap/dist/css/bootstrap.min.css";
import './index.css';
import './Welcome.css';
import './App.css'

import {
  HashRouter as Router,
  Switch,
  Route,
  NavLink,
} from "react-router-dom";


const App = () => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [roles, setRoles] = useState([]);
  return (
    <>
      <Router>
        <Header
          loggedIn={loggedIn}
          setLoggedIn={setLoggedIn}
          roles={roles}
          setRoles={setRoles} />
        <div className="container">
          <Switch>
            <Route exact path="/"
              component={WelcomePage} />
            <Route path="/login">
              <LogInScreen
                loggedIn={loggedIn}
                setLoggedIn={setLoggedIn}
                roles={roles}
                setRoles={setRoles} />
            </Route>
            <Route path="/simplemovie">
              <SimpleMovie />
            </Route>
            <Route path="/allmovie">
              <AllMovie
                loggedIn={loggedIn} />
            </Route>
            <Route path="/admin">
              <Admin
                loggedIn={loggedIn}
                roles={roles} />
            </Route>
            <Route component={NoMatch} />
          </Switch>
        </div>
      </Router>
    </>
  );
};

const NoMatch = () => (
  <div>You're trying to access a resource that doesn't exist.</div>
);

const Header = ({ loggedIn, setLoggedIn, roles, setRoles }) => {
  return (
    <ul className="header">
      <li>
        <NavLink exact activeClassName="active" to="/">
          Home
        </NavLink>
      </li>

      {/*Login*/}
      {!loggedIn &&
        <li>
          <NavLink activeClassName="active" to="/login">
            Login
          </NavLink>
        </li>
      }

      {/*Logout*/}
      {loggedIn &&
        <li>
          <NavLink onClick={() => {
            setLoggedIn(false);
            facade.logout();
            setRoles([]);
          }} to="/login">
            Log out
          </NavLink>
        </li>
      }

      {!loggedIn &&
        <li>
          <NavLink activeClassName="active" to="/simplemovie">
            Simple Movie Data
          </NavLink>
        </li>
      }

      {loggedIn &&
        <li>
          <NavLink activeClassName="active" to="/allmovie">
            All Movie Data
          </NavLink>
        </li>
      }

      {loggedIn && roles.includes("admin") &&
        <li>
          <NavLink to="/admin">
            Admin
        </NavLink>
        </li>
      }
    </ul>
  );
};


export default App;

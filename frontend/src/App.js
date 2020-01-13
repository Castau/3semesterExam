import React, { useState } from "react";
import facade from "./apiFacade";
import "bootstrap/dist/css/bootstrap.min.css";
import WelcomePage from "./components/Welcome.jsx";
import Search from "./components/Search.jsx";
import Admin from "./components/Admin.jsx";
import LogInScreen from "./components/LogInScreen.jsx";
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
            <Route path="/search">
              <Search
                permission={loggedIn} />
            </Route>
            <Route path="/admin">
              <Admin
                loggedIn={loggedIn}
                roles={roles} />
            </Route>
            <Route
              component={NoMatch} />
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

      {/*Permission user*/}
      {loggedIn &&
        <li>
          <NavLink to="/search">
            Search
        </NavLink>
        </li>
      }

      {/*Permission admin*/}
      {loggedIn && roles.includes("admin") &&
        <li>
          <NavLink to="/admin">
            Admin panel
        </NavLink>
        </li>
      }
    </ul>
  );
};


export default App;

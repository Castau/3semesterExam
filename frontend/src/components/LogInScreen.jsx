import React, { useState } from "react";
import facade from "../apiFacade";
import ShowRoles from "./ShowRoles.jsx";
import { LogIn } from "./Login.jsx";
import { Badge } from 'react-bootstrap';


const LogInScreen = ({ loggedIn, setLoggedIn, roles, setRoles }) => {
  const [loginErrorMessage, setLoginErrorMessage] = useState("");
  const login = (user, pass) => {
    facade
      .login(user, pass, setRoles)
      .then(res => {
        setLoginErrorMessage("");
        setLoggedIn(true);
      })
      .catch(err => {
        if (err.status) {
          setLoginErrorMessage("Failed to log in, check your information");
          err.fullError.then(e => console.log(e.code, e.message));
        } else {
          console.log("Network error");
        }
      });
  };

  return (
    <>
      {!loggedIn &&
        <LogIn login={login} loginErrorMessage={loginErrorMessage} />
      }

      {loggedIn &&
        roles.length &&
        <>
          <ShowRoles roles={roles} />
          <LoggedIn />
        </>
      }
    </>
  );
};
export default LogInScreen;

const LoggedIn = () => {

  return (
    <>
      <h1 className="mb-3"> <Badge variant="info">You are now logged in</Badge></h1>
      {/* <Link to="/search">Searching tool</Link> */}
      <br></br>
      <br></br>
    </>
  );
};

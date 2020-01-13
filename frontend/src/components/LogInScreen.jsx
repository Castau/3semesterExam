import React, { useState, useEffect } from "react";
import facade from "../apiFacade";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from "react-router-dom";
import ShowRoles from "./ShowRoles.jsx";
import { LogIn, LoggedIn } from "./Login.jsx";


const LogInScreen = ({ loggedIn, setLoggedIn, roles, setRoles }) => {
  const [message, setMessage] = useState("");
  const login = (user, pass) => {
    facade
      .login(user, pass, setRoles)
      .then(res => {
        setMessage("");
        setLoggedIn(true);
      })
      .catch(err => {
        if (err.status) {
          setMessage("Failed to log in, check your information");
          err.fullError.then(e => console.log(e.code, e.message));
        } else {
          console.log("Network error");
        }
      });
  };

  return (
    <div>
      {!loggedIn ? (
        <LogIn login={login} message={message} />
      ) : (
          <div>
            {roles.length &&
              <>
                <ShowRoles roles={roles} />
                <LoggedIn />
              </>
            }
          </div>
        )}
      <br></br>
      {/* <Link to="/">Back to WelcomePage</Link> */}
    </div>
  );
};

export default LogInScreen;
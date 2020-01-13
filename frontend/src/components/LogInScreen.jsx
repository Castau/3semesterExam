import React, { useState } from "react";
import facade from "../apiFacade";
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
    <>
      {!loggedIn &&
        <LogIn login={login} message={message} />
      }

      {loggedIn &&
        roles.length &&
        <>
          <ShowRoles roles={roles} />
          <LoggedIn />
        </>
      }
    </>
    // <div>
    //   {!loggedIn ? (
    //     <LogIn login={login} message={message} />
    //   ) : (
    //       <div>
    // {roles.length &&
    //   <>
    //     <ShowRoles roles={roles} />
    //     <LoggedIn />
    //   </>
    // }
    //       </div>
    //     )}
    // </div>
  );
};

export default LogInScreen;
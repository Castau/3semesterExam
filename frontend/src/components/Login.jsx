import React, { useState } from "react";
import { Badge } from 'react-bootstrap';
import "bootstrap/dist/css/bootstrap.min.css";

export const LogIn = ({ login, message }) => {
  const [user, setUser] = useState({ username: "", password: "" });

  function log_in(evt) {
    evt.preventDefault();
    login(user.username, user.password);
  }

  const onChange = evt => {
    setUser({ ...user, [evt.target.id]: evt.target.value });
  };

  return (
    <div>
      <h3 className="mt-3 mb-3"> <Badge variant="info">Please log in to use the application</Badge></h3>
      <form onSubmit={log_in}>
        <input placeholder="User Name" id="username" onChange={onChange} />{" "}
        <input
          placeholder="Password"
          id="password"
          type="password"
          onChange={onChange}
        />{" "}
        <button className="btn-dark">Login</button>
        <br></br>
        <p>{message}</p>
      </form>
    </div>
  );
};

export const LoggedIn = () => {

  return (
    <>
      <h2>You are now logged in</h2>
      {/* <Link to="/search">Searching tool</Link> */}
      <br></br>
      <br></br>
    </>
  );
};

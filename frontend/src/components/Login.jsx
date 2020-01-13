import React, { useState } from "react";
import { Badge } from 'react-bootstrap';

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
      <h3 className="mt-2"> <Badge variant="info">Please log in to use the application</Badge></h3>
      <h4> <Badge variant="danger">{message}</Badge></h4>
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
      </form>
    </div>
  );
};

export const LoggedIn = () => {

  return (
    <>
      <h1 className="mb-3"> <Badge variant="info">You are now logged in</Badge></h1>
      {/* <Link to="/search">Searching tool</Link> */}
      <br></br>
      <br></br>
    </>
  );
};

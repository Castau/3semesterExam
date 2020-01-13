import React, { useState } from "react";
import facade from "../apiFacade";
import { Badge, Card, Container, Row, ListGroup } from 'react-bootstrap';

export const LogIn = ({ login, message }) => {
  const [user, setUser] = useState({ username: "", password: "" });
  const [errorMessage, setErrorMessage] = useState('');
  const [data, setData] = useState('');

  function log_in(evt) {
    evt.preventDefault();
    login(user.username, user.password);
  }

  const onChange = evt => {
    setUser({ ...user, [evt.target.id]: evt.target.value });
  };

  const handleClick = () => {
    facade
      .fetchGetData('testdata', '')
      .then(res => {
        setData(res);
        setErrorMessage('');
      })
      .catch(err => {
        if (err.status) {
          err.fullError.then(e => {
            setErrorMessage(e.message);
            console.log(e.code, e.message);
          });
        } else {
          console.log('Network error');
        }
      });
  };

  return (
    <div>
      <h3 className="mt-2"> <Badge variant="info">Please log in to use the application</Badge></h3>
      <h4> <Badge variant="danger">{message}</Badge></h4>
      <form onSubmit={log_in}>
        <input placeholder="User Name" id="username" onChange={onChange} />{" "}
        <input placeholder="Password" id="password" type="password" onChange={onChange} />
        <button className="btn-dark mb-3">Login</button>
      </form>
      <Container className="mt-4">
        <Row >
          <Card className="w-25">
            <Card.Body>
              <h5><Badge variant="info">Supply database with data</Badge></h5>
              <button className="btn-dark mb-3" onClick={handleClick}>Generate data</button>
              <p className="mt-3">{data.message} </p>
              <h5><Badge variant="info">Test Users</Badge></h5>
              <ListGroup className="about_link" variant="flush">
                <ListGroup.Item>
                  <p className="mb-0">User: admin</p><p className="mb-0"> Pass: admin</p>
                </ListGroup.Item>
                <ListGroup.Item>
                  <p className="mb-0">User: user</p><p className="mb-0"> Pass: user</p>
                </ListGroup.Item>
                <ListGroup.Item>
                  <p className="mb-0">User: karen77</p><p className="mb-0"> Pass: mortil3</p>
                </ListGroup.Item>
                <ListGroup.Item>
                  <p className="mb-0">User: vlad</p><p className="mb-0"> Pass: mrpresident</p>
                </ListGroup.Item>
                <ListGroup.Item>
                  <p className="mb-0">User: therealhat</p><p className="mb-0"> Pass: tophat</p>
                </ListGroup.Item>
                <ListGroup.Item>
                  <p className="mb-0">User: noone</p><p className="mb-0"> Pass: arya</p>
                </ListGroup.Item>
              </ListGroup>
            </Card.Body>
          </Card>
        </Row>
      </Container>
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

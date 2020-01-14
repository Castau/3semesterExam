import React, { useState, useEffect } from 'react';
import facade from "../apiFacade.jsx";
import { Container, Row, Card, Badge } from 'react-bootstrap';

const Admin = ({ loggedIn, roles }) => {
  const [title, setTitle] = useState('');
  const [countInfo, setCountInfo] = useState('');
  const [movieErrorMessage, setmovieErrorMessage] = useState('');

  const onChange = evt => {
    setTitle(evt.target.value);
  };

  const onClick = () => {
    facade
      .fetchGetData('movieCount', title)
      .then(res => {
        setCountInfo(res);
        setmovieErrorMessage('');
      })
      .catch(err => {
        console.log(err);
        if (err.status) {
          err.fullError.then(e => {
            setmovieErrorMessage(e.message);
            console.log(e.code, e.message);
          });
        } else {
          console.log('Network error');
        }
      });
  };

  if (!loggedIn || !roles.includes("admin")) {
    return (
      <>
        <h4 className="mt-5 mb-3"> <Badge variant="danger">You are not logged in as admin</Badge></h4>
      </>
    )
  } else {
    return (
      <>
        <h4 className="mt-5 mb-3"> <Badge variant="info">Check amount of requests for specific movie</Badge></h4>
        <h4> <Badge variant="danger">{movieErrorMessage}</Badge></h4>
        <input placeholder="Movie Title" id="title" type="text" onChange={onChange} />
        <button className="btn-dark mb-3" onClick={onClick}>Check movie requests</button>

        {countInfo &&
          <Card className="p-3">
            <p>Title: {countInfo.title}</p>
            <p>Amount of requests: {countInfo.count}</p>
          </Card>}
      </>
    );
  }
}

export default Admin;
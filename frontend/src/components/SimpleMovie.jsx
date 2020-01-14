import React, { useState } from "react";
import facade from "../apiFacade";
import { Badge, Card } from 'react-bootstrap';

const SimpleMovie = () => {
  const [title, setTitle] = useState('');
  const [movieInfo, setMovieInfo] = useState('');
  const [movieErrorMessage, setmovieErrorMessage] = useState('');

  const onChange = evt => {
    setTitle(evt.target.value);
  };

  const reset = () => {
    setTitle('');
    setMovieInfo('');
  }

  const onClick = () => {
    facade
      .fetchGetData('movieInfoSimple', title)
      .then(res => {
        setMovieInfo(res);
        setmovieErrorMessage('');
        setTitle('');
      })
      .catch(err => {
        if (err.status) {
          err.fullError.then(e => {
            setmovieErrorMessage(e.message);
            reset();
            console.log(e.code, e.message);
          });
        } else {
          console.log('Network error');
          reset();
        }
      });
  };

  return (
    <>
      <h2 className="mt-5 "><Badge variant="info">Simple Movie Data</Badge></h2>
      <h4> <Badge variant="danger">{movieErrorMessage}</Badge></h4>
      <input placeholder="Movie Title" value={title} id="title" type="text" onChange={onChange} />
      <button className="btn-dark mb-3" onClick={onClick}>Movie Info</button>
      {movieInfo &&
        <Card className="p-3">
          <h3><Badge variant="success">{movieInfo.title}</Badge></h3>
          <p>Released: {movieInfo.year}</p>
          <p>Genre: {movieInfo.genres}</p>
          <p>Directors: {movieInfo.directors}</p>
          <p>Actors: {movieInfo.cast}</p>
          <p>Resumé: {movieInfo.plot}</p>
          {movieInfo &&
            <center>
              <img className="imgscale" src={movieInfo.poster}></img>
            </center>}
        </Card>}
    </>
  );
};

export default SimpleMovie;
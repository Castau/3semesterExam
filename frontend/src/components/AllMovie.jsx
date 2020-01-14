import React, { useState } from "react";
import facade from "../apiFacade";
import { Badge, Row, Card, Col } from 'react-bootstrap';


const AllMovie = () => {
  const [title, setTitle] = useState('');
  const [movieInfo, setMovieInfo] = useState('');
  const [movieErrorMessage, setmovieErrorMessage] = useState('');

  const onChange = evt => {
    setTitle(evt.target.value);
  };

  const onClick = () => {
    facade
      .fetchGetData('movieInfoAll', title)
      .then(res => {
        setMovieInfo(res);
        setmovieErrorMessage('');
      })
      .catch(err => {
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
  return (
    <>
      <h2 className="mt-5 "><Badge variant="info">All Movie Data</Badge></h2>
      <h4> <Badge variant="danger">{movieErrorMessage}</Badge></h4>
      <input placeholder="Movie Title" id="title" type="text" onChange={onChange} />
      <button className="btn-dark mb-3" onClick={onClick}>Movie Info</button>
      {movieInfo &&
        <Card className="p-3">
          <h3><Badge variant="success">{movieInfo.title}</Badge></h3>
          <p>Released: {movieInfo.year}</p>
          <p>Genre: {movieInfo.genres}</p>
          <p>Directors: {movieInfo.directors}</p>
          <p>Actors: {movieInfo.cast}</p>
          <p>Resumé: {movieInfo.plot}</p>
          <Row noGutters={true}>
            <Col>
              <Card>
                <h5><Badge variant="info">IMDB</Badge></h5>
                <p>Rating: {movieInfo.imdb.rating}</p>
                <p>Rating: {movieInfo.imdb.votes}</p>
              </Card>
              <Card>
                <h5><Badge variant="info">Meta Critic</Badge></h5>
                <p>Rating: {movieInfo.metaCritic.metacritic}</p>
              </Card>
            </Col>
            <Col>
              <Card>
                <h5><Badge variant="info">Rotten Tomatoes</Badge></h5>
                <Col>
                  <h6><Badge variant="secondary">Critics</Badge></h6>
                  <p>Meter: {movieInfo.tomatoes.criticMeter}</p>
                  <p>Num. reviews: {movieInfo.tomatoes.criticNumReviews}</p>
                  <p>Rating: {movieInfo.tomatoes.criticRating}</p>
                </Col>
                <Col>
                  <h6><Badge variant="secondary">Viewers</Badge></h6>
                  <p>Meter: {movieInfo.tomatoes.viewerMeter}</p>
                  <p>Num. reviews: {movieInfo.tomatoes.viewerNumReviews}</p>
                  <p>Rating: {movieInfo.tomatoes.viewerRating}</p>
                </Col>
              </Card>
            </Col>
          </Row>
          {movieInfo && <img src={movieInfo.poster}></img>}
        </Card>}
    </>
  );
};

export default AllMovie;
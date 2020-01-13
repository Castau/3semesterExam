import React, { useState } from "react";
import facade from "../apiFacade";
import "bootstrap/dist/css/bootstrap.min.css";
import HobbyTable from "./HobbyTable.jsx";
import { Container, Row, Col, Badge } from 'react-bootstrap';

const Search = ({ permission }) => {
  if (!permission) {
    return (
      <>
        <h4 className="mt-5 mb-5"> <Badge variant="danger">You are not logged in</Badge></h4>
      </>
    );
  } else {
    return (
      <>
        <SearchFunction />
      </>
    );
  }
};

export default Search;

const SearchFunction = () => {
  const [persons, setPersons] = useState();
  const [allHobbies, setAllHobbies] = useState();
  const [errorMessage, setErrorMessage] = useState();

  const [ID, setID] = useState('');
  const [Email, setEmail] = useState('');
  const [Phone, setPhone] = useState('');
  const [Hobby, setHobby] = useState('');

  const getClientData = (endpoint, value, type) => {
    facade
      .fetchGetData(endpoint, value)
      .then(res => {
        type === 'person' ? setPersons(res) : setAllHobbies(res);
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

  const handleChange = event => {
    if (event.target.id === 'personID') {
      setID(event.target.value);
    } else if (event.target.id === 'personEmail') {
      setEmail(event.target.value);
    } else if (event.target.id === 'personPhone') {
      setPhone(event.target.value);
    } else if (event.target.id === 'personHobby') {
      setHobby(event.target.value);
    }
  };

  const handleSubmit = (event) => {
    if (event.target.id === 'buttonID') {
      getClientData('id', ID, 'person');
      setID('');
    } else if (event.target.id === 'buttonEmail') {
      getClientData('email', Email, 'person');
      setEmail('');
    } else if (event.target.id === 'buttonPhone') {
      getClientData('phone', Phone, 'person');
      setPhone('');
    } else if (event.target.id === 'buttonHobby') {
      getClientData('hobby', Hobby, 'person');
      setHobby('');
    } else if (event.target.id === 'buttonAllHobbies') {
      getClientData('hobby', 'all', 'hobby');
      setAllHobbies('');
    }

  };

  return (
    <>
      <h4 className="mt-5 mb-5"> <Badge variant="danger">{errorMessage}</Badge></h4>
      <h1 className="mt-5 mb-5"> <Badge variant="secondary"> Search for people and hobbies</Badge></h1>
      <Container className="mt-5 mb-5">
        <Row className="mt-3">
          <Col>
            <center>
              <input id="personID" placeholder='Person ID' value={ID} className="w-75 block" type="number" onChange={handleChange} />
              <button id="buttonID" className="btn-dark w-75 pt-2 pb-2" type="button" onClick={handleSubmit}>
                Get person by ID
            </button>
            </center>
          </Col>
          <Col>
            <center>
              <input id="personEmail" placeholder='Email' value={Email} className="w-75 block" type="text" onChange={handleChange} />
              <button id="buttonEmail" className="btn-dark w-75 pt-2 pb-2" type="button" onClick={handleSubmit}>
                Get person by Email
            </button>
            </center>
          </Col>
        </Row>
        <Row className="mt-3">
          <Col>
            <center>
              <input id="personPhone" placeholder='Phone number' value={Phone} className="w-75 block" type="number" onChange={handleChange} />
              <button id="buttonPhone" className="btn-dark w-75 pt-2 pb-2" type="button" onClick={handleSubmit}>
                Get person by Phone
              </button>
            </center>
          </Col>
          <Col>
            <center>
              <input id="personHobby" placeholder='Hobby' value={Hobby} className="w-75 block" type="text" onChange={handleChange} />
              <button id="buttonHobby" className="btn-dark w-75 pt-2 pb-2" type="button" onClick={handleSubmit}>
                Get persons by Hobby
              </button>
            </center>
          </Col>
        </Row>
        <Row className="mt-5">
          <Col>
            <center>
              <button id="buttonAllHobbies" className="btn-dark w-25 pt-2 pb-2" type="button" onClick={handleSubmit}>
                Get All Hobbies
              </button>
            </center>
          </Col>
        </Row>
        <Row className="mt-5">
          {persons && <MemberTable members={!Array.isArray(persons) ? [persons] : persons} />}
          {allHobbies && <HobbyTable hobbies={!Array.isArray(allHobbies) ? [allHobbies] : allHobbies} />}
        </Row>
      </Container>
    </>
  );
};

function MemberTable({ members }) {
  const tableItems = members.map(member => (
    <MemberTableRow
      key={member.id}
      id={member.id}
      fName={member.fName}
      lName={member.lName}
      mail={member.mail}
      telephone={member.telephone}
      hobbylist={member.hobbylist}
      residence={member.residence}
    />
  ));
  return (
    <table className="table">
      <thead className="thead-dark">
        <tr>
          <th>ID</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Email</th>
          <th>Phone</th>
          <th>Hobbies</th>
          <th>Address</th>
          <th>City</th>
        </tr>
      </thead>
      <tbody>{tableItems}</tbody>
    </table>
  );
}

function MemberTableRow(props) {
  // destructuring 
  const { id, fName, lName, mail, telephone, hobbylist, residence } = props;
  return (
    <tr>
      <td>{id}</td>
      <td>{fName}</td>
      <td>{lName}</td>
      <td>{mail}</td>
      <td>{telephone}</td>
      <td>
        {hobbylist.map((element, index) => {
          return <li key={index}>{element.hobbyName}</li>;
        })}
      </td>
      <td>{(residence.road)}</td>
      <td>{(residence.town)}</td>
    </tr>
  );
}



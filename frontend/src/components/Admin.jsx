import React, { useState, useEffect } from 'react';
import facade from "../apiFacade.jsx";
import "bootstrap/dist/css/bootstrap.min.css";
import HobbyTable from "./HobbyTable.jsx";
import { Container, Row, Col, Badge } from 'react-bootstrap';

const Admin = ({ loggedIn, roles }) => {
  const [updateTable, setUpdateTable] = useState(true);
  const [errorMessage, setErrorMessage] = useState();
  const initialState = { id: 0, hobbyName: '', hobbyDescription: '' };
  const [hobby, setHobby] = useState(initialState);

  if (!loggedIn || !roles.includes("admin")) {
    return (
      <>
        <h4 className="mt-5 mb-5"> <Badge variant="danger">You are not logged in as admin</Badge></h4>
      </>
    )
  } else {
    return <>
      <AddEditHobby
        setUpdateTable={setUpdateTable}
        errorMessage={errorMessage}
        setErrorMessage={setErrorMessage}
        hobby={hobby}
        setHobby={setHobby}
        initialState={initialState}
      />

      <GetAllHobbies
        updateTable={updateTable}
        setUpdateTable={setUpdateTable}
        setErrorMessage={setErrorMessage}
        setHobby={setHobby}
      />
    </>;
  }
}

export default Admin;

const AddEditHobby = ({ setUpdateTable, errorMessage, setErrorMessage, hobby, setHobby, initialState }) => {
  const [buttontext, setButtontext] = useState(hobby.id > 0 ? 'Edit ' : 'Add ');

  useEffect(() => {
    setButtontext(hobby.id > 0 ? 'Edit ' : 'Add ');
  }, [hobby]);


  const handleChange = event => {
    const value = event.target.value;
    const id = event.target.id;
    setHobby({ ...hobby, [id]: value });
  }

  const handleSubmit = () => {
    if (hobby.hobbyName === '' || hobby.hobbyDescription === '') {
      setErrorMessage('Hobby name or description is empty!');
      return;
    }
    if (hobby.id === 0) {
      facade.add(hobby).then(() => {
        setUpdateTable(true);
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

    } else {
      facade.edit(hobby).then(() => {
        setUpdateTable(true);
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
    }
    setHobby({ ...initialState });
  }

  const cancelEditButton = () => {
    if (hobby.id > 0) {

      const handleClick = () => {
        setHobby({ ...initialState });
      }

      return (
        <button id="cancelEditbutton" type="button" className="btn-danger w-25" onClick={handleClick}>Cancel Edit</button>
      )
    }
  }

  return (
    <div>
      <h4 className="mt-5 mb-5"> <Badge variant="danger">{errorMessage}</Badge></h4>
      <h1 className="mt-5 mb-5"> <Badge variant="secondary">{buttontext}hobby</Badge></h1>
      <Container className="mt-5 mb-5">
        <Row className="mt-5">
          <Col>
            <center>
              <input id='hobbyName' className="w-100 block" type='text' placeholder='Hobby Name' value={hobby.hobbyName} onChange={handleChange}></input>
              <input id='hobbyDescription' className="w-100 block" type='text' placeholder='Hobby Description' value={hobby.hobbyDescription} onChange={handleChange}></input>
            </center>
            {cancelEditButton()}
          </Col>
          <Col>
            <center>
              <button id="addEditbutton" type="button" className="btn-dark w-75 pt-3 pb-3" onClick={handleSubmit}>{buttontext}this hobby</button>
            </center>
          </Col>
        </Row>
      </Container>
    </div>
  );
}

const GetAllHobbies = ({ updateTable, setUpdateTable, setErrorMessage, setHobby }) => {
  const [allHobbies, setAllHobbies] = useState();

  const getClientData = (endpoint, value) => {
    facade
      .fetchGetData(endpoint, value)
      .then(res => {
        setAllHobbies(res);
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

  useEffect(() => {
    if (updateTable) {
      getClientData('hobby', 'all', 'hobby');
      setAllHobbies('');
      setUpdateTable(false);
    }
  }, [updateTable]);

  return (
    <>
      <Container className="mt-5 mb-5">
        <Row className="mt-5">
          {allHobbies && <HobbyTable isAdmin={true} setHobby={setHobby} hobbies={!Array.isArray(allHobbies) ? [allHobbies] : allHobbies} />}
        </Row>
      </Container>
    </>
  );
};

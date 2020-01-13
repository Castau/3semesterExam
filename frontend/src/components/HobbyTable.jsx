import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";

function HobbyTable({ hobbies, isAdmin, setHobby }) {
  const tableItems = hobbies.map(hobby => (
    <HobbyTableRow
      key={hobby.id}
      id={hobby.id}
      name={hobby.hobbyName}
      description={hobby.hobbyDescription}
      isAdmin={isAdmin}
      setHobby={setHobby}
    />
  ));
  return (
    <table className="table">
      <thead className="thead-dark">
        <tr>
          <th>ID</th>
          <th>Hobby</th>
          <th>Description</th>
          {adminPanel(isAdmin)}
        </tr>
      </thead>
      <tbody>{tableItems}</tbody>
    </table>
  );
}

function HobbyTableRow(props) {
  const handleClick = (event) => {
    if (event.target.id === 'editButton') {
      props.setHobby({ id: props.id, hobbyName: props.name, hobbyDescription: props.description })
    } else if (event.target.id === 'deleteButton') {

    }
  }

  const addEditButtonDelete = () => {
    if (props.isAdmin) {
      return (
        <td>
          <button id="editButton" className="btn btn-secondary mr-2" onClick={handleClick}>Edit</button>
          <button id="deleteButton" className="btn btn-secondary" onClick={handleClick}>Delete</button>
        </td>
      )
    }
  }

  return (
    <tr>
      <td>{props.id}</td>
      <td>{props.name}</td>
      <td>{props.description}</td>
      {addEditButtonDelete()}
    </tr>
  );
}

function adminPanel(isAdmin) {
  if (isAdmin) {
    return (
      <th>Admin Panel</th>
    )
  }
}

export default HobbyTable;
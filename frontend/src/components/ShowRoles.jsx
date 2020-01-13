import React from "react";
import { Badge } from 'react-bootstrap';

const Show = ({ roles }) => {
  let prettyRoles = roles;
  prettyRoles = prettyRoles.replace(/\[|\]|\"/g, '').replace(',', ' & ').toUpperCase();

  return (
    <h4 className="mt-5 mb-5"><Badge variant="success">Permission: {prettyRoles}</Badge></h4>
  );
};

export default Show;
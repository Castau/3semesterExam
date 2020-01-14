import React, { useState } from "react";
import facade from "../apiFacade";
import { Badge } from 'react-bootstrap';

const SimpleMovie = () => {

  return (
    <>
      <h4 className="mt-5 mb-5"><Badge variant="success">Simple Movie Data</Badge></h4>
    </>
  );
};

export default SimpleMovie;
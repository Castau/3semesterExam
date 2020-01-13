import React from 'react';
import { Badge, Card, ListGroup, CardGroup, Container, Row } from 'react-bootstrap';

const Welcome = () => {
  return (
    <div>
      {/* <Link to="/search">Searching tool</Link> */}

      <Container className="mt-5 mb-5">
        <Row className="justify-content-md-center">
          <CardGroup className="w-50">
            <Card className="mr-2">
              <Card.Body>
                <h1 className="mt-3 mb-1"> <Badge className="w-100" variant="info">3. semester exam project</Badge></h1>
                <h5><Badge className="w-100" variant="info">Made by Camilla Jenny Valerius Staunstrup | cs340</Badge></h5>
                <ListGroup className="about_link_github" variant="flush">
                  <ListGroup.Item>
                    <a href="mailto: cph-cs340@cphbusiness.dk" target="blank">cph-cs340@cphbusiness.dk</a>
                  </ListGroup.Item>
                  <ListGroup.Item>
                    <a href="https://github.com/Castau/3semesterExam" target="blank">GitHub Repository</a>
                  </ListGroup.Item>
                  <ListGroup.Item>
                    <a href="https://camillastaunstrup.dk/sem3exam/" target="blank">Backend</a>
                  </ListGroup.Item>
                  <ListGroup.Item>
                    <a href="https://travis-ci.org/Castau/3semesterExam?utm_medium=notification&utm_source=github_status" target="blank">Travis Build Log</a>
                  </ListGroup.Item>
                  <ListGroup.Item>
                    <a href=" " target="blank">Link to exam</a>
                  </ListGroup.Item>
                </ListGroup>
              </Card.Body>
            </Card>
          </CardGroup>
        </Row>
        <Row className="justify-content-md-center">
          <CardGroup className="w-50">
            <Card className="mr-2">
              <Card.Body>
                <h5> <Badge className="w-100" variant="info">Hvad nåede jeg</Badge></h5>
                <Card.Text>
                  Jeg nåede det her
                </Card.Text>
              </Card.Body>
            </Card>
          </CardGroup>
        </Row>
      </Container>
    </div>
  );
};

export default Welcome;

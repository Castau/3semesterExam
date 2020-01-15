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
                    <a href="https://sem3exam-camilla.surge.sh/#/" target="blank">Frontend (This)</a>
                  </ListGroup.Item>
                  <ListGroup.Item>
                    <a href="mailto: cph-cs340@cphbusiness.dk" target="blank">cph-cs340@cphbusiness.dk</a>
                  </ListGroup.Item>
                  <ListGroup.Item>
                    <a href="https://github.com/Castau/3semesterExam" target="blank">GitHub Repository</a>
                  </ListGroup.Item>
                  <ListGroup.Item>
                    <a href="https://travis-ci.org/Castau/3semesterExam?utm_medium=notification&utm_source=github_status" target="blank">Travis Build Log</a>
                  </ListGroup.Item>
                  <ListGroup.Item>
                    <a href="https://docs.google.com/document/d/16N3h0TVzfwPJEr8SwP2l_DAvGsODrjtc13TtTBnjGK8/edit" target="blank">Link to exam</a>
                  </ListGroup.Item>
                  <ListGroup.Item>
                    <a href="https://camillastaunstrup.dk/sem3exam/" target="blank">Backend</a>
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
                  <h6>Jeg har nået at lave:</h6>
                  <p>Et endpont der kan tilgåes uden at være logget ind, hvor der vises simpel filmdata og plakat.</p>
                  <p>Et endpoint der kan tilgåes af brugere der er logget ind (users og admin), hvor der vises al info om den fremsøgte film.</p>
                  <p>Søgninger fra movieInfoAll gemmes i databasen på den simpleste måde jeg kunne komme på, på grund af tidsmangel.</p>
                  <p>Admin kan tilgå et endpoint der viser hvor mange gange der er søgt på en given film.</p>
                  <p>Dataen der bliver returneret til brugeren i movieInfoAll er den gemte data, hvis filmen har været søgt frem før.</p>
                  <p>Et endpoint der lægger testbrugere i databasen.</p>
                  <p>Frontend med login og med adgang til de nævnte endpoints, begrænset af de relevante brugerroller.</p>

                  <h6>Jeg nåede _ikke_ at lave:</h6>
                  <p>Refresh af den gemte film-cache vha. timestamp</p>
                  <p>Lokale queries på film baseret på årstal eller skuespiller. Dette skyldes igen tidsmangel.</p>
                  <p>Styling af frontenden er ikke den bedste, jeg bruger stort set udelukkende bootstrap i dette projekt.</p>
                  <p>Jeg har ikke så mange tests (rest og facade)- dels på grund af tidsmangel og affødt af at jeg er i tvivl om hvad jeg mere kan teste (jeg kunne nok godt have lavet nogle negative tests).</p>
                </Card.Text>
              </Card.Body>
            </Card>
          </CardGroup>
        </Row>
      </Container>
    </div >
  );
};

export default Welcome;

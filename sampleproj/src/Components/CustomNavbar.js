import React from "react";
import { Navbar, Nav, Button, Form, FormControl, Container } from "react-bootstrap";
import { FaShoppingCart, FaSearch } from "react-icons/fa"; // Import icons
import { Link } from "react-router-dom";

const CustomNavbar = () => {
  return (
    <Navbar bg="light" expand="lg" className="shadow-sm">
      <Container>
        <Navbar.Brand href="/">
          <img
            src="/logo.png" // Replace with your logo path
            alt="Company Logo"
            height="40"
          />
        </Navbar.Brand>

        <Navbar.Toggle aria-controls="basic-navbar-nav" />

        <Navbar.Collapse id="basic-navbar-nav" className="justify-content-center">
          <Nav>
          <Nav.Link as={Link} to="/">Home</Nav.Link>
            <Nav.Link as={Link} to="/add-product">Add Product</Nav.Link>
            <Nav.Link as={Link} to="/categories">Categories</Nav.Link>
          </Nav>
        </Navbar.Collapse>

        <div className="d-flex align-items-center">
          <Form className="d-flex me-2">
            <FormControl type="search" placeholder="Search" className="me-2" />
            <Button variant="outline-primary">
              <FaSearch />
            </Button>
          </Form>
          <Button variant="outline-danger" className="ms-2">
            <FaShoppingCart /> Cart
          </Button>
        </div>
      </Container>
    </Navbar>
  );
};

export default CustomNavbar;

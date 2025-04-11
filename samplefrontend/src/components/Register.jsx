import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Button, Form, Container, Alert } from "react-bootstrap";

const Register = () => {
  const navigate = useNavigate();

  const [username, setUsername] = useState("");
  const [Email, setEmail] = useState("");
  const [Age, setAge] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  // 🔁 If token already in sessionStorage, redirect to Home
  // useEffect(() => {
  //   const token = sessionStorage.getItem("token");
  //   if (token) {
  //     navigate("/home"); // 🔁 Change path if your Home route differs
  //   }
  // }, [navigate]);

   useEffect(() => {
      // const token = sessionStorage.getItem("token");
      // if (token) {
      //   localStorage.setItem("theme", "dark-theme");
      //   navigate("/home");
      // }
      // localStorage.setItem("theme", "light-theme");
    }, [navigate]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {

      // axios.post('/register', data, {
      //   headers: {
      //     'Content-Type': 'application/json',
      //     'Accept': 'application/json'
      //   }
      // })
      // .then(response => {
      //   console.log('Success:', response.data);
      // })
      // .catch(error => {
      //   console.error('Error:', error);
      // });

      const response = await axios.post("http://localhost:8080/register", {
        username: username,
        password: password,
        age: Age,
        email: Email
      },{
        headers: {
          'Content-Type': 'application/json'
        }
      }).then((res) => {
        console.log(res.data);
        if (res.data.success) {
          alert(res.data.message);
        } else {
          alert(res.data.message);
        }
      })
      .catch((error) => {
        console.error("Registration error:", error);
        alert(res.data.message);
      });

      // const token = response.data.token;
      // sessionStorage.setItem("token", token);
      // console.log(response);
      // alert("You are successfuly registered as "+response.token);
      // navigate("/login");

    } catch (err) {
        console.error("Registration error:", error);
        alert("Unable to register...");
    }
  };

  return (
    <Container className="register-container register-page-row" style={{ maxWidth: "400px" }}>
      <h2 className="mb-4">Register</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formBasicUsername" className="mb-3">
          <Form.Label>Username</Form.Label>
          <Form.Control
            type="username"
            placeholder="Enter username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group controlId="formBasicEmail" className="mb-3">
          <Form.Label>Email</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter email address"
            value={Email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group controlId="formBasicPassword" className="mb-3">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group controlId="formBasicAge" className="mb-3">
          <Form.Label>Age</Form.Label>
          <Form.Control
            type="number"
            placeholder="Age"
            value={Age}
            min={0}
            max={150}
            onChange={(e) => setAge(e.target.value)}
            required
          />
        </Form.Group>

        <Button variant="primary" type="submit" className="w-100">
          Register
        </Button>
      </Form>
      <p className="mt-3 text-center">
        Already have an account? <a href="/login">Login</a>
      </p>
    </Container>
  );
};

export default Register;

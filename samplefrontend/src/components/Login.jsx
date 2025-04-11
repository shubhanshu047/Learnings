import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Button, Form, Container, Alert } from "react-bootstrap";

const Login = () => {
  const navigate = useNavigate();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    const token = sessionStorage.getItem("token");
    if (token) {
      // localStorage.setItem("theme", "dark-theme");
      navigate("/home");
    }
    // localStorage.setItem("theme", "light-theme");
  }, [navigate]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/login", {
        username: username,
        password: password,
      },{
        headers:{
          'Content-Type':'application/json'
        }
      }).then((res)=>{
        console.log(res.data);
        if (res.data.success) {
          alert(res.data.message);
          
          const token = res.data.token;
          sessionStorage.setItem("token", token);
          navigate("/home");
        } else {
          alert(res.data.message);
        }
      })
      .catch((error) => {
        console.error("Registration error:", error);
        alert(res.data.message);
      });
    } catch (err) {
        console.error("Registration error:", error);
        alert("Unable to register...");
    }
  };

  return (
    <Container className="login-container login-page-row" style={{ maxWidth: "400px" }}>
      <h2 className="mb-4">Login</h2>
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

        <Button variant="primary" type="submit" className="w-100">
          Login
        </Button>
      </Form>
      <p className="mt-3 text-center">
        New here? <a href="/register">Register</a>
      </p>
    </Container>
  );
};

export default Login;

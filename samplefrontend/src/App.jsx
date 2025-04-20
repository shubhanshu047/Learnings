import "./App.css";
import React, { useState, useEffect } from "react";
import Home from "./components/Home";
import Navbar from "./components/Navbar";
import Cart from "./components/Cart";
import AddProduct from "./components/AddProduct";
import Login from "./components/Login";
import Product from "./components/Product";
import Register from "./components/Register";
import { BrowserRouter, Routes, Route, useLocation, Navigate } from "react-router-dom";
import { AppProvider } from "./Context/Context";
import UpdateProduct from "./components/UpdateProduct";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import 'bootstrap/dist/css/bootstrap.min.css';


function AppWrapper() {
  const location = useLocation();
  const [cart, setCart] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("");

  const handleCategorySelect = (category) => {
    setSelectedCategory(category);
    console.log("Selected category:", category);
  };
  const addToCart = (product) => {
    const existingProduct = cart.find((item) => item.id === product.id);
    if (existingProduct) {
      setCart(
        cart.map((item) =>
          item.id === product.id
            ? { ...item, quantity: item.quantity + 1 }
            : item
        )
      );
    } else {
      setCart([...cart, { ...product, quantity: 1 }]);
    }
  };

  const hideNavbar = location.pathname === "/login";
  const isRegister = location.pathname === "/register";
  const isLoggedIn = sessionStorage.getItem("token");

  return (
    <>
        {!hideNavbar && !isRegister && <Navbar onSelectCategory={handleCategorySelect}/>}
        <Routes>
          <Route path="/login" element={isLoggedIn ? <Navigate to="/"/> : <Login />}/>
          <Route path="/" element={!isLoggedIn ? <Navigate to="/login"/> : <Home addToCart={addToCart} selectedCategory={selectedCategory}/>}/>
          <Route path="/add_product" element={<AddProduct />} />
          <Route path="/register" element={<Register />} />
          <Route path="/product" element={<Product  />} />
          <Route path="product/:id" element={<Product  />} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/product/update/:id" element={<UpdateProduct />} />
        </Routes>
    </> 
  );
}

function App(){
  return (
    <AppProvider>
      <BrowserRouter>
        <AppWrapper/>
      </BrowserRouter>
    </AppProvider>
  )
}

export default App;

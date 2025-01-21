import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter,Route,Routes} from 'react-router-dom';
import './index.css';
import Login from './Login';
import Home from './Home';
import AddProducts from './AddProducts';
import Categories from './Categories';
import 'bootstrap/dist/css/bootstrap.min.css';
import ShowProduct from './Components/ShowProduct';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
        <Route path='/login' element={<Login/>}/>
        <Route path='/' element={<Home/>}/>
        <Route path='/add-product' element={<AddProducts/>}/>
        <Route path='/categories' element={<Categories/>}/>
        <Route path='/ShowProductDetails/:id' element={<ShowProduct/>}/>
    </Routes>
  </BrowserRouter>
);


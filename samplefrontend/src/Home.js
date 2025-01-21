import React, { useState, useEffect } from 'react'
import CustomNavbar from './Components/CustomNavbar'
import Product from './Components/Product'

export default function Home() {
    // const data = [{ "name": "aalu", "price": 10, "rateing": 2, "showDetails": "potassium" }, 
    //     { "name": "kanda", "price": 20, "rateing": 5, "showDetails": "taste" }, 
    //     { "name": "baigan", "price": 15, "rateing": 0, "showDetails": "NOOOOOTHING" }];

    const [data,setdata] = useState([]);

    useEffect(()=>{
        fetch("http://localhost:8080/products").then(response=>response.json()).then(d=>setdata(d)).catch(error=>console.log("There was an error fetching data"));
    },[]);

  return (
    <div>
        <CustomNavbar/>
        <div id="ProductsDiv" style={{ display: "flex", flexWrap: "wrap", justifyContent: "center", gap: "20px", padding: "20px" }}>
            {
                data.map((prod,index)=>{
                    return <Product key={index} product={prod}/>
                })
            }
        </div>
    </div>
  )
}

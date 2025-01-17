import React from 'react'
import CustomNavbar from './Components/CustomNavbar'
import Product from './Components/Product'

export default function Home() {
    const data = [{ "name": "aalu", "price": 10, "rateing": 2, "showDetails": "NOOOOOTHING" }, 
        { "name": "aalu", "price": 10, "rateing": 2, "showDetails": "NOOOOOTHING" }, 
        { "name": "aalu", "price": 10, "rateing": 2, "showDetails": "NOOOOOTHING" }];
  return (
    <div>
        <CustomNavbar/>
        <div id="ProductsDiv">
            {
                data.map((prod,index)=>{
                    return <Product key={index} product={prod}/>
                })
            }
        </div>
    </div>
  )
}

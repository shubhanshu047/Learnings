import React from 'react'
import { Navigate, useNavigate } from 'react-router-dom'

export default function Product({ product }) {

    const navigate = useNavigate();
    const handleClick = () => {
        navigate(`/ShowProductDetails/${product.id}`);
    };



    return (


        <div style={{width: "30%", height:"20rem"}}>
            
            {/* <div style={{ border: "2px solid white", width: "20%",  borderRadius: "5px" }}>
                <p>{product.id}</p>
                <h1>{product.name}</h1>
                <h1>{product.price}</h1>
            </div> */}

            <div style={{ 
            border: "2px solid white",
            display: "flex",
            flexDirection: "column",
            borderRadius: "5px",
            padding: "10px",
            height: "100%",
            backgroundColor: "rgb(40, 44, 52)",
            color: "white",
            justifyContent: "center"
            }} onClick={handleClick}>
                <h1>{product.name}</h1>
                <p>{product.description}</p>
                <h2>Price: ₹{product.price}</h2>
                <h4>Released on: {product.releaseDate}</h4>
                <p>Available: {product.available ? "Yes" : "No"}</p>
            </div>

        </div>
    )
}

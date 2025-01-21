import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'

export default function ShowProduct() {
    const { id } = useParams();
    const [prod, setprod] = useState(null);
    const [loading, setLoading] = useState(true);
    useEffect(() => {
        fetch(`http://localhost:8080/product/${id}`)
            .then(response => response.json())
            .then(p => {
                setprod(p);          // Update state with the fetched product
                setLoading(false);   // Set loading to false once data is fetched
            })
            .catch(error => {
                console.error("Error fetching product:", error);
                setLoading(false);   // Stop loading if there was an error
            });
    }, []);  // Dependency array ensures useEffect runs only when 'id' changes

    if (loading) {
        return <div>Loading...</div>;
    }

    if (!prod) {
        return <div>Product not found</div>;
    }

    return (
        <div style={{width: "60%", height:"20rem"}}>

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
            }}>
                <h1>{prod.name}</h1>
                <p>{prod.description}</p>
                <h2>Price: ₹{prod.price}</h2>
                <h4>Released on: {prod.releaseDate}</h4>
                <p>Available: {prod.available ? "Yes" : "No"}</p>
            </div>

        </div>
    )
}

// import Cart from "./Cart.tsx";
// import HomeNavbar from "../Navbar&Modals/HomeNavbar.tsx";
// import {Link, useLocation} from "react-router-dom";
// import {useState} from "react";
//
// const AddToCart = ({ item }) => {
//     const location = useLocation(); // Use useLocation to get the current location
//     const currentLocation = location.pathname;
//
//     const [quantity , setQuantity] = useState(1);
//
//     const setIncrease = () =>{
//         quantity == 1 ? setQuantity(quantity + 1):setQuantity(1)
//     };
//
//     const setDecrease = () =>{
//         quantity > 1 ? setQuantity(quantity - 1):setQuantity(1)
//     };
//     return(
//         <>
//            <CartQuantityToogle
//                quantity={quantity}
//            setDecrease={setDecrease}
//            setIncrease={setIncrease}/>
//
//             <HomeNavbar activePage={currentLocation} />
//
//             <Link to={"/cart"}><button className={"btn"} onClick={()=>
//             addToCart(id , price , quanity)
//             }>Add to Cart</button></Link>
//
//
//         </>
//     )
// }
// export default AddToCart
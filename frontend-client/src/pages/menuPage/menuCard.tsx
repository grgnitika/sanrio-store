import {useMutation} from "@tanstack/react-query";
import axios from "axios";
import {useEffect, useState} from "react";

const MenuCard = ({ menuData }) => {

    const [user, setUser] = useState({

    })
    useEffect(() => {
        const data: any = JSON.parse(localStorage.getItem("userDetails"));
        setUser(data);
    }, [localStorage.getItem("userDetails")]);
    console.log(user?.id)


    const useApiCall = useMutation({
        mutationKey: ["POST_CART_DATA"],
        mutationFn: (payload) => {
            // Update the endpoint and headers according to your backend requirements
            return axios.post("http://localhost:8080/cart/save", payload);
        },onSuccess:()=>{
            alert("Item added to cart successfully")
        }
    });

    const onSubmit = (itemId, total_price) => {
        // Create the payload with itemId, userId, itemPrice, and quantity
        const payload = {
            itemId,
            userId: user.id,
            total_price,
            quantity: 1, // Initial quantity
        };
        console.log(payload)
        // Call the mutation function with the payload

        useApiCall.mutate(payload);
    };

    const showLoginPopup = () => {
        alert("Please log-in to add items to your cart."); // You can replace this with a modal or other UI component
    };


    return (
        <>
            <section className="menu-card--cointainer">
                {menuData && menuData.length > 0 ? (
                    menuData.map((curElem: any) => (
                        <div className="menu-card-container" key={curElem?.id}>
                            <div className="menu-card">
                                <div className="menu-card-body">
                                    <span className="menu-card-author subtle">{curElem?.category?.name}</span>
                                    <img src={'data:image/jpeg;base64,'+curElem?.itemImage} alt={curElem?.itemName} className="menu-card-media" />
                                    <h2 className="menu-card-title">{curElem?.itemName}</h2>
                                    <div className={"price-addtocart-div"}>
                                        <h4 className="menu-card-price subtle">Rs. {curElem?.itemPrice}</h4>
                                        <span>
                                            <button className="add-to-card-btn subtle"
                                                    onClick={() => user ? onSubmit(curElem.id, curElem.itemPrice) : showLoginPopup()}>Add to Cart
                                            </button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))
                ) : (
                    <div>No menu items available</div>
                )}
            </section>
        </>
    );
};

export default MenuCard;
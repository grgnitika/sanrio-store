import {useEffect, useState} from "react";
import {useLocation, useNavigate, useParams} from "react-router-dom";
import KhaltiCheckout from "khalti-checkout-web";
import HomeNavbar from "../Navbar&Modals/HomeNavbar.tsx";
import "../../css/payment.css";
import {useQuery} from "@tanstack/react-query";

import axios from 'axios';
import '../../css/payment.css';

const myKey = {
    publicTestKey: 'test_public_key_402c2b0e98364222bb1c1ab02369cefd',
    secretKey: 'test_secret_key_d46fe88dee964ecfbd0f699a9985f2d4',
};

console.log('Fetched ID::::',localStorage.getItem("userDetails"));


const config = {
    publicKey: myKey.publicTestKey,
    productIdentity: '123766',
    productName: 'Feast Food',
    productUrl: 'http://localhost:4004',
    eventHandler: {
        onSuccess(payload) {
            // Handle successful payment
            console.log(payload);

            const data = {
                token: payload.token,
                amount: payload.amount,
            };

            // Display success alert
            alert("Payment done successfully!");

            // Make an API call using Axios to update payment status
            axios.get(`https://meslaforum.herokuapp.com/khalti/${data.token}/${data.amount}/${myKey.secretKey}`)
                .then((response) => {
                    // Get user details from localStorage
                    const userDetails = localStorage.getItem("userDetails");
                    const userObject = userDetails ? JSON.parse(userDetails) : null;

                    // Check if user details are valid
                    if (userObject && userObject.id) {
                        const userId = userObject.id;

                        // Get payment ID from localStorage
                        const payId = localStorage.getItem("pay");

                        // API hit to update payment status
                        if (response.status === 200) {
                            axios.put(`http://localhost:8080/payment/update/${payId}`, { userId })
                                .then((updateResponse) => {
                                    console.log(updateResponse.data);
                                })
                                .catch((error) => {
                                    console.error("Error updating payment:", error);
                                });
                        } else {
                            console.error("Received non-200 status code:", response.status);
                        }
                    } else {
                        console.log('User details not found or invalid format');
                    }
                })
                .catch((error) => {
                    console.log(error);
                    console.log('Error triggering this block');
                });
        },
        onError(error) {
            // Handle payment error
            console.log(error);
        },
        onClose() {
            // Handle widget closure
            console.log('Widget is closing');
        },
    },
    paymentPreference: ['KHALTI', 'EBANKING', 'MOBILE_BANKING', 'CONNECT_IPS', 'SCT'],
};


const Payment = () => {
    const {cartTotal}=useParams();
    const location = useLocation();
    const currentLocation = location.pathname;
    const navigate = useNavigate();

    const [currentPaymentId, setCurrentPaymentId] = useState();

    const checkout = new KhaltiCheckout(config);
    const buttonStyles = {
        backgroundColor: "orange",
        padding: "10px",
        color: "white",
        cursor: "pointer",
        fontWeight: "semi-bold",
        border: "0px solid white",
    };

    // payment dropdown logic
    const [selectedPaymentOption, setSelectedPaymentOption] = useState("");


    // Fetching data from API
    const{data:cartData} = useQuery({
        queryKey:["GET_CART_DATA"],
        queryFn(){
            return axios.get("http://localhost:8080/cart/getAll")
        }
    })

    // const cartTotal = cartData?.data.reduce(
    //     (total, item) => total + item?.total_price * item?.quantity,
    //     0
    // );

    // // Fetching user details // //
    const [user, setUser] = useState({

    })
    useEffect(() => {
        const data: any = JSON.parse(localStorage.getItem("userDetails"));
        setUser(data)

    }, [localStorage.getItem("userDetails")]);

    const handleConfirmOrder = async () => {
        if (selectedPaymentOption === "Cash on delivery") {
            const itemIds = cartData?.data
                .map(item => item?.item?.id)
                .filter(id => id !== null && id !== undefined);

            //oder connection//

            const orderData = {
                userId: user?.id,
                orderItems: itemIds,
                payVia: selectedPaymentOption,
                pickUpOption: selectedDeliveryOption,
                totalPrice: totalAmount,
                address: selectedDeliveryOption === "Home Delivery" ? document.querySelector(".address_input").value : null,
                phoneNumber: selectedDeliveryOption === "Home Delivery" ? document.querySelector(".phone_input").value : null,
            };

            try {
                const response = await axios.post("http://localhost:8080/order/save", orderData);
                console.log(response.data);
                localStorage.setItem("pay", currentPaymentId);
                const storedPaymentId = parseInt(localStorage.getItem("pay"), 10);
                console.log('Payment Test ID::::', storedPaymentId);
                alert("Order placed successfully!");


                navigate('/OurMenu');
            } catch (error) {
                console.error("Error placing the order", error);
                alert("Error placing the order. Please try again.");
            }

        }


        // both payment and order
        if (selectedPaymentOption === "Pay Via Khalti") {
            const itemIds = cartData?.data
                .map(item => item?.item?.id)
                .filter(id => id !== null && id !== undefined);

            const orderData = {
                userId: user?.id,
                orderItems: itemIds,
                payVia: selectedPaymentOption,
                pickUpOption: selectedDeliveryOption,
                totalPrice: totalAmount,
                address: selectedDeliveryOption === "Home Delivery" ? document.querySelector(".address_input").value : null,
                phoneNumber: selectedDeliveryOption === "Home Delivery" ? document.querySelector(".phone_input").value : null,
            };

            const paymentData = {
                userId: user?.id,
                orderItems: itemIds,
                subTotal: cartTotal,
                deliveryFee: selectedDeliveryOption === "Home Delivery" ? 75 : 0, // Add delivery fee only for Home Delivery
                total: totalAmount,
            };

            try {
                // Make API call for order data
                const response = await axios.post("http://localhost:8080/order/save", orderData);
                console.log(response.data);

                // Make API call for payment data
                const paymentResponse = await axios.post("http://localhost:8080/payment/save", paymentData);
                console.log(typeof paymentResponse.data,paymentResponse.data);  // latest Payment Id
                setCurrentPaymentId(paymentResponse.data);



                alert("Order placed successfully!");
                checkout.show({ amount: totalAmount*100 });
                // navigate('/OurMenu');

            } catch (error) {
                console.error("Error placing the order or payment", error);
                alert("Error placing the order or payment. Please try again.");
            }
        }
    };

    console.log("currentPaymentId",currentPaymentId);

    // State for the selected delivery option
    const [selectedDeliveryOption, setSelectedDeliveryOption] = useState<string>("");

    // State for the total amount
    const [totalAmount, setTotalAmount] = useState<number>(0);

    // Calculate total amount whenever cartTotal or selectedDeliveryOption changes
    useEffect(() => {
        let newTotalAmount = +cartTotal || 0;

        if (selectedDeliveryOption === "Home Delivery") {
            newTotalAmount += 150; // Add delivery fee
        }

        setTotalAmount(newTotalAmount);

    }, [cartTotal, selectedDeliveryOption]);


    return (
        <>
            <div className={"payment-container"}>
                <HomeNavbar activePage={currentLocation} /><br></br>
                <div className={"payment-text-div"}><h1> Payment<b> Page</b></h1></div><br></br>
                <div className={"delivery-mode-container"}>
                    <div className={"delivery-method"}>
                        <div className={"delivery-text"}>
                            <h2>Delivery Section</h2>
                        </div>
                        <div className={"dropdown-delivery"}>
                            <select
                                className={"select-delivery-option"}
                                value={selectedDeliveryOption}
                                onChange={(e) => setSelectedDeliveryOption(e.target.value)}
                            >
                                <option>Select Delivery Mode</option>
                                <option>Self Pick Up</option>
                                <option>Home Delivery</option>
                            </select>

                            {selectedDeliveryOption === "Home Delivery" && (
                                <>
                                    <input type="text" className="address_input" placeholder="Enter your Address"/>

                                    <input type="text" className="phone_input" placeholder="Enter your phone number"/>
                                </>

                            )}

                        </div>
                    </div>

                    <div className={"receipt-container"}>
                        <div className={"receipt-container-text"}>
                            <h2>Receipt</h2>
                        </div>
                        <div className={"receipt-text"}>

                            <div className={"sub-total-box"} placeholder={"SUB-TOTAL"}>
                                Cart Total: Rs. {cartTotal}

                            </div>
                            {selectedDeliveryOption === "Home Delivery" && (
                                <div className={'delivery-fee-box'} placeholder={"DELIVERY-FEE"}>
                                    Delivery-Fee: Rs{75}
                                </div>
                            )}
                            <h5>----------------------------------------------</h5>
                            <div className={"Total-box5"} placeholder={"TOTAL"}>
                                Total: Rs. {totalAmount}
                            </div>
                        </div>
                    </div>

                    <div>
                        <div className={"payment-mode-container"}>
                            <div className={"payment-text"}>
                                <h2>Payment Method</h2>
                            </div>
                            <div className={"dropdown-payment"}>
                                <select
                                    className={"select-payment-option"}
                                    onChange={(e) => setSelectedPaymentOption(e.target.value)}
                                >
                                    <option>Select Payment Option</option>
                                    <option>Cash on delivery</option>
                                    <option>Pay Via Khalti</option>
                                </select>
                            </div>
                        </div>
                        <hr />
                        <div className={"confirm-order"}>
                            <button onClick={handleConfirmOrder} style={buttonStyles}>
                                Confirm Order
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default Payment;
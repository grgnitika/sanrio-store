import {useLocation} from "react-router-dom";
import {useMutation, useQuery} from "@tanstack/react-query";
import axios from "axios";
import AdminSidebar from "./adminSidebar.tsx";
import "../../src/css/orderPage.css"
import {MdDelete} from "react-icons/md";

function OrderPage(){

    const location = useLocation();
    const currentLocation = location.pathname;

    // const[search, setSearch] = useState('');

    // Fetching Booked event data from API
    const{data:orderData,refetch} = useQuery({
        queryKey:["GET_ORDER_MADE_DATA"],
        queryFn(){
            return axios.get("http://localhost:8080/order/getAll")
        }
    })

    //Deleting booked event data
    const deleteByIdApi=useMutation(
        {
            mutationKey:["DELETE_ORDER_BY_ID"],
            mutationFn(id:number){
                return axios.delete("http://localhost:8080/order/delete/"+id);
            },onSuccess(){refetch()}
        }
    )


    return(
        <>
            <div className={"customer-page"}>
                <AdminSidebar activePage={currentLocation}/>

                <div className={"customer-page-right"}>
                    <header className={"customer-page-header"}>
                        <h1>Orders</h1>
                    </header>
                    <div className={"customer-page-main"}>
                        <div className={"no-of-customer"}>
                            <h2>Orders Made</h2>
                        </div>
                        <table className={"event-table2"}>
                            <thead>
                            <tr>
                                <th className={"name-box6"}>Customer's Name</th>
                                <th className={"nid-box6"}>Item ID</th>
                                <th className={"date-box6"}>Order date&time</th>
                                <th className={"price-box6"}>Total Price</th>
                                <th className={"delivery-box6"}>Delivery Option</th>
                                <th className={"action-box6"}>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            {orderData?.data.map((i) => (
                                <tr key={i?.id}>
                                    <td>{i?.user?.fullName}</td>
                                    <td>{i?.orderItems}</td>
                                    <td>{new Date(i?.orderDateTime).toLocaleString()}</td>
                                    <td>Rs. {i?.totalPrice}</td>
                                    <td className={"tddelivery-box6"}>
                                        {i?.address ? (
                                            <>
                                                {i?.address} ,
                                                {i?.phoneNumber}
                                            </>
                                        ) : (
                                            "Self Pickup"
                                        )}
                                    </td>
                                    <td>
                                        <button className={"delete-btn3"} onClick={() => {
                                            if (window.confirm("Are you sure you want to delete this Order?")) {
                                                deleteByIdApi.mutate(i?.id);
                                            }
                                        }}><MdDelete />
                                        </button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    )
}
export default OrderPage;

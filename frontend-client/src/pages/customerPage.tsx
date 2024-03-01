import "../css/customerPage.css"
import AdminSidebar from "./adminSidebar.tsx";
import {useLocation} from "react-router-dom";
import {FaSearch} from "react-icons/fa";
import {MdDelete} from "react-icons/md";
import {useMutation, useQuery} from "@tanstack/react-query";
import axios from "axios";
import {useState} from "react";


const CustomerPage = () =>{

    const location = useLocation();
    const currentLocation = location.pathname;

    const[search, setSearch] = useState('');

    // Fetching data from API
    const{data,refetch} = useQuery({
        queryKey:["GETCUSTOMERDATA"],
        queryFn(){
            return axios.get("http://localhost:8080/register/getAll")
        }
    })

    //Searching data
    // Filtered data based on the search input (name, email, or ID)
    const filteredData = data?.data.filter((customer) =>
        customer.id.toString().includes(search.toLowerCase()) || // Include ID
        customer.fullName.toLowerCase().includes(search.toLowerCase()) ||
        customer.email.toLowerCase().includes(search.toLowerCase())
    );

    // Dynamically calculate the number of customers
    const userLength = filteredData ? filteredData.length : 0;

    //Deleting data
    const deleteUserByIdApi=useMutation(
        {
            mutationKey:["DELETE_CUSTOMER_BY_ID"],
            mutationFn(id:number){
                return axios.delete("http://localhost:8080/register/deleteUserById/"+id);
            },onSuccess(){refetch()}
        }
    )

    return(
        <>
            <div className={"customer-page"}>
                <AdminSidebar activePage={currentLocation}/>

                <div className={"customer-page-right"}>
                    <header className={"customer-page-header"}>
                        <h1>Manage Customers</h1>

                        <div className={"search-wrapper2"} style={{width:"16rem"}}>
                            <span><FaSearch /></span>
                            <input type={"search"} placeholder={"Search Customers"} value={search} onChange={(e)=>setSearch(e.target.value)}/>
                        </div>
                    </header>
                    <div className={"customer-page-main"}>
                        <div className={"no-of-customer"}>
                            <h2>No. of Customer: {userLength}</h2>
                        </div>
                        <table className={"customer-table"}>
                            <thead>
                            <tr>
                                <th className={"id-box3"}>ID</th>
                                <th className={"name-box3"}>Name</th>
                                <th className={"name-box2"}>Email</th>
                                <th className={"delete-box2"}>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            {filteredData?.map((customer) => (
                                <tr key={customer?.id}>
                                    <td>{customer?.id}</td>
                                    <td>{customer?.fullName}</td>
                                    <td>{customer?.email}</td>
                                    <td>
                                        <button
                                            className={"delete-btn2"}
                                            onClick={() => {
                                                if (window.confirm(
                                                        "Are you sure you want to delete this customer?"))
                                                {deleteUserByIdApi.mutate(customer?.id);}
                                            }}>
                                            <MdDelete />
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

export default CustomerPage
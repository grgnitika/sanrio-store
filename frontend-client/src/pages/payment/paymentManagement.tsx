import React from "react";
import { useLocation } from "react-router-dom";
import { useQuery, useMutation } from "@tanstack/react-query";
import axios from "axios";
import { useForm } from "react-hook-form";
import { CiEdit } from "react-icons/ci";
import { MdDelete } from "react-icons/md";
import gsap from "gsap";
import AdminSidebar from "../adminSidebar.tsx";

import "../../css/paymentManagement.css";

const PaymentManagement = () => {
    const location = useLocation();
    const currentLocation = location.pathname;

    // Fetching data from API
    const { data: paymentData, refetch } = useQuery({
        queryKey: ["GETDATA"],
        queryFn() {
            return axios.get("http://localhost:8080/payment/findAll");
        },
    });

    // Deleting data
    const deleteByIdApi = useMutation({
        mutationKey: ["DELETE_BY_ID"],
        mutationFn(id: number) {
            return axios.delete("http://localhost:8080/payment/delete/" + id);
        },
        onSuccess() {
            refetch();
        },
    });

    return (
        <div className={"paymentManagement-page"}>
            <div className={"paymentManagement-left"}>
                <AdminSidebar activePage={currentLocation} />
            </div>

            <div className={"manageTable-right"}>
                <header className={"paymentManagement-header"}>
                    <h1>Payment Management</h1>
                    <div className={"user-wrapper2"}>
                        <img
                            src={
                                "data:image/jpeg;base64,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"
                            }
                            width={"40px"}
                            height={"60px"}
                            alt={"N"}
                        />
                        <div>
                            <h4>Admin</h4>
                            <small>Super admin</small>
                        </div>
                    </div>
                </header>
                <div className={"paymentManagement-main-content"}>
                    <div className={"paymentManagementMain-content"}>
                        <div className={"paymentManagement-container2"}>
                            <div className={"card-body2"}>
                                <table className={"paymentManagement-bordered2"}>
                                    <thead>
                                    <tr>
                                        <th className={"id-box2"}>id</th>
                                        <th className={"paymentDate-box2"}>Payment Date</th>
                                        <th className={"userId-box2"}>UID</th>
                                        <th className={"orderItems-box2"}>OrderItems</th>
                                        <th className={"subTotal-box2"}>SubTotal</th>
                                        <th className={"deliveryFee-box2"}>DeliveryFee</th>
                                        <th className={"total-box2"}>Total</th>
                                        <th className={"Status-box2"}>Status</th>
                                        <th className={"delete-box2"}>Delete</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {paymentData?.data.map((i) => {
                                        return (
                                            <tr key={i?.id}>
                                                <td>{i?.id}</td>
                                                <td>{i?.paymentDate}</td>
                                                <td>{i?.user.id}</td>
                                                <td>{i?.orderItems}</td>
                                                <td>{i?.subTotal}</td>
                                                <td>{i?.deliveryFee}</td>
                                                <td>{i?.total}</td>
                                                <td>{i?.status}</td>
                                                <td>
                                                    <button
                                                        className={"delete-btn2"}
                                                        onClick={() => {
                                                            // Display confirmation prompt before deletion
                                                            if (
                                                                window.confirm(
                                                                    "Are you sure you want to delete this payment?"
                                                                )
                                                            ) {
                                                                deleteByIdApi.mutate(i?.id);
                                                            }
                                                        }}
                                                    >
                                                        <MdDelete />
                                                    </button>
                                                </td>
                                            </tr>
                                        );
                                    })}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default PaymentManagement;

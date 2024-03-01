
import AdminSidebar from "./adminSidebar.tsx";
import {useLocation, useNavigate, useParams} from "react-router-dom";
import {useForm} from "react-hook-form";
import axios from "axios";
import {useQuery} from "@tanstack/react-query";
import {useMutation} from "@tanstack/react-query";


const EditItem = ()=>{

    const navigate = useNavigate();

    const useApiCall = useMutation({
        mutationKey:["POST_ITEM_MANAGEITEM"],
        mutationFn:(payload:any)=>{
            // console.log(payload)
            return axios.post("http://localhost:8080/item/save",payload)
        },onSuccess: () => {
            // notify();
            reset();
            navigate("/ManageItem");
        }
    })

    const onSubmit=(value:any)=>{
        console.log(value);
        const fd= new FormData();
        fd.append("productName",value?.itemName)
        fd.append("price",value?.itemPrice)
        fd.append("categoryId",value?.categoryId)
        // fd.append("productImage",value?.productImage[0])
        useApiCall.mutate(fd)
    }

    //To update
    const{pk_id} = useParams();

    const{data:getItemByIdApi}=useQuery({
        queryKey:["GET_BY_ID_CATEGORY_API"],
        queryFn(){
            return axios.get("http://localhost:8080/item/findById/"+pk_id)
        },enabled:!!pk_id
    })

    //hitting server on port 8080
    const{register,
        handleSubmit,
        formState
        ,reset}=useForm({values:getItemByIdApi?.data});

    const{errors} = formState;

    const location = useLocation(); // Use useLocation to get the current location
    const currentLocation = location.pathname;

    return(
        <>
            <AdminSidebar activePage={currentLocation} />
            <div className="add-item-modal">
                <div className="add-item-modal-content">
                    <h2>Edit Item</h2>

                    <form onSubmit={handleSubmit(onSubmit)}>
                        <div className={"select-category"}>
                            {/*<label>Category : {filteredItemData?.category?.name}</label>*/}
                        </div>
                        <div className={"item-name"}>
                            <label>Item Name</label>
                            <input type={"text"} placeholder={"Enter item Name"} {...register("itemName",{required:"Item Name is required!!"})}/>
                            <h6 style={{paddingLeft:"3px"}}>{errors?.itemName?.message}</h6>
                        </div>
                        <div className={"item-price"}>
                            <label>Price</label>
                            <input type={"number"} placeholder={"Enter the Price"} {...register("itemPrice",{required:"Price is required!!"})}/>
                            <h6 style={{paddingLeft:"3px"}}>{errors?.itemPrice?.message}</h6>
                        </div>
                        {/*<div className={"item-image"}>*/}
                        {/*    <label>Image</label>*/}
                        {/*    <span>*/}
                        {/*            <input type={"file"} placeholder={"Add image here"} {...register("productImage",{required:"Item Image is required!!"})}/>*/}
                        {/*             <h6 style={{paddingLeft:"3px"}}>{errors?.productImage?.message}</h6>*/}
                        {/*        </span>*/}
                        {/*</div>*/}

                        <div className={"item-name-add-btn"}>
                            <button type={"submit"}>Edit</button>
                        </div>
                    </form>
                </div>
            </div>
        </>
    )
}

export default EditItem;
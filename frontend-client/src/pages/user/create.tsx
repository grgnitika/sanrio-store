import {useForm} from "react-hook-form";
import {useMutation} from "@tanstack/react-query";
import axios from "axios";

function UserCreate(){

    const {register,
        handleSubmit,
        formState}=useForm();

    const {errors}=formState;

    const useApiCall=useMutation({
        mutationKey:["POST_USER_CREATE"],
        mutationFn:(payload:any)=>{
            console.log(payload)
            return axios.post("http://localhost:8080/users/save",payload)
        }
    })


    const onSubmit=(value:any)=>{
        useApiCall.mutate(value)
    }


    return (<>
        <form onSubmit={handleSubmit(onSubmit)}>
            <div>
                <label>fullname</label>
                <input type={"text"} {...register("fullName",{
                    required:"full name is required"
                })}/>
                <p>{errors?.fullName?.message}</p>
            </div>
            <div>
                <label>username</label>
                <input type={"text"} {...register("username")}/>
            </div>
            <div>
                <label>password</label>
                <input type={"password"} {...register("password")}/>
            </div>

            <div>
                <label>email</label>
                <input type={"email"} {...register("email")}/>
            </div>

            <div>
                <label>mobileNo</label>
                <input type={"text"} {...register("mobileNo")}/>
            </div>


            <div>
                <label>role</label>
                <input type={"text"} {...register("role")}/>
            </div>
            <div>
                <input type={"submit"}/>
            </div>
        </form>
    </>)
}


export default UserCreate;

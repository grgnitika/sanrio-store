import {CiEdit} from "react-icons/ci";
import {MdDelete} from "react-icons/md";
import {useQuery, useMutation} from "@tanstack/react-query";
import axios from "axios";
import {useNavigate} from "react-router-dom";

interface CategoryDataProps {
    search: string;
}

const CategoryData: React.FC<CategoryDataProps> = ({ search}) => {

    const navigate = useNavigate();

    // Fetching data from API
    const{data,refetch} = useQuery({
        queryKey:["GETDATA"],
        queryFn(){
            return axios.get("http://localhost:8088/category/findAll")
        }
    })

    //Searching data

    const filteredData = data?.data.filter((category) =>
        category.name.toLowerCase().includes(search.toLowerCase())
    );

    //Deleting data
    const deleteByIdApi=useMutation(
        {
            mutationKey:["DELETE_BY_ID"],
            mutationFn(id:number){
                return axios.delete("http://localhost:8088/category/delete/"+id);
            },onSuccess(){refetch()}
        }
    )

    return (
        <>
            {
                filteredData?.map((i) =>{
                    return(
                        <tr key={i?.id}>
                            <td>{i?.id}</td>
                            <td>{i?.name}</td>
                            <td><button className={"edit-btn2"} onClick={()=>{
                                navigate("/edit/"+i?.id);
                                console.log(i?.id)
                            }}><CiEdit /></button></td>
                            <td><button className={"delete-btn2"} onClick={() => {
                                // Display confirmation prompt before deletion
                                if (window.confirm("Are you sure you want to delete this category?")) {
                                    deleteByIdApi.mutate(i?.id);
                                }
                            }}><MdDelete /></button></td>
                        </tr>
                    )
                })
            }
        </>
    )
}

export default CategoryData;

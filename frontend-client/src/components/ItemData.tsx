import React from "react";
import {CiEdit} from "react-icons/ci";
import {MdDelete} from "react-icons/md";


interface User {
    id: number;
    name: string;
    email: string;
}

interface UserDataProps {
    users: User[];
}

const ItemData: React.FC<UserDataProps> = ({ users }) => {
    return (
        <>
            {users.map((curUser) => {
                const { id, name, email } = curUser;

                return (
                    <tr key={id}>
                        <td>{id}</td>
                        <td>{name}</td>
                        <td>{email}</td>
                        <td>{id}</td>
                        <td>{id}</td>
                        <td><button className={"edit-btn3"}><CiEdit /></button><button className={"delete-btn3"}><MdDelete /></button></td>
                        <td>{id}</td>
                    </tr>
                );
            })}
        </>
    );
};

export default ItemData;

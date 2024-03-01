

function HomeDelivery() {
    return (
        <>
            <div className={"homedelivery"}>
                <select className={"select-delivery"}>
                    <option>Home Delivery</option>
                        <label>Name
                            <input type={"text"}/>
                        </label>
                        <br/>
                        <label>Phoneno.
                            <input type={"text"}/>
                        </label>
                        <br/>
                        <label>Address
                            <input type={"text"}/>
                        </label>
                    <br/>
                </select>
            </div>
        </>

    );
}

export default HomeDelivery
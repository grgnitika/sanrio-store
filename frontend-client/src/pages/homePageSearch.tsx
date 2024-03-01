
const HomePageSearch = ( {filteredItemData} ) => {

    return(
        <>
            <section className={"menu-card--cointainer"}>
                {filteredItemData?.map((curElem)=>(
                    <div className="menu-card-container" key={curElem?.id}>
                        <div className="menu-card">
                            <div className="menu-card-body">
                                <span className="menu-card-author subtle"> {curElem?.category?.name}</span>
                                <img src={'data:image/jpeg;base64,'+curElem?.itemImage} alt="images" className="menu-card-media" />
                                <h2 className="menu-card-title"> {curElem?.itemName} </h2>
                                <div className={"price-addtocart-div"}>
                                    <h4 className="menu-card-price subtle">{curElem?.itemPrice}</h4>
                                    <span><button className="add-to-card-btn subtle">
                                        Add to Cart
                                    </button></span>
                                </div>
                            </div>
                        </div>
                    </div>
                ))}
            </section>
        </>
    )
}

export default HomePageSearch;
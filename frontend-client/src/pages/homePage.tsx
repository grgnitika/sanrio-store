import "../css/homePage.css";
import HomeNavbar from "./Navbar&Modals/HomeNavbar.tsx";
import {Link, useLocation} from "react-router-dom";
import {FaSearch} from "react-icons/fa";
import ImageSlider from "./imgSliderBar/imageSlider1.tsx";
import {useState} from "react";
import HomePageSearch from "./homePageSearch.tsx";
import {useQuery} from "@tanstack/react-query";
import axios from "axios";

const HomePage = () => {

    const [search,setSearch] = useState(null);

    const location = useLocation(); // Use useLocation to get the current location
    const currentLocation = location.pathname;

    // Fetching data from API
    const { data: menuData } = useQuery({
        queryKey: ["GET_ITEMMENU_DATA"],
        queryFn() {
            return axios.get("http://localhost:8080/item/findAll");
        }
    });

    // Searching data
    const filteredItemData = menuData?.data.filter((item) =>
        item?.itemName?.toLowerCase().includes(search?.toLowerCase())
    );

    return(
        <>
            <div className={"home-page"}>
                <div className={"hp-first-div"}>
                    <HomeNavbar activePage={currentLocation} />
                    <div className={"hp-main-container"}>
                        <div className={"sanrio-slogan"}>
                            <h1>Sanrio Store: Where joy meets imagination!</h1>
                            <br></br>
                        </div>
                        <div className={"hp-search-wrapper"}>
                            <input type={"search"} placeholder={"Search your items here..."} onChange={(e)=> setSearch(e.target.value)}/>
                            <span><FaSearch/></span>
                        </div>
                    </div>
                </div>

                <div className={"hp-second-div"}>
                    {search && <div className={"line2"}></div> &&  <div className={"home-search-div"}>
                        <HomePageSearch filteredItemData={filteredItemData}/>
                    </div>}
                    <div className={"recommendation-div"}>
                        <h2 className={"recommended-text"}>New Arrivals</h2>
                        <ImageSlider/>
                        <div className={"view-more-btn"}>
                            <Link to={"/OurMenu"}><button>Explore More</button></Link>
                        </div>
                    </div>

                    <div className={"line2"}></div>
                    <div className={"copyright-text"}>
                        <br></br>
                        <br></br>
                        <br></br>
                        <br></br>
                        <br></br>
                        <h5 style={{color:"black"}}>Copyright ©2024 Sanrio.All Rights Reserved.</h5>
                    </div>
                </div>
            </div>
        </>
    )
}

export default HomePage
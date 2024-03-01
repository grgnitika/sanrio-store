import "../../css/imageSlider1.css";
import { useQuery } from "@tanstack/react-query";
import axios from "axios"; // Import the image data

const ImageSlider = () => {

    // Fetching data from API
    const { data: imageData1 } = useQuery({
        queryKey: ["GET_ITEM_IMAGE_DATA"],
        queryFn() {
            return axios.get("http://localhost:8080/item/findAll")
        }
    })

    return (
        <div>
            <div className="img-container">
                {imageData1?.data.map((image, index) => {
                    console.log(image);
                    if (index <= 7) {
                        return (
                            <span key={`slider${index + 1}`} className="slider" id={`slider${index + 1}`} />
                        );
                    }
                })}
                <div className="imgContainer">
                    {imageData1?.data.map((image, index) => {
                        if (index < 7) {
                            return (
                                <div key={`slide_${index + 1}`} className={`slide_div`} id={`slide_${index + 1}`}>
                                    <img src={'data:image/jpeg;base64,' + image?.itemImage} alt="" className="img" id={`img${index + 1}`} />
                                    <a href={`#slider${index + 1}`} className="slide-button" id={`button${index + 1}`} />
                                </div>
                            );
                        }
                    })}
                </div>
            </div>
        </div>
    );
}

export default ImageSlider;

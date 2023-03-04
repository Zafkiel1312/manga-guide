import {ImageListItem, ImageListItemBar} from "@mui/material";
import {MangaEntryModel} from "../common/MangaEntryModel";
import {useNavigate} from "react-router-dom";

export function MangaField(props: MangaEntryModel) {

    const navigate = useNavigate()

    function handleOnClick() {
        navigate("/view/" + props.id)
    }

    return (
        <div
            className={"image-list-item"}
            onClick={handleOnClick}
        >
            <ImageListItem
                style={{
                    height: "100%"
                }}
            >
                <img src={props.picture} alt={props.title} />
                <ImageListItemBar
                    title={props.title}
                    subtitle={props.author}
                    style={{
                        position: "absolute",
                        top: "75%",
                        paddingBottom: "5%"
                    }}
                />
            </ImageListItem>
        </div>
    )
}

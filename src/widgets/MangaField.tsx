import { ImageListItem, ImageListItemBar } from "@mui/material";
import { ShowMangaContext } from "../common/context/ShowMangaContext";
import { useContext } from "react";
import { MangaEntryModel } from "../common/MangaEntryModel";

export function MangaField(props: MangaEntryModel) {

    const {setVisibility, setEntry} = useContext(ShowMangaContext)

    function handleOnClick() {
        setEntry(props)
        setVisibility(true)
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

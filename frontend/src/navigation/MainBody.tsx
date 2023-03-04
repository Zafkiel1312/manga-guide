import {Box, useTheme} from "@mui/material";
import MangaGrid from "../widgets/MangaGrid";
import React from "react";
import {AddMangaDialog} from "../widgets/AddMangaDialog";
import {ShowMangaDialog} from "../widgets/ShowMangaDialog";
import {useParams} from "react-router-dom";

interface MainBodyProps {
    viewManga?: boolean,
    addManga?: boolean
}

function MainBody(props: MainBodyProps) {
    const theme = useTheme()
    const style = {
        margin: "55px 0 0 0",
        background: `linear-gradient(
            0deg, 
            ${theme.palette.secondary.main} 80%, 
            ${theme.palette.primary.main} 98%)`,
        width: "100%",
        height: "100%"
    }

    const mangaId = useParams<string>().mangaId

    return (
        <Box
            id={"main-body"}
            style={style}
        >
            <MangaGrid/>
            {props.addManga && <AddMangaDialog/>}
            {props.viewManga && <ShowMangaDialog mangaId={mangaId}/>}
        </Box>
    )
}

export default MainBody
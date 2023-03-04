import { Box, useTheme } from "@mui/material";
import MangaGrid from "../widgets/MangaGrid";
import React, { useState } from "react";
import { AddMangaDialog } from "../widgets/AddMangaDialog";
import { ShowMangaContextObject, ShowMangaContext } from "../common/context/ShowMangaContext";
import { defaultEntry } from "../common/MangaData";
import { ShowMangaDialog } from "../widgets/ShowMangaDialog";

function MainBody() {

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

    const [visibility, setVisibility] = useState(false)
    const [entry, setEntry] = useState(defaultEntry)
    const showMangaContextObject: ShowMangaContextObject = {
        visibility,
        setVisibility,
        entry,
        setEntry
    }

    return (
        <Box
            id={"main-body"}
            style={style}
        >
            <ShowMangaContext.Provider value={showMangaContextObject}>
                <MangaGrid />
                <AddMangaDialog />
                <ShowMangaDialog />
            </ShowMangaContext.Provider>
        </Box>
    )
}

export default MainBody
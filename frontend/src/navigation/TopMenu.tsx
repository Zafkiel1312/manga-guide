import { AppBar, Box, IconButton, Toolbar, useTheme } from "@mui/material";
import { useContext, useState } from "react";
import {
    AddMangaVisibilityContext,
    AddMangaVisibilityContextObject
} from "../common/context/AddMangaVisibilityContextObject";
import ColorLensIcon from '@mui/icons-material/ColorLens';
import Brightness4Icon from '@mui/icons-material/Brightness4';
import { ColorThemeContext } from "../common/context/ColorThemeContext";
import { CustomTheme } from "../CustomTheme";
import { ColorChangeButton } from "../widgets/ColorChangeButton";
import AddCircleIcon from '@mui/icons-material/AddCircle';
import ScrollContainer from "react-indiana-drag-scroll";

function TopMenu() {

    const theme = useTheme()
    const { isDarkTheme, setIsDarkTheme } = useContext(ColorThemeContext)

    const { setValue } = useContext(AddMangaVisibilityContext) as AddMangaVisibilityContextObject

    const [isColorChangeable, setIsColorChangeable] = useState(false)
    const colorOptions: CustomTheme[] = []
    for (let customThemeMember in CustomTheme) {
        colorOptions.push(customThemeMember as CustomTheme)
    }

    return (
        <Box>
            <AppBar elevation={0}>
                <Toolbar style={{ justifyContent: "space-between" }}>
                    <Box style={{
                        display: "flex",
                        flexWrap: "nowrap",
                        justifyContent: "center",
                        alignItems: "center"
                    }}>
                        <Box
                            style={{
                                minWidth: "105px"
                            }}
                        >
                            The "complete" <br />
                            Manga Guide
                        </Box>
                        <IconButton
                            color={"secondary"}
                            onClick={() => {
                                setValue(true)
                            }}
                            style={{
                                margin: "0 2%"
                            }}
                        >
                            <AddCircleIcon fontSize={"large"} />
                        </IconButton>
                    </Box>
                    <Box style={{
                        display: "flex",
                        flexWrap: "nowrap",
                        justifyContent: "center",
                        alignItems: "center"
                    }}>
                        <Box
                            className={isColorChangeable ? "color-is-changeable" : "color-is-not-changeable"}
                            overflow={"hidden"}
                            style={{
                                backgroundColor: theme.palette.secondary.main,
                                opacity: "1",
                                display: "flex",
                                flexWrap: "nowrap",
                                borderRadius: "50px",
                                height: "fit-content",
                                transition: "width 300ms",
                            }}
                        >
                            <ScrollContainer
                                className={"scrollHost"}
                                vertical={false}
                                style={{
                                    width: "100%",
                                    height: "100%",
                                    display: "flex",
                                    flexWrap: "nowrap",
                                    overflowX: "hidden",
                                    WebkitOverflowScrolling: "touch"
                                }}
                            >
                                {colorOptions.map((theme) =>
                                    <ColorChangeButton key={theme} theme={theme} isDarkTheme={isDarkTheme} />
                                )}
                            </ScrollContainer>
                        </Box>
                        <IconButton
                            color={"secondary"}
                            onClick={() => {setIsColorChangeable(!isColorChangeable)}}
                        >
                            <ColorLensIcon fontSize={"large"} />
                        </IconButton>
                        <IconButton
                            color={"secondary"}
                            onClick={() => {
                                setIsDarkTheme(!isDarkTheme)
                            }}
                        >
                            <Brightness4Icon fontSize={"large"} />
                        </IconButton>
                    </Box>
                </Toolbar>
            </AppBar>
        </Box>
    )
}

export default TopMenu
import React, { useState } from 'react';
import TopMenu from "./navigation/TopMenu";
import MainBody from "./navigation/MainBody";
import { Stack, ThemeProvider } from "@mui/material";
import {
    AddMangaVisibilityContextObject,
    AddMangaVisibilityContext
} from "./common/context/AddMangaVisibilityContextObject";
import { ColorThemeContext, ColorThemeContextObject } from "./common/context/ColorThemeContext";
import { CustomTheme, getDarkTheme, getLightTheme } from "./CustomTheme";
import { LocalStorageContext, LocalStorageContextObject } from "./common/context/LocalStorageContext";
import { LOCAL_STORAGE_KEY, testData, useLocalStorage } from "./common/MangaData";

function App() {

    const [visibility, setVisibility] = useState(false)
    const addMangaDialogVisibility: AddMangaVisibilityContextObject = {
        "value": visibility,
        "setValue": setVisibility
    }

    const [isDarkTheme, setIsDarkTheme] = useState(true)
    const [colorTheme, setColorTheme] = useState(CustomTheme.DEEP_PURPLE)
    const isDarkThemeObject: ColorThemeContextObject = {
        isDarkTheme: isDarkTheme,
        setIsDarkTheme: setIsDarkTheme,
        colorTheme: colorTheme,
        setColorTheme: setColorTheme
    }

    const [localStorage, setLocalStorage] = useLocalStorage(LOCAL_STORAGE_KEY, testData)
    const localStorageObject: LocalStorageContextObject = {
        value: localStorage,
        setValue: setLocalStorage
    }

    return (
        <Stack
            spacing={0}
        >
            <ThemeProvider theme={isDarkTheme ? getDarkTheme(colorTheme) : getLightTheme(colorTheme)}>
                <LocalStorageContext.Provider value={localStorageObject}>
                    <AddMangaVisibilityContext.Provider value={addMangaDialogVisibility}>
                        <ColorThemeContext.Provider value={isDarkThemeObject}>
                            <TopMenu />
                        </ColorThemeContext.Provider>
                        <MainBody />
                    </AddMangaVisibilityContext.Provider>
                </LocalStorageContext.Provider>
            </ThemeProvider>
        </Stack>

    )
        ;
}

export default App;

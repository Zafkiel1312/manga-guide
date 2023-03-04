import React, {useState} from 'react';
import TopMenu from "./navigation/TopMenu";
import MainBody from "./navigation/MainBody";
import {Stack, ThemeProvider} from "@mui/material";
import {ColorThemeContext, ColorThemeContextObject} from "./common/context/ColorThemeContext";
import {CustomTheme, getDarkTheme, getLightTheme} from "./CustomTheme";
import {LocalStorageContext, LocalStorageContextObject} from "./common/context/LocalStorageContext";
import {LOCAL_STORAGE_KEY, testData, useLocalStorage} from "./common/MangaData";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";

function App() {

    const [isDarkTheme, setIsDarkTheme] = useState(true)
    const [colorTheme, setColorTheme] = useState(CustomTheme.DEEP_PURPLE)
    const isDarkThemeObject: ColorThemeContextObject = {
        isDarkTheme: isDarkTheme,
        setIsDarkTheme: setIsDarkTheme,
        colorTheme: colorTheme,
        setColorTheme: setColorTheme
    }

    const queryClient = new QueryClient()

    const [localStorage, setLocalStorage] = useLocalStorage(LOCAL_STORAGE_KEY, testData)
    const localStorageObject: LocalStorageContextObject = {
        value: localStorage,
        setValue: setLocalStorage
    }

    return (
        <BrowserRouter>
            <Stack
                spacing={0}
            >
                <ThemeProvider theme={isDarkTheme ? getDarkTheme(colorTheme) : getLightTheme(colorTheme)}>
                    <QueryClientProvider client={queryClient}>
                        <LocalStorageContext.Provider value={localStorageObject}>
                            <ColorThemeContext.Provider value={isDarkThemeObject}>
                                <TopMenu/>
                            </ColorThemeContext.Provider>
                            <Routes>
                                <Route path={"/"} element={<MainBody/>}/>
                                <Route path={"/view/:mangaId"} element={<MainBody viewManga/>}/>
                                <Route path={"/add/"} element={<MainBody addManga/>}/>
                            </Routes>
                        </LocalStorageContext.Provider>
                    </QueryClientProvider>
                </ThemeProvider>
            </Stack>
        </BrowserRouter>
    )
}

export default App;

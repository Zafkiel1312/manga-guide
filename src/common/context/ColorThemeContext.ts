import { createContext } from "react";
import { CustomTheme } from "../../CustomTheme";

export interface ColorThemeContextObject {
    isDarkTheme: boolean,
    setIsDarkTheme: Function,
    colorTheme: CustomTheme,
    setColorTheme: Function
}

const defaultValue: ColorThemeContextObject = {
    isDarkTheme: true,
    setIsDarkTheme: () => {},
    colorTheme: CustomTheme.DEEP_PURPLE,
    setColorTheme: () => {}
}

export const ColorThemeContext = createContext(defaultValue)

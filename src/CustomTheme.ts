import { createTheme, Theme } from "@mui/material";
import {
    amber, blueGrey,
    deepOrange,
    deepPurple,
    green,
    grey,
    indigo,
    pink, red,
    teal,
} from "@mui/material/colors";

export enum CustomTheme {
    RED = "RED",
    PINK = "PINK",
    DEEP_PURPLE= "DEEP_PURPLE",
    INDIGO = "INDIGO",
    TEAL = "TEAL",
    GREEN = "GREEN",
    AMBER = "AMBER",
    DEEP_ORANGE = "DEEP_ORANGE",
    BLUE_GREY = "BLUE_GREY"
}

export function getColor(colorTheme: CustomTheme) {
    switch (colorTheme) {
        case CustomTheme.RED:
            return red
        case CustomTheme.PINK:
            return pink
        case CustomTheme.DEEP_PURPLE:
            return deepPurple
        case CustomTheme.INDIGO:
            return indigo
        case CustomTheme.TEAL:
            return teal
        case CustomTheme.GREEN:
            return green
        case CustomTheme.AMBER:
            return amber
        case CustomTheme.DEEP_ORANGE:
            return deepOrange
        case CustomTheme.BLUE_GREY:
            return blueGrey
    }
}

export function getDarkTheme(colorTheme: CustomTheme) {
    const secondary = grey[900]
    return createCustomTheme(
        getColor(colorTheme)[700],
        secondary
    )
}

export function getLightTheme(colorTheme: CustomTheme) {
    const secondary = "#FFFFFF"
    return createCustomTheme(
        getColor(colorTheme)[300],
        secondary
    )
}

function createCustomTheme(primary: string, secondary: string): Theme {
    return createTheme({
        palette: {
            primary: {
                main: primary
            },
            secondary: {
                main: secondary
            }
        }
    })
}
import { createContext } from "react";

export interface AddMangaVisibilityContextObject {
    "value": boolean,
    "setValue": Function
}

const defaultValue: AddMangaVisibilityContextObject = {
    value: false,
    setValue: () => {}
}

export const AddMangaVisibilityContext = createContext(defaultValue)

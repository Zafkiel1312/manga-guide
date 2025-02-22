import { createContext } from "react";
import { MangaEntryModel } from "../MangaEntryModel";
import { defaultEntry } from "../MangaData";

export interface ShowMangaContextObject {
    visibility: boolean,
    setVisibility: Function,
    entry: MangaEntryModel,
    setEntry: Function
}

const defaultValue: ShowMangaContextObject = {
    visibility: false,
    setVisibility: () => {},
    entry: defaultEntry,
    setEntry: () => {},
}

export const ShowMangaContext = createContext(defaultValue)
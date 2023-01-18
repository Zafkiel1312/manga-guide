import { createContext } from "react";

export interface LocalStorageContextObject {
    value: any,
    setValue: Function
}

const defaultValue: LocalStorageContextObject = {
    value: "",
    setValue: () => {}
}

export const LocalStorageContext = createContext(defaultValue)
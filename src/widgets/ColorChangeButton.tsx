import { CustomTheme, getColor } from "../CustomTheme";
import CircleIcon from '@mui/icons-material/Circle';
import { useContext } from "react";
import { ColorThemeContext } from "../common/context/ColorThemeContext";

export interface ColorChangeButtonProps {
    theme: CustomTheme,
    isDarkTheme: boolean
}

export function ColorChangeButton(props: ColorChangeButtonProps) {

    const { setColorTheme } = useContext(ColorThemeContext)

    const brightness = props.isDarkTheme ? 700 : 300

    return (
        <div>
            <CircleIcon
                fontSize={"large"}
                style={{
                    color: getColor(props.theme)[brightness],
                    height: "100%",
                }}
                onClick={() => {
                    setColorTheme(props.theme)
                }}
            />
        </div>
    )
}
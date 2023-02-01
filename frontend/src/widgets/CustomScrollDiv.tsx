import React, { useState, useCallback, ReactNode } from "react";

export interface CustomScrollDivProps {
    children: ReactNode[],
    className: string,
    restProps: any[]
}

//ToDo nochmal mit umsetzung auseinander setzten
//https://levelup.gitconnected.com/build-on-hover-custom-scrollbar-in-react-d846194a7ea4

export default function CustomScrollDiv(props: CustomScrollDivProps) {
    const [hovering, setHovering] = useState(false);

    const handleMouseOver = useCallback(() => {
        setHovering(true);
    }, []);
    const handleMouseOut = useCallback(() => {
        setHovering(false);
    }, []);

    return (
        <div
            className={"scrollhost-container"}
            onMouseOver={handleMouseOver}
            onMouseOut={handleMouseOut}
        >
            <div
                className={`scrollhost ${props.className}`}
                {...props.restProps}
            >
                {props.children}
                <div
                    className={"scroll-bar"}
                    style={{ opacity: hovering ? 1 : 0 }}
                >
                </div>
            </div>
        </div>
    );
}
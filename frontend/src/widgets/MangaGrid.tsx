import { ImageList } from "@mui/material";
import { MangaField } from "./MangaField";
import { useContext, useEffect, useState } from "react";
import { MangaEntryModel } from "../common/MangaEntryModel";
import { LocalStorageContext } from "../common/context/LocalStorageContext";

function MangaGrid() {

    const calculateColCount = () => Math.ceil(window.innerWidth / 300)

    const [colCount, setColCount] = useState(calculateColCount())

    useEffect(() => {
        function handleResize() {
            setColCount(calculateColCount())
        }

        window.addEventListener('resize', handleResize)
    })

    const [mangaList, setMangaList] = useState<MangaEntryModel[]>([])

    const { value: entries } = useContext(LocalStorageContext)

    useEffect(() => {
        function renderMangaList() {
            setMangaList(entries as MangaEntryModel[])
        }

        renderMangaList()
    }, [entries])

    return (
        <div>
            <ImageList cols={colCount} id={"image-grid"}>
                {mangaList.map((entry, num) =>
                    <MangaField
                        id={entry.id}
                        title={entry.title}
                        author={entry.author}
                        publisher={entry.publisher}
                        picture={entry.picture}
                        releaseDate={entry.releaseDate}
                        releasedVolumes={entry.releasedVolumes}
                        ownedVolumes={entry.ownedVolumes}
                        nextVolumeRelease={entry.nextVolumeRelease}
                        key={"image-grid-" + num}
                    />
                )}
            </ImageList>
        </div>
    )
}

export default MangaGrid
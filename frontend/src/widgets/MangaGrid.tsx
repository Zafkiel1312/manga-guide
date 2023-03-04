import {ImageList} from "@mui/material";
import {MangaField} from "./MangaField";
import {useEffect, useState} from "react";
import {useGetAllMangas} from "../common/middleware";

function MangaGrid() {

    const calculateColCount = () => Math.ceil(window.innerWidth / 300)

    const [colCount, setColCount] = useState(calculateColCount())

    useEffect(() => {
        function handleResize() {
            setColCount(calculateColCount())
        }

        window.addEventListener('resize', handleResize)
    })

    // const [mangaList, setMangaList] = useState<MangaEntryModel[]>([])

    //const { value: entries } = useContext(LocalStorageContext)
    const { data, isLoading } = useGetAllMangas()

    // useEffect(() => {
    //     function renderMangaList() {
    //         setMangaList(entries as MangaEntryModel[])
    //     }
    //
    //     renderMangaList()
    // }, [entries])

    return (
        <div>
            {!isLoading && <ImageList cols={colCount} id={"image-grid"}>
                {data!.map((entry, num) =>
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
            }
        </div>
    )
}

export default MangaGrid
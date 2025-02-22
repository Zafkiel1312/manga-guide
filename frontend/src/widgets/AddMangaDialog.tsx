import {Box, IconButton, TextField} from "@mui/material";
import {useContext, useState} from "react";
import {compareMangaEntry, MangaEntryModel} from "../common/MangaEntryModel";
import {LocalStorageContext} from "../common/context/LocalStorageContext";
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CancelIcon from '@mui/icons-material/Cancel';
import {defaultEntry} from "../common/MangaData";
import {useNavigate} from "react-router-dom";

export function AddMangaDialog() {
    //ToDo Übergänge ordentlich machen
    const navigate = useNavigate()

    const { value: entries, setValue: setEntries } = useContext(LocalStorageContext)

    const [newEntry, setNewEntry] = useState(defaultEntry)


    function handleSubmit() {
        const oldData: MangaEntryModel[] = entries.filter((entry: MangaEntryModel) => {
            return (
                entry.title !== newEntry.title ||
                entry.author !== newEntry.author
            )
        })

        const newData = [...oldData, newEntry].sort(compareMangaEntry)
        setEntries(newData)
    }


    return (
        <Box id={"add-manga-dialog-main"}>

            <Box
                className={"opaque-background-visible"}
                onClick={() => {navigate("/")}}
            />
            <Box
                className={"add-manga-visible"}
                style={{
                    width: "50%",
                    minWidth: "400px",
                    height: "fit-content",
                    top: "20%",
                    borderRadius: "50px",
                    position: "fixed",
                }}
            >
                    <form id={"add-manga-form"}>
                        <TextField
                            className={"add-manga-text-field"}
                            id="add-manga-title"
                            label="Title"
                            placeholder={"One Piece"}
                            type="text"
                            variant={"filled"}
                            fullWidth
                            margin={"dense"}
                            InputLabelProps={{
                                shrink: true,
                                style: { color: '#fff' }
                            }}
                            InputProps={{
                                style: { color: '#fff' }
                            }}
                            onChange={(e) => {
                                setNewEntry({
                                    ...newEntry,
                                    title: e.target.value
                                })
                            }}
                        />
                        <TextField
                            className={"add-manga-text-field"}
                            id="add-manga-author"
                            label="Author"
                            type="text"
                            placeholder={"Eiichiro Oda"}
                            variant={"filled"}
                            fullWidth
                            margin={"dense"}
                            InputLabelProps={{
                                shrink: true,
                                style: { color: '#fff' }
                            }}
                            InputProps={{
                                style: { color: '#fff' }
                            }}
                            onChange={(e) => {
                                setNewEntry({
                                    ...newEntry,
                                    author: e.target.value
                                })
                            }}
                        />
                        <Box style={{
                            display: "flex",
                            justifyContent: "space-between",
                            width: "100%"
                        }}>
                            <TextField
                                className={"add-manga-text-field"}
                                id="add-manga-publisher"
                                label="Publisher"
                                placeholder={"Carlsen Verlag"}
                                type="text"
                                variant={"filled"}
                                margin={"dense"}
                                style={{
                                    width: "65%"
                                }}
                                InputLabelProps={{
                                    shrink: true,
                                    style: { color: '#fff' }
                                }}
                                InputProps={{
                                    style: { color: '#fff' }
                                }}
                                onChange={(e) => {
                                    setNewEntry({
                                        ...newEntry,
                                        publisher: e.target.value
                                    })
                                }}
                            />
                            <TextField
                                className={"add-manga-text-field"}
                                id={"add-manga-release-year"}
                                label={"Year of release"}
                                placeholder={"2001"}
                                type={"text"}
                                inputMode={"numeric"}
                                variant={"filled"}
                                margin={"dense"}
                                style={{
                                    width: "35%"
                                }}
                                InputLabelProps={{
                                    shrink: true,
                                    style: { color: '#fff' }
                                }}
                                InputProps={{
                                    style: { color: '#fff' }
                                }}
                                onChange={(e) => {
                                    setNewEntry({
                                        ...newEntry,
                                        releaseDate: parseInt(e.target.value)
                                    })
                                }}
                            />
                        </Box>
                        <TextField
                            className={"add-manga-text-field"}
                            id="add-manga-picture"
                            label="Picture"
                            placeholder={"https://mangaguide.de/bilder/frontcover_gross/162.jpg"}
                            type="url"
                            variant={"filled"}
                            fullWidth
                            margin={"dense"}
                            InputLabelProps={{
                                shrink: true,
                                style: { color: '#fff' }
                            }}
                            InputProps={{
                                style: { color: '#fff' }
                            }}
                            onChange={(e) => {
                                setNewEntry({
                                    ...newEntry,
                                    picture: e.target.value
                                })
                            }}
                        />
                        <Box style={{
                            display: "flex",
                            justifyContent: "space-between",
                            width: "100%"
                        }}>
                            <TextField
                                className={"add-manga-text-field"}
                                id={"add-manga-owned-volumes"}
                                label={"Number of owned volumes"}
                                placeholder={"13"}
                                type={"text"}
                                inputMode={"numeric"}
                                variant={"filled"}
                                margin={"dense"}
                                style={{
                                    width: "25%"
                                }}
                                InputLabelProps={{
                                    shrink: true,
                                    style: { color: '#fff' }
                                }}
                                InputProps={{
                                    style: { color: '#fff' }
                                }}
                                onChange={(e) => {
                                    setNewEntry({
                                        ...newEntry,
                                        ownedVolumes: parseInt(e.target.value)
                                    })
                                }}
                            />
                            <TextField
                                className={"add-manga-text-field"}
                                id={"add-manga-release-volumes"}
                                label={"Number of released volumes"}
                                placeholder={"102"}
                                type={"text"}
                                inputMode={"numeric"}
                                variant={"filled"}
                                margin={"dense"}
                                style={{
                                    width: "25%"
                                }}
                                InputLabelProps={{
                                    shrink: true,
                                    style: { color: '#fff' }
                                }}
                                InputProps={{
                                    style: { color: '#fff' }
                                }}
                                onChange={(e) => {
                                    setNewEntry({
                                        ...newEntry,
                                        releasedVolumes: parseInt(e.target.value)
                                    })
                                }}
                            />
                            <TextField
                                className={"add-manga-text-field"}
                                id={"add-manga-next-volume"}
                                label={"Date for next volume"}
                                placeholder={"Februar 2023"}
                                type={"text"}
                                variant={"filled"}
                                margin={"dense"}
                                style={{
                                    width: "50%"
                                }}
                                InputLabelProps={{
                                    shrink: true,
                                    style: { color: '#fff' }
                                }}
                                InputProps={{
                                    style: { color: '#fff' }
                                }}
                                onChange={(e) => {
                                    setNewEntry({
                                        ...newEntry,
                                        nextVolumeRelease: e.target.value
                                    })
                                }}
                            />
                        </Box>
                        <IconButton
                            color={"primary"}
                            onClick={handleSubmit}
                        >
                            <CheckCircleIcon fontSize={"large"} />
                        </IconButton>
                        <IconButton
                            color={"primary"}
                            onClick={() => {
                                navigate("/")
                            }}
                        >
                            <CancelIcon fontSize={"large"} />
                        </IconButton>

                    </form>
            </Box>
        </Box>
    )
}

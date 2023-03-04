import {useEffect, useState} from "react";
import {Box, IconButton, TextField} from "@mui/material";
import {CreateMangaEntryModel} from "../common/MangaEntryModel";
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CancelIcon from '@mui/icons-material/Cancel';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {useNavigate} from "react-router-dom";
import {deleteManga, updateManga, useGetAllMangas, useGetManga} from "../common/middleware";
import {defaultEntry} from "../common/MangaData";

interface ShowMangaDialogProps {
    mangaId: string | undefined,
}

export function ShowMangaDialog(props: ShowMangaDialogProps) {
    // ToDo Übergang wieder einbauen
    const mangaId = props.mangaId || ""

    const navigate = useNavigate()

    const [entry, setEntry] = useState<CreateMangaEntryModel>(defaultEntry)

    const {data, isLoading, isError} = useGetManga(mangaId || "")
    const {} = useGetAllMangas()

    useEffect(() => {
        if (isError)
            navigate("/",)
    }, [isError])

    useEffect(() => {
        if (data !== undefined)
            setEntry({
                author: data.author,
                nextVolumeRelease: data.nextVolumeRelease,
                ownedVolumes: data.ownedVolumes,
                picture: data.picture,
                publisher: data.publisher,
                releaseDate: data.releaseDate,
                releasedVolumes: data.releasedVolumes,
                title: data.title
            })
    }, [isLoading])

    const [editable, setEditable] = useState(false)

    function handleSubmit() {
        updateManga(mangaId!, entry).then()
        setEditable(false)
        navigate("/")
    }

    function handleDelete() {
        deleteManga(mangaId).then()
        setEditable(false)
        navigate("/")
    }

    return (
        <Box id={"show-manga-dialog-main"}>

            <Box
                className={"opaque-background-visible"}
                onClick={() => {
                    navigate("/")
                    setEditable(false)
                }}
            />
            {!isLoading && (
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
                        <Box
                            style={{
                                display: "flex",
                                flexWrap: "nowrap"
                            }}
                        >
                            <TextField
                                className={"add-manga-text-field"}
                                id="add-manga-title"
                                label="Title"
                                defaultValue={entry?.title}
                                type="text"
                                variant={"filled"}
                                fullWidth
                                margin={"dense"}
                                InputLabelProps={{
                                    shrink: true,
                                    style: {color: '#fff'}
                                }}
                                InputProps={{
                                    readOnly: !editable,
                                    style: {color: '#fff'}
                                }}

                                onChange={(e) => {
                                    setEntry({
                                        ...entry,
                                        title: e.target.value
                                    })
                                }}
                            />
                            {editable && (
                                <IconButton
                                    color={"primary"}
                                    onClick={handleDelete}
                                >
                                    <DeleteIcon fontSize={"large"}/>
                                </IconButton>
                            )}
                        </Box>
                        <TextField
                            className={"add-manga-text-field"}
                            id="add-manga-author"
                            label="Author"
                            defaultValue={entry?.author}
                            type="text"
                            variant={"filled"}
                            fullWidth
                            margin={"dense"}
                            InputLabelProps={{
                                shrink: true,
                                style: {color: '#fff'}
                            }}
                            InputProps={{
                                readOnly: !editable,
                                style: {color: '#fff'}
                            }}
                            onChange={(e) => {
                                setEntry({
                                    ...entry,
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
                                defaultValue={entry?.publisher}
                                type="text"
                                variant={"filled"}
                                margin={"dense"}
                                style={{
                                    width: "65%"
                                }}
                                InputLabelProps={{
                                    shrink: true,
                                    style: {color: '#fff'}
                                }}
                                InputProps={{
                                    readOnly: !editable,
                                    style: {color: '#fff'}
                                }}
                                onChange={(e) => {
                                    setEntry({
                                        ...entry,
                                        publisher: e.target.value
                                    })
                                }}
                            />
                            <TextField
                                className={"add-manga-text-field"}
                                id={"add-manga-release-year"}
                                label={"Year of release"}
                                defaultValue={entry?.releaseDate}
                                type={"text"}
                                inputMode={"numeric"}
                                variant={"filled"}
                                margin={"dense"}
                                style={{
                                    width: "35%"
                                }}
                                InputLabelProps={{
                                    shrink: true,
                                    style: {color: '#fff'}
                                }}
                                InputProps={{
                                    readOnly: !editable,
                                    style: {color: '#fff'}
                                }}
                                onChange={(e) => {
                                    setEntry({
                                        ...entry,
                                        releaseDate: parseInt(e.target.value)
                                    })
                                }}
                            />
                        </Box>
                        {editable && (
                            <TextField
                                className={"add-manga-text-field"}
                                id="add-manga-picture"
                                label="Picture"
                                defaultValue={entry?.picture}
                                type="url"
                                variant={"filled"}
                                fullWidth
                                margin={"dense"}
                                InputLabelProps={{
                                    shrink: true,
                                    style: {color: '#fff'}
                                }}
                                InputProps={{
                                    readOnly: !editable,
                                    style: {color: '#fff'}
                                }}
                                onChange={(e) => {
                                    setEntry({
                                        ...entry,
                                        picture: e.target.value
                                    })
                                }}
                            />
                        )}
                        <Box style={{
                            display: "flex",
                            justifyContent: "space-between",
                            width: "100%"
                        }}>
                            <TextField
                                className={"add-manga-text-field"}
                                id={"add-manga-owned-volumes"}
                                label={"Number of owned volumes"}
                                defaultValue={entry?.ownedVolumes}
                                type={"text"}
                                inputMode={"numeric"}
                                variant={"filled"}
                                margin={"dense"}
                                style={{
                                    width: "25%"
                                }}
                                InputLabelProps={{
                                    shrink: true,
                                    style: {color: '#fff'}
                                }}
                                InputProps={{
                                    readOnly: !editable,
                                    style: {color: '#fff'}
                                }}
                                onChange={(e) => {
                                    setEntry({
                                        ...entry,
                                        ownedVolumes: parseInt(e.target.value)
                                    })
                                }}
                            />
                            <TextField
                                className={"add-manga-text-field"}
                                id={"add-manga-release-volumes"}
                                label={"Number of released volumes"}
                                defaultValue={entry?.releasedVolumes}
                                type={"text"}
                                inputMode={"numeric"}
                                variant={"filled"}
                                margin={"dense"}
                                style={{
                                    width: "25%"
                                }}
                                InputLabelProps={{
                                    shrink: true,
                                    style: {color: '#fff'}
                                }}
                                InputProps={{
                                    readOnly: !editable,
                                    style: {color: '#fff'}
                                }}
                                onChange={(e) => {
                                    setEntry({
                                        ...entry,
                                        releasedVolumes: parseInt(e.target.value)
                                    })
                                }}
                            />
                            <TextField
                                className={"add-manga-text-field"}
                                id={"add-manga-next-volume"}
                                label={"Date for next volume"}
                                defaultValue={entry?.nextVolumeRelease}
                                type={"text"}
                                variant={"filled"}
                                margin={"dense"}
                                style={{
                                    width: "50%"
                                }}
                                InputLabelProps={{
                                    shrink: true,
                                    style: {color: '#fff'}
                                }}
                                InputProps={{
                                    readOnly: !editable,
                                    style: {color: '#fff'}
                                }}
                                onChange={(e) => {
                                    setEntry({
                                        ...entry,
                                        nextVolumeRelease: e.target.value
                                    })
                                }}
                            />
                        </Box>
                        {editable && (
                            <Box>
                                <IconButton
                                    color={"primary"}
                                    onClick={handleSubmit}
                                >
                                    <CheckCircleIcon fontSize={"large"}/>
                                </IconButton>
                                <IconButton
                                    color={"primary"}
                                    onClick={() => {
                                        navigate("/")
                                        setEditable(false)
                                    }}
                                >
                                    <CancelIcon fontSize={"large"}/>
                                </IconButton>
                            </Box>

                        )}
                        {!editable && (
                            <IconButton
                                color={"primary"}
                                onClick={() => {
                                    setEditable(true)
                                }}
                            >
                                <EditIcon fontSize={"large"}/>
                            </IconButton>
                        )}
                    </form>
                </Box>
            )}
        </Box>
    )
}

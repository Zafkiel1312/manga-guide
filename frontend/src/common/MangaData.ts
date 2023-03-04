
import { v4 as uuidv4 } from 'uuid';
import {CreateMangaEntryModel, MangaEntryModel} from "./MangaEntryModel";
import { useEffect, useState } from "react";

export const LOCAL_STORAGE_KEY = "manga-guide"

export const useLocalStorage = (storageKey: string, fallbackState: any) => {
    const storedData = localStorage.getItem(storageKey)

    const [value, setValue] = useState(
        storedData ? JSON.parse(storedData) : fallbackState
    );

    useEffect(() => {
        localStorage.setItem(storageKey, JSON.stringify(value));
    }, [value, storageKey]);

    return [value, setValue];
}

export const defaultEntry: CreateMangaEntryModel = {
    title: "One Piece",
    author: "Eiichiro Oda",
    publisher: "Carlsen Verlag",
    releaseDate: 2001,
    picture: "https://mangaguide.de/bilder/frontcover_gross/162.jpg",
    releasedVolumes: 102,
    ownedVolumes: 12,
    nextVolumeRelease: "Februar 2023"
}

export const testData: MangaEntryModel[] =
    [
        {
            "id": uuidv4(),
            "title": "Accel World",
            "author": "Hiroyuki Aigamo",
            "publisher": "Tokyopop",
            "picture": "https://mangaguide.de/bilder/frontcover_gross/11034.jpg",
            "releaseDate": 2014,
            "releasedVolumes": 8,
            "ownedVolumes": 8,
            "nextVolumeRelease": ""
        },
        {
            "id": uuidv4(),
            "title": "Aldnoah.Zero",
            "author": "Pinakes",
            "publisher": "KAZÉ Manga",
            "picture": "https://mangaguide.de/bilder/frontcover_gross/11308.jpg",
            "releaseDate": 2015,
            "releasedVolumes": 4,
            "ownedVolumes": 4,
            "nextVolumeRelease": ""
        },
        {
            "id": uuidv4(),
            "title": "Chainsaw Man",
            "author": " Tatsuki Fujimoto",
            "publisher": "Egmont Manga",
            "picture": "https://mangaguide.de/bilder/frontcover_gross/20026.jpg",
            "releaseDate": 2020,
            "releasedVolumes": 11,
            "ownedVolumes": 11,
            "nextVolumeRelease": "Mai 2023"
        },
        {
            "id": uuidv4(),
            "title": "Die Braut des Magiers",
            "author": "Kore Yamazaki",
            "publisher": "Tokyopop",
            "picture": "https://mangaguide.de/bilder/frontcover_gross/11848.jpg",
            "releaseDate": 2015,
            "releasedVolumes": 16,
            "ownedVolumes": 7,
            "nextVolumeRelease": "August 2023"
        },
        {
            "id": uuidv4(),
            "title": "Dimension W",
            "author": "Yuji Iwahara",
            "publisher": "KAZÉ Manga",
            "picture": "https://mangaguide.de/bilder/frontcover_gross/12207.jpg",
            "releaseDate": 2016,
            "releasedVolumes": 16,
            "ownedVolumes": 3,
            "nextVolumeRelease": ""
        },
        {
            "id": uuidv4(),
            "title": "Komi can't communicate",
            "author": "Tomohito Oda",
            "publisher": "Tokyopop",
            "picture": "https://mangaguide.de/bilder/frontcover_gross/17860.jpg",
            "releaseDate": 2020,
            "releasedVolumes": 15,
            "ownedVolumes": 14,
            "nextVolumeRelease": "Januar 2023"
        },
        {
            "id": uuidv4(),
            "title": "More than a doll",
            "author": "Shinichi Fukuda",
            "publisher": "Egmont Manga",
            "picture": "https://mangaguide.de/bilder/frontcover_gross/18075.jpg",
            "releaseDate": 2020,
            "releasedVolumes": 9,
            "ownedVolumes": 9,
            "nextVolumeRelease": "Juni 2023"
        },
        {
            "id": uuidv4(),
            "title": "Shy",
            "author": "Bukimi Miki",
            "publisher": "Crunchyroll",
            "picture": "https://mangaguide.de/bilder/frontcover_gross/18671.jpg",
            "releaseDate": 2021,
            "releasedVolumes": 11,
            "ownedVolumes": 10,
            "nextVolumeRelease": "Februar 2023"
        },
        {
            "id": uuidv4(),
            "title": "Spice & Wolf",
            "author": "Keito Koume",
            "publisher": "Planet Manga",
            "picture": "https://mangaguide.de/bilder/frontcover_gross/8024.jpg",
            "releaseDate": 2011,
            "releasedVolumes": 16,
            "ownedVolumes": 7,
            "nextVolumeRelease": ""
        },
        {
            "id": uuidv4(),
            "title": "Spy x Family",
            "author": "Tatsuya Endo",
            "publisher": "Crunchyroll",
            "picture": "https://mangaguide.de/bilder/frontcover_gross/17920.jpg",
            "releaseDate": 2020,
            "releasedVolumes": 9,
            "ownedVolumes": 9,
            "nextVolumeRelease": ""
        },
        {
            "id": uuidv4(),
            "title": "The Book of List – Grimm’s Magical Items",
            "author": "Izuco Fujiya",
            "publisher": "Carlsen Verlag",
            "picture": "https://mangaguide.de/bilder/frontcover_gross/10988.jpg",
            "releaseDate": 2015,
            "releasedVolumes": 6,
            "ownedVolumes": 6,
            "nextVolumeRelease": ""
        },
        {
            "id": uuidv4(),
            "title": "Tonikawa – Fly Me to the Moon",
            "author": "Kenjiro Hata",
            "publisher": "Manga Cult",
            "releaseDate": 2021,
            "picture": "https://mangaguide.de/bilder/frontcover_gross/18738.jpg",
            "releasedVolumes": 10,
            "ownedVolumes": 10,
            "nextVolumeRelease": "März 2023"
        }
    ]
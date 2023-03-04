import PocketBase from 'pocketbase';
import {useQuery} from "@tanstack/react-query";
import {CreateMangaEntryModel} from "./MangaEntryModel";

const pb = new PocketBase("http://127.0.0.1:8090")

export const useGetAllMangas = () => useQuery(["getAllManga"], async () =>
    await pb.collection("mangas").getFullList()
)

export const saveManga = (manga: CreateMangaEntryModel) =>
    pb.collection("mangas").create(manga).then(res => res)


export const updateManga = (mangaId: string, manga: CreateMangaEntryModel) =>
    pb.collection('mangas').update(mangaId, manga)



export const useGetManga = (mangaId: string) => useQuery(["getManga-" + mangaId], async () =>
    await pb.collection('mangas').getOne(mangaId)
)

export const deleteManga = (mangaId: string) =>
    pb.collection('mangas').delete(mangaId)

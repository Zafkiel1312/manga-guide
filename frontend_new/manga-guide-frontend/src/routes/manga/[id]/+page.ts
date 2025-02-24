import {getMangaById} from "$lib/middleware/mangaApi";
import type {PageLoad} from "../../../../.svelte-kit/types/src/routes/manga/[id]/$types";

export const load: PageLoad = async ({params}) => {
    const id = params.id
    return {
        manga: await getMangaById(id)
    }
}
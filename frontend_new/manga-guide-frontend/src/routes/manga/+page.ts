import {getAllMangas} from "$lib/middleware/mangaApi";
import type {PageLoad} from "../../../.svelte-kit/types/src/routes/manga/[id]/$types";

export const load: PageLoad = async () => {
    return {
        mangas: await getAllMangas()
    }
}
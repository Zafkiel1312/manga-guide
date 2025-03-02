import type {PageLoad} from "../../../../.svelte-kit/types/src/routes/manga/[id]/$types";
import {getAllMangaSources} from "$lib/middleware/mangaApi";

export const load: PageLoad = async () => {
    return {
        sourcesKeys: await getAllMangaSources()
    }
}
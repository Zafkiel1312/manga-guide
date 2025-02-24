<script lang="ts">
    import {type MangaDto} from "$lib/middleware/mangaApi.js";
    import MangaField from "$lib/components/manga/MangaField.svelte";
    import {getContext} from "svelte";
    import type {PageProps} from "../../../.svelte-kit/types/src/routes/manga/[id]/$types";

    function chunkArray<T>(array: T[], chunkSize: number): T[][] {
        const chunkedArraySize = Math.ceil(array.length / chunkSize)
        const newArray = Array<T[]>(chunkedArraySize)
        for (let i = 0; i < array.length; i += chunkSize) {
            newArray[i / chunkSize] = array.slice(i, i + chunkSize)
        }
        return newArray
    }

    let {data}: PageProps = $props()
    let mangas = data.mangas

    let usableWidth = getContext<() => number>("usableWidth")
    let rowSize = $derived(Math.ceil(usableWidth()/420))
    let leftOverSpaces = $derived((rowSize - (mangas.length % rowSize)) % rowSize)
    let rowCount = $derived(Math.ceil(mangas.length / rowSize))
</script>

<div class="w-full h-full flex flex-col space-y-10 p-10">
    {#each chunkArray<MangaDto>(mangas, rowSize) as mangaChunks, i}
        <div class="w-full flex flex-row space-x-10">
            {#each mangaChunks as manga}
                <div class="overflow-hidden rounded-2xl hover:scale-110 transition w-full">
                    <MangaField manga={manga} />
                </div>
            {/each}
            {#if (rowCount === i+1) && (leftOverSpaces > 0)}
                {#each Array(leftOverSpaces) as _}
                    <div class="w-full"></div>
                {/each}
            {/if}
        </div>
    {/each}
</div>

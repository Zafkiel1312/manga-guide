<script lang="ts">
    import {getAllMangas, type MangaDto} from "$lib/middleware/mangaApi.js";
    import MangaField from "$lib/components/manga/MangaField.svelte";
    import {getContext} from "svelte";

    function chunkArray<T>(array: T[], chunkSize: number): T[][] {
        const chunkedArraySize = Math.ceil(array.length / chunkSize)
        const newArray = Array<T[]>(chunkedArraySize)
        for (let i = 0; i < array.length; i += chunkSize) {
            newArray[i / chunkSize] = array.slice(i, i + chunkSize)
        }
        return newArray
    }

    let usableWidth = getContext<() => number>("usableWidth")
    let rowSize = $derived(Math.ceil(usableWidth()/420))
</script>

{#await getAllMangas()}
    <span>Waiting...</span>
{:then mangas}
    <div class="w-full h-full flex flex-col space-y-10 p-10">
        {#each chunkArray<MangaDto>(mangas, rowSize) as mangaChunks}
            <div class="w-full flex flex-row space-x-10">
                {#each mangaChunks as manga}
                    <div class="overflow-hidden rounded-2xl hover:scale-110 transition w-full">
                        <MangaField manga={manga} />
                    </div>
                {/each}
            </div>
        {/each}
    </div>
{/await}

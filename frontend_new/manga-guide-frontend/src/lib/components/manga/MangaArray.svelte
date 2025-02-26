<script lang="ts">

    import {getContext} from "svelte";
    import {chunkArray} from "$lib/util/arrayUtil";
    import {createNemManga, type MangaDto} from "$lib/middleware/mangaApi";
    import MangaField from "$lib/components/manga/MangaField.svelte";
    import {goto} from "$app/navigation";

    let {mangas, linkToDetails} = $props()

    let usableWidth = getContext<() => number>("usableWidth")
    let rowSize = $derived(Math.ceil(usableWidth() / 420))
    let leftOverSpaces = $derived((rowSize - (mangas.length % rowSize)) % rowSize)
    let rowCount = $derived(Math.ceil(mangas.length / rowSize))

    let onClickEvent = async (id: number) => {
        let uuid = await createNemManga(id)
        await goto(`/manga/${uuid}`)
    }
</script>

<div class="w-full h-full flex flex-col space-y-10 p-10">
    {#each chunkArray < MangaDto > (mangas, rowSize) as mangaChunks, i}
        <div class="w-full flex flex-row space-x-10">
            {#each mangaChunks as manga}
                <div class="overflow-hidden rounded-2xl hover:scale-110 transition w-full">
                    {#if linkToDetails}
                        <MangaField text={manga.title} alt={manga.title} imageUrl={manga.imageUrl} href="/manga/{manga.id}" onClickEvent={() => {}}/>
                    {:else}
                        <MangaField text={manga.title} alt={manga.title} imageUrl={manga.imageUrl} href="/manga/add" onClickEvent={() => {onClickEvent(manga.mangaPassionId)}} />
                    {/if}
                </div>
            {/each}
            {#if (rowCount === i + 1) && (leftOverSpaces > 0)}
                {#each Array(leftOverSpaces) as _}
                    <div class="w-full"></div>
                {/each}
            {/if}
        </div>
    {/each}
</div>
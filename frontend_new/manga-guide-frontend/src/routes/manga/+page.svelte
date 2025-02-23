<script>
    import {getAllMangas} from "$lib/middleware/mangaApi.js";
    import MangaField from "$lib/components/manga/MangaField.svelte";

</script>

{#await getAllMangas()}
    <span>Waiting...</span>
{:then mangas}
    <div class="w-max h-max flex flex-row space-x-10 m-10">
        {#each mangas as manga}
            <div class="overflow-hidden rounded-2xl hover:scale-110 transition h-128 w-64">
                <MangaField manga={manga} />
            </div>
        {/each}
    </div>
{/await}

<script lang="ts">
    import type {PageProps} from "../../../.svelte-kit/types/src/routes/manga/[id]/$types";
    import MaterialSymbolsAdd2Rounded from '~icons/material-symbols/add-2-rounded'
    import MangaArray from "$lib/components/manga/MangaArray.svelte";

    let {data}: PageProps = $props()
    let mangas = data.mangas

    // let usableWidth = getContext<() => number>("usableWidth")
    // let rowSize = $derived(Math.ceil(usableWidth() / 420))
    // let leftOverSpaces = $derived((rowSize - (mangas.length % rowSize)) % rowSize)
    // let rowCount = $derived(Math.ceil(mangas.length / rowSize))
</script>

<div>
    <MangaArray mangas={mangas} linkToDetails={true} onClickEvent={() => {}}/>
    <div class="absolute bottom-10 right-10 z-50">
        <a class="btn-icon btn-icon-xl bg-primary-600" href="/manga/add">
            <MaterialSymbolsAdd2Rounded />
        </a>
    </div>
</div>
<script lang="ts">
    import IcBaselineSearch from '~icons/ic/baseline-search'
    import {type MangaSearchDto, searchNewManga} from "$lib/middleware/mangaApi";
    import MangaArray from "$lib/components/manga/MangaArray.svelte";
    import {onMount} from "svelte";
    import type {PageProps} from "../../../../.svelte-kit/types/src/routes/manga/[id]/$types";
    import {ProgressRadial} from "@skeletonlabs/skeleton";

    let {data}: PageProps = $props()
    let sourceKeys = data.sourcesKeys

    let selectedSource = $state(sourceKeys[0])

    let searchString = $state("")
    let mangas = $state(new Array<MangaSearchDto>())
    let searching = $state(false)

    const submitSearch = async (search: string) => {
        searching = true
        mangas = await searchNewManga(selectedSource, search)
        searching = false
    }

    onMount(() => {
        let input = document.getElementById("search-new-manga")
        input!!.addEventListener("keypress", function (event) {
            if (event.key === "Enter") {
                event.preventDefault()
                document.getElementById("search-submit-button")!!.click()
            }
        })
    })
</script>

<div>
    <div class="p-5">
        <div class="input-group input-group-divider grid-cols-[auto_1fr_auto_auto] h-10">
            <div class="input-group-shim">
                <IcBaselineSearch/>
            </div>
            <input id="search-new-manga" type="search" placeholder="Search..." class="pl-3 w-5/6"
                   bind:value={searchString}
                   onsubmit={() => submitSearch(searchString)}/>
            <select bind:value={selectedSource} class="pl-3 pr-3 w-40">
                {#each sourceKeys as sourceKey}
                    <option value={sourceKey}>{sourceKey}</option>
                {/each}
            </select>
            <button id="search-submit-button" class="variant-filled-primary" onclick={() => submitSearch(searchString)}>
                Submit
            </button>
        </div>
    </div>
    {#if searching}
        <div class="pl-5">
            <ProgressRadial value="{undefined}" width="w-14" stroke="80" meter="stroke-primary-600" track="stroke-primary-600/30"/>
        </div>
    {:else}
        <MangaArray mangas={mangas} linkToDetails={false} sourceKey={selectedSource}/>
    {/if}
</div>

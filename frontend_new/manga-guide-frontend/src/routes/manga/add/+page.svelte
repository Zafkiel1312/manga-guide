<script lang="ts">
    import IcBaselineSearch from '~icons/ic/baseline-search'
    import {type MangaSearchDto, searchNewManga} from "$lib/middleware/mangaApi";
    import MangaArray from "$lib/components/manga/MangaArray.svelte";
    import {onMount} from "svelte";

    let searchString = $state("")
    let mangas = $state(new Array<MangaSearchDto>())

    const submitSearch = async (search: string) => {
        mangas = await searchNewManga(search)
    }

    onMount(() => {
        let input = document.getElementById("search-new-manga")
        input!!.addEventListener("keypress", function(event) {
            if (event.key === "Enter") {
                event.preventDefault()
                document.getElementById("search-submit-button")!!.click()
            }
        })
    })
</script>

<div>
    <div class="p-5">
        <div class="input-group input-group-divider grid-cols-[auto_1fr_auto] h-10">
            <div class="input-group-shim">
                <IcBaselineSearch/>
            </div>
            <input id="search-new-manga" type="search" placeholder="Search..." class="pl-3" bind:value={searchString}  onsubmit={() => submitSearch(searchString)}/>
            <button id="search-submit-button" class="variant-filled-primary" onclick={() => submitSearch(searchString)}>Submit</button>
        </div>
    </div>
    <MangaArray mangas={mangas} linkToDetails={false}/>
</div>

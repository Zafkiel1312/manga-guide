<script lang="ts">
    import '../app.css';
    import { AppRail, AppRailTile, AppRailAnchor } from '@skeletonlabs/skeleton';
    import MaterialSymbolsMenu from '~icons/material-symbols/menu'
    import MaterialSymbolsMenuBook from '~icons/material-symbols/menu-book'
    import MaterialSymbolsArticlePerson from '~icons/material-symbols/article-person'
    import MaterialSymbolsAccountCircleFull from '~icons/material-symbols/account-circle-full'
    import { page } from '$app/state';
    import {setContext} from "svelte";

    let { children } = $props();
    let currentTile: number = $state(0);

    let width = $state(1)
    let height = $state(1)

    setContext("usableWidth", () => width)
    setContext("usableHeight", () => height)

</script>
<div class="flex flex-row w-full">
    <AppRail height="h-screen">
        <svelte:fragment slot="lead">
            <AppRailAnchor href="/">
                <span class="flex justify-center w-full">
                    <MaterialSymbolsMenu style="font-size:2em"/>
                </span>
            </AppRailAnchor>
        </svelte:fragment>
        <AppRailAnchor href="/manga" selected={page.url.pathname === "/manga"} bind:group={currentTile} name="manga" value={0} title="manga">
            <svelte:fragment slot="lead">
                <span class="flex justify-center w-full">
                    <MaterialSymbolsMenuBook style="font-size:1.5em"/>
                </span>
            </svelte:fragment>
            <span>Manga</span>
        </AppRailAnchor>
        <AppRailAnchor href="/publisher" selected={page.url.pathname === "/publisher"} bind:group={currentTile} name="publisher" value={1} title="publisher">
            <svelte:fragment slot="lead">
                <span class="flex justify-center w-full">
                    <MaterialSymbolsArticlePerson style="font-size:1.5em"/>
                </span>
            </svelte:fragment>
            <span>Publisher</span>
        </AppRailAnchor>
        <AppRailAnchor slot="trail" >
            <svelte:fragment slot="lead">
                <span class="flex justify-center w-full">
                    <MaterialSymbolsAccountCircleFull style="font-size:1.5em" />
                </span>
            </svelte:fragment>
        </AppRailAnchor>
    </AppRail>
    <div bind:clientHeight={height} bind:clientWidth={width} class="h-screen w-full overflow-scroll hide-scrollbar">
        {@render children()}
    </div>
</div>

<script lang="ts">
    import '../app.css';
    import { AppRail, AppRailTile, AppRailAnchor } from '@skeletonlabs/skeleton';
    import MaterialSymbolsMenu from '~icons/material-symbols/menu'
    import MaterialSymbolsMenuBook from '~icons/material-symbols/menu-book'
    import MaterialSymbolsArticlePerson from '~icons/material-symbols/article-person'
    import MaterialSymbolsAccountCircleFull from '~icons/material-symbols/account-circle-full'
    let { children } = $props();
    let currentTile: number = $state(0);
</script>
<div class="flex flex-row">
    <AppRail height="h-screen">
        <svelte:fragment slot="lead">
            <AppRailAnchor href="/">
                <span class="flex justify-center w-full">
                    <MaterialSymbolsMenu style="font-size:2em"/>
                </span>
            </AppRailAnchor>
        </svelte:fragment>
        <!-- --- -->
        <AppRailTile bind:group={currentTile} name="manga" value={0} title="manga">
            <svelte:fragment slot="lead">
                <span class="flex justify-center w-full">
                    <MaterialSymbolsMenuBook style="font-size:1.5em"/>
                </span>
            </svelte:fragment>
            <span>Manga</span>
        </AppRailTile>
        <AppRailTile bind:group={currentTile} name="publisher" value={1} title="publisher">
            <svelte:fragment slot="lead">
                <span class="flex justify-center w-full">
                    <MaterialSymbolsArticlePerson style="font-size:1.5em"/>
                </span>
            </svelte:fragment>
            <span>Publisher</span>
        </AppRailTile>
        <AppRailTile slot="trail" bind:group={currentTile} name="account" value={2} title="account">
            <svelte:fragment slot="lead">
                <span class="flex justify-center w-full">
                    <MaterialSymbolsAccountCircleFull style="font-size:1.5em" />
                </span>
            </svelte:fragment>
        </AppRailTile>
    </AppRail>
    {@render children()}
</div>

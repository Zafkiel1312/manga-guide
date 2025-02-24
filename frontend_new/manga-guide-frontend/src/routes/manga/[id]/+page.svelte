<script lang="ts">
    import type {PageProps} from './$types';
    import MaterialSymbolsCheckCircleRounded from '~icons/material-symbols/check-circle-rounded'

    let {data}: PageProps = $props();
    data.manga.releaseDate = new Date(data.manga.releaseDate)

    let boughtFinished = data.manga.boughtVolumes === data.manga.japaneseVolumes && data.manga.finishedJapanese
</script>

<div class="w-full h-full flex flex-col">
    <div class="w-full h-full flex flex-row justify-between">
        <div class="h-fit w-full pr-5 border-primary-700 border-r-2">
            <div class="h-fit w-full flex flex-col border-b-2 border-primary-700 pb-5 mr-5">
                <div class="font-semibold text-xl text-primary-600">
                    {data.manga.title}
                </div>
                <div>
                    <span>by </span>
                    {#each data.manga.author as author, i}
                        <span>{author}{i === data.manga.author.length ? "," : ""} </span>
                    {/each}
                </div>
                <div class="text-surface-400">
                    released: {data.manga.releaseDate.getDate()}.{data.manga.releaseDate.getMonth()+1}.{data.manga.releaseDate.getFullYear()}
                </div>
            </div>
            <div class="table-container pt-5">
                <table class="table table-hover text-center">
                    <thead>
                        <tr>
                            <th></th>
                            <th class="text-center">volumes</th>
                            <th class="text-center">finished</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>bought</td>
                            <td>{data.manga.boughtVolumes}</td>
                            <td class="flex justify-center">
                                {#if boughtFinished}
                                    <MaterialSymbolsCheckCircleRounded class="text-primary-700"/>
                                {/if}
                            </td>
                        </tr>
                        <tr>
                            <td>released</td>
                            <td>{data.manga.releasedVolumes}</td>
                            <td class="flex justify-center">
                                {#if data.manga.finished}
                                    <MaterialSymbolsCheckCircleRounded class="text-primary-700"/>
                                {/if}
                            </td>
                        </tr>
                        <tr>
                            <td>japanese</td>
                            <td>{data.manga.japaneseVolumes}</td>
                            <td class="flex justify-center">
                                {#if data.manga.finishedJapanese}
                                    <MaterialSymbolsCheckCircleRounded class="text-primary-700"/>
                                {/if}
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="h-full w-fit pl-5">
            <div class="h-72 w-48 overflow-hidden rounded-2xl">
                <img src={data.manga.pictureUrl} alt={data.manga.title} class="object-cover h-full w-full"/>
            </div>
        </div>
    </div>
</div>
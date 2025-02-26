<script lang="ts">
    import type {PageProps} from './$types';
    import MaterialSymbolsCheckCircleRounded from '~icons/material-symbols/check-circle-rounded'

    let {data}: PageProps = $props();
    if (data.manga.releaseDate != null) {
        data.manga.releaseDate = new Date(data.manga.releaseDate)
    }
    data.volumes.forEach((volume) => {
        if (volume.releaseDate != null) {
            volume.releaseDate = new Date(volume.releaseDate)
        }
    })

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
                    released: {data.manga.releaseDate.getDate()}.{data.manga.releaseDate.getMonth() + 1}
                    .{data.manga.releaseDate.getFullYear()}
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
                <img src={data.manga.imageUrl} alt={data.manga.title} class="object-cover h-full w-full"/>
            </div>
        </div>
    </div>
    <div class="snap-x scroll-px-4 snap-mandatory scroll-smooth flex gap-4 overflow-x-auto px-4 py-10">
        {#each data.volumes as volume, i}
            <div class="snap-start shrink-0 card h-72 w-48 overflow-hidden hover:scale-110 transition">
                <div class="relative z-0 h-full w-full">
                    <img src={volume.imageUrl} alt={i} class="object-cover h-full w-full"/>
                    <div class="absolute z-10 bottom-0 bg-surface-800 opacity-90 h-1/6 w-full flex flex-col justify-center">
                        <div class="w-full p-3 text-center">
                            {#if volume.releaseDate != null}
                                {volume.releaseDate.getDate()}.{volume.releaseDate.getMonth() + 1}.{volume.releaseDate.getFullYear()}
                            {:else }
                                TBA
                            {/if}
                        </div>
                    </div>
                </div>
            </div>
        {/each}
    </div>
</div>
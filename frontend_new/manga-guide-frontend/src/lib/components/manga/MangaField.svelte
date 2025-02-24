<script lang="ts">
    let { manga } = $props()
</script>

<a class="card card-hover overflow-hidden h-full w-full" href="/manga/{manga.id}">
    <div class="relative z-0 h-full w-full">
        <img src={manga.pictureUrl} alt={manga.title} class="object-cover h-full w-full"/>
        <div class="absolute z-10 bottom-0 bg-surface-800 opacity-90 h-1/6 w-full flex flex-col justify-center">
            <div class="w-full p-3 truncate text-lg font-semibold">
                {manga.title}
            </div>
        </div>

    </div>
</a>


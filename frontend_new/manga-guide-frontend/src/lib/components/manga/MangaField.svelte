<script lang="ts">
    let { manga } = $props()
</script>

<a class="card card-hover overflow-hidden h-max w-max" href="/manga/{manga.id}">
    <img src={manga.pictureUrl} alt={manga.title}/>
    <div class="absolute bg-gray-950 opacity-60">
        asdkjgahe
    </div>
</a>


<script lang="ts">
    let { children } = $props();
</script>

<div class="h-full w-full p-10">
    <div class="w-full p-4 bg-surface-800 rounded-xl">
        {@render children()}
    </div>
</div>

<script lang="ts">
  //import Text from "$lib/components/Text.svelte";
  import type {PageData} from "../../../.svelte-kit/types/src/routes/$types";

  let num = $state(0)
  let calc = $derived(2 * num)

  let names = ["Hans", "Peter", "Gert", "Jürgen", "Jens", "Georg"]

  let { data }: { data: PageData } = $props()
  data.num
</script>

<!--{#await }-->

<!--{:then i}-->

<!--{/await}-->

{#if num > 5}
    {#each names as name, i}
        <span>{i}, {name}</span>
    {/each}
{/if}


<h1>Welcome to SvelteKit</h1>
<p>Visit <a href="https://svelte.dev/docs/kit">svelte.dev/docs/kit</a> to read the documentation</p>

<button onclick="{() => {num++}}" class="bg-red-500">
    Test
</button>

<span>
    {calc}
</span>
<!-- <Text text="sökjdfngösdfjg" img="https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/640px-Cat03.jpg"/> -->
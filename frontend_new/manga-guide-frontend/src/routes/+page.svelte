<script lang="ts">
    let num = $state(0)
    let calc = $derived(2 * num)

    let names = ["Hans", "Peter", "Gert", "Jürgen", "Jens", "Georg"]
</script>

<!--<div class="w-screen h-screen flex flex-col space-y-5">-->
<!--    {#each names as name, i}-->
<!--        <div class="w-full bg-gray-500 text-white p-2 hover:scale-90 active:bg-gray-800 transition-all duration-300 ease-in">-->
<!--            {name}-->

<!--        </div>-->
<!--    {/each}-->
<!--</div>-->

<button type="button" class="btn variant-filled">
    Button
</button>
<script lang="ts">
    let { text, img } = $props()
</script>

<span>
    {text}
    <img src={img} alt="">
</span>
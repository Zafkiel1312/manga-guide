import type { PageLoad } from "../../../.svelte-kit/types/src/routes"
export const load: PageLoad = async ({params}) => {

    return {num: 1}
}
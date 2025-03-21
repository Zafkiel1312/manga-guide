export interface MangaDto {
    "id": string,
    "title": string,
    "imageUrl": string,
    "author": string[],
    "publisherId": string,
    "releaseDate": Date | null,
    "releasedVolumes": number,
    "boughtVolumes": number,
    "japaneseVolumes": number,
    "finished": boolean,
    "finishedJapanese": boolean,
    "nextVolumeReleaseDate": Date,
    "volumeIds": string[]
}

export interface VolumeDto {
    "id": string,
    "mangaId": string,
    "number": number,
    "releaseDate": Date | null,
    "released": boolean,
    "imageUrl": string
}

export interface MangaSearchDto {
    "mangaSourceId": number,
    "title": string,
    "author": string[],
    "imageUrl": string
}

export const getAllMangas = async () => {
    let url = "http://localhost:8080/manga"

    const response= await fetch(url)

    if (!response.ok) {
        throw new Error(response.statusText)
    }

    return await response.json() as MangaDto[]
}

export const getMangaById = async (id: string) => {
    let url = `http://localhost:8080/manga/${id}`

    const response = await fetch(url)

    if (!response.ok) {
        throw new Error(response.statusText)
    }

    return await response.json() as MangaDto
}

export const getVolumesOfMangaWithId = async (id: string) => {
    let url = `http://localhost:8080/manga/${id}/volumes`

    const response = await fetch(url)

    if (!response.ok) {
        throw new Error(response.statusText)
    }

    return await response.json() as VolumeDto[]
}

export const searchNewManga = async (sourceKey: string, searchString: string) => {
    let url = `http://localhost:8080/manga/search?searchString=${searchString}&sourceKey=${sourceKey}`

    const response = await fetch(url)

    if (!response.ok) {
        throw new Error(response.statusText)
    }

    return await response.json() as MangaSearchDto[]
}

export const createNemManga = async (sourceKey: string, id: string) => {
    let url = `http://localhost:8080/manga?sourceKey=${sourceKey}`
    let body = JSON.stringify({sourceMangaId: id})

    const response = await fetch(url, {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: body
    })

    if (!response.ok) {
        throw new Error(response.statusText)
    }

    return await response.json() as string
}

export const getAllMangaSources = async () => {
    let url = "http://localhost:8080/manga/sources"

    const response= await fetch(url)

    if (!response.ok) {
        throw new Error(response.statusText)
    }

    return await response.json() as string[]
}

/*export const createNemManga = async (id: number) => {
    let url = "http://localhost:8080/manga/mangaPassion"
    let body = JSON.stringify({id: id})

    const response = await fetch(url, {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: body
    })

    if (!response.ok) {
        throw new Error(response.statusText)
    }

    return await response.json() as string
}*/

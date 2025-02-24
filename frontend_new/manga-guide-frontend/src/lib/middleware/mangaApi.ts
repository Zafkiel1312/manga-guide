export interface MangaDto {
    "id": string,
    "title": string,
    "pictureUrl": string,
    "author": string[],
    "publisherId": string,
    "releaseDate": Date,
    "releasedVolumes": number,
    "boughtVolumes": number,
    "japaneseVolumes": number,
    "finished": boolean,
    "finishedJapanese": boolean,
    "nextVolumeReleaseDate": Date,
    "volumeIds": string[]
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
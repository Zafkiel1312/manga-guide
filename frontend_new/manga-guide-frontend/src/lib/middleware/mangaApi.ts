export interface MangaDto {
    "id": string,
    "title": string,
    "pictureUrl": string,
    "author": string[],
    "publisherId": string,
    "releaseDate": string,
    "releasesVolumes": number,
    "boughtVolumes": number,
    "japaneseVolumes": number,
    "finished": boolean,
    "finishedJapanese": boolean,
    "nextVolumeReleaseDate": string,
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
export interface MangaEntryModel {
    "id": string,
    "title": string,
    "author": string,
    "publisher": string,
    "releaseDate": number,
    "picture": string,
    "releasedVolumes": number,
    "ownedVolumes": number,
    "nextVolumeRelease": string
}

export interface CreateMangaEntryModel {
    "title": string,
    "author": string,
    "publisher": string,
    "releaseDate": number,
    "picture": string,
    "releasedVolumes": number,
    "ownedVolumes": number,
    "nextVolumeRelease": string
}

export function compareMangaEntry(a: MangaEntryModel | CreateMangaEntryModel, b: MangaEntryModel | CreateMangaEntryModel) {
    if (a.title < b.title)
        return -1
    if (a.title > b.title)
        return 1
    if (a.author < b.author)
        return -1
    if (a.author > b.author)
        return 1
    return 0
}
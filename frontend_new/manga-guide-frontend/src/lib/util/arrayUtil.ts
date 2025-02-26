export function chunkArray<T>(array: T[], chunkSize: number): T[][] {
    const chunkedArraySize = Math.ceil(array.length / chunkSize)
    const newArray = Array<T[]>(chunkedArraySize)
    for (let i = 0; i < array.length; i += chunkSize) {
        newArray[i / chunkSize] = array.slice(i, i + chunkSize)
    }
    return newArray
}
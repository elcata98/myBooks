export interface Book {

    id: string;

    title: string;
    author: string;
    language: string;

    year: number;
    isbn: string;
    editorial: string;
    collection: string;
    edition: string;
    numVolumes: number;
    volume: number;
    location: string;
    summary: string;
    bookFormat: string // BookFormat enum on backend
}
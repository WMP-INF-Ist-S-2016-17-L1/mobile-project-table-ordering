export interface GeoLocation {
    formatted_address: string;
    address_components: Array<any>;
    geometry: {
        location: Coordinates;
    };
    place_id: string;
}

export interface Coordinates {
    lat: number;
    lng: number;
}

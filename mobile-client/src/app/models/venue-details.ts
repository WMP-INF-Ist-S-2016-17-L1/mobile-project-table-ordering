export interface VenueDetails {
  id?: number;
  query: string;
  lat: number;
  lng: number;
  location?: Location;
  name?: string;
  isRegistered?: boolean;
}

interface Location {
  address: string;
  lat: number;
  lng: number;
  state: string;
  city: string;
  country: string;
}

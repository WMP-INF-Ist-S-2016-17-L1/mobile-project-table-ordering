export interface Venue {
  foursquareId: string;
  name: string;
  location: VenueLocation;
}

export interface VenueWithDetails extends Venue {
  websiteUrl: string;
  rating: number;
}

export interface VenueLocation {
  country: string;
  city: string;
  address: string;
  latitude: number;
  longitude: number;
}

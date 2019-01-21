export interface ReservationRequest {
  id: number;
  numberOfPersons: number;
  dateAndTime: Date;
  message?: string;
  date?: Date;
  time?: Date;
}

export interface NewReservation {
  restaurantApiId: string;
  numberOfPersons: number;
  dateAndTime: Date;
  clientMessage?: string;
}

export interface Date {
    day: number;
    month: number;
    year: number;
}

export interface Time {
    hour: number;
    minute: number;
}

export interface DateTime {
    date: Date;
    time: Time;
}

class DateUtils {

    getDateTime(date: Date, time: Date): Date {
        const minutes = time.getMinutes();
        const hours = time.getHours();
        date.setHours(hours, minutes);

        return date;
    }
}

export default new DateUtils();

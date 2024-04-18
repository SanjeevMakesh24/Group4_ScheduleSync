export const findDayAndMonth = (weekdayName) => {
    const currentDate = new Date();
    const daysOfWeek = ['SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY'];
    const targetDayIndex = daysOfWeek.findIndex(day => day === weekdayName);
    const currentDayOfWeek = currentDate.getDay();
    const difference = targetDayIndex - currentDayOfWeek;
    const targetDate = new Date(currentDate);
    targetDate.setDate(targetDate.getDate() + difference);
    const dayNumber = targetDate.getDate();
    const monthName = targetDate.toLocaleString('default', { month: 'long' });
    return { dayNumber, monthName };
};
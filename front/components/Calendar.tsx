import { Calendar as BigCalendar, momentLocalizer } from "react-big-calendar";
import "react-big-calendar/lib/css/react-big-calendar.css";
import moment from "moment";
import "moment/locale/sr";

export type CalendarEvent = {
  allDay?: boolean | undefined;
  title?: string | undefined;
  start?: Date | undefined;
  end?: Date | undefined;
  resource?: any;
};

interface MyCalendarProps {
  events?: Array<CalendarEvent>;
  onSelectEvent?: (e: CalendarEvent) => void;
}

export function MyCalendar(props: MyCalendarProps) {
  moment.locale("sr");
  const localizer = momentLocalizer(moment); // or globalizeLocalizer
  return (
    <BigCalendar
      culture="sr"
      localizer={localizer}
      messages={{
        agenda: "Raspored",
        allDay: "Ceo dan",
        month: "Mesec",
        day: "Dan",
        today: "Danas",
        previous: "Prethodan",
        next: "Sledeci",
        date: "Datum",
        noEventsInRange: "Nema dogadjaja u opsegu",
        time: "Vreme",
        tomorrow: "Sutra",
        week: "Nedelja",
        work_week: "Radna nedelja",
        yesterday: "Juce",
      }}
      events={props.events}
      startAccessor="start"
      endAccessor="end"
      onSelectEvent={(e) => {
        if (props.onSelectEvent) {
          props.onSelectEvent(e);
        }
      }}
    />
  );
}

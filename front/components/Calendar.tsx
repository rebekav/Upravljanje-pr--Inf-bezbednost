import { Calendar as BigCalendar, momentLocalizer } from "react-big-calendar";
import "react-big-calendar/lib/css/react-big-calendar.css";
import moment from "moment";

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
  const localizer = momentLocalizer(moment); // or globalizeLocalizer
  return (
    <BigCalendar
      localizer={localizer}
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

import { HStack } from "@chakra-ui/layout";
import { GetServerSideProps, NextPage } from "next";
import { Session } from "next-auth";
import { getSession } from "next-auth/client";
import { Dashboard } from "../../../components/layout/Dashboard";
import { getRadniKalendar } from "../../../util/api/sestra";
import { MyCalendar } from "../../../components/Calendar";

interface RadniKalendarProps {
  session: Session;
}

const RadniKalendar: NextPage<RadniKalendarProps> = (props) => {
  const { data, isLoading, error } = getRadniKalendar(props.session);
  console.log(data);
  return (
    <Dashboard>
      {isLoading && !data ? (
        <div>Loading...</div>
      ) : (
        <MyCalendar events={data}></MyCalendar>
      )}
    </Dashboard>
  );
};

export const getServerSideProps: GetServerSideProps = async (ctx) => {
  return { props: { session: await getSession(ctx) } };
};
export default RadniKalendar;

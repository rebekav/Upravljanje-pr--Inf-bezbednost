import {
  Table,
  TableCaption,
  Tbody,
  Th,
  Thead,
  Tr,
  Td,
} from "@chakra-ui/table";
import { HStack } from "@chakra-ui/layout";
import { GetServerSideProps, NextPage } from "next";
import { Session } from "next-auth";
import { getSession } from "next-auth/client";
import { Dashboard } from "../../../components/layout/Dashboard";
import { getRecepti } from "../../../util/api/sestra";
import { ReceptRow } from "../../../components/TableRows/ReceptRow";

interface KorsniciProps {
  session: Session;
}

const Korisnici: NextPage<KorsniciProps> = (props) => {
  const { data, isLoading, error, mutate } = getRecepti(props.session);
  console.log(data);
  return (
    <Dashboard>
      {isLoading ? (
        <div>Loading...</div>
      ) : (
        <Table>
          <TableCaption
            placement="top"
            textAlign="start"
            fontSize="xl"
            fontWeight="bold"
          >
            <HStack lign="stretch">
              <span> Recepti:</span>
            </HStack>
          </TableCaption>
          <Thead>
            <Tr>
              <Th>Naziv</Th>
              <Th>Pacijent</Th>
              <Th>Lekar</Th>
              <Th>Podaci O Pregledu</Th>
              <Th>Vreme</Th>
              <Th>Overen</Th>
              <Th>Akcije</Th>
            </Tr>
          </Thead>
          <Tbody>
            {data?.map((d) => (
              <ReceptRow
                key={d.id}
                k={d}
                session={props.session}
                mutate={mutate}
              />
            ))}
          </Tbody>
        </Table>
      )}
    </Dashboard>
  );
};

export const getServerSideProps: GetServerSideProps = async (ctx) => {
  return { props: { session: await getSession(ctx) } };
};
export default Korisnici;

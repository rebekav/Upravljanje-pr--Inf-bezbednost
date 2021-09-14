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
import { PacijentRow } from "../../../components/TableRows/PacijentRow";
import { getPacijenti } from "../../../util/api/sestra";
import { Select } from "@chakra-ui/react";
import { useState } from "react";

interface PacijentiProps {
  session: Session;
}

const Pacijenti: NextPage<PacijentiProps> = (props) => {
  const [sort, setSort] = useState("0");
  const { data, isLoading, error, mutate } = getPacijenti(props.session, sort);
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
            <HStack lign="stretch" justifyContent="space-between">
              <span> Pacijenti:</span>
              <Select
                w="40"
                value={sort}
                onChange={(e) => setSort(e.target.value)}
              >
                <option value="0">Sortiraj</option>
                <option value="1">Ime rastuce</option>
                <option value="2">Ime opadajuce</option>
                <option value="3">ID rastuce</option>
                <option value="4">ID opadajuce</option>
              </Select>
            </HStack>
          </TableCaption>
          <Thead>
            <Tr>
              <Th>#</Th>
              <Th>Ime</Th>
              <Th>Prezime</Th>
              <Th>Email</Th>
            </Tr>
          </Thead>
          <Tbody>
            {data?.map((k) => (
              <PacijentRow key={k.id} k={k}></PacijentRow>
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
export default Pacijenti;

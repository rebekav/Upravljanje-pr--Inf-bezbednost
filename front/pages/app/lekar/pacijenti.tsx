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
import { getPacijenti } from "../../../util/api/lekar";
import { Button } from "@chakra-ui/button";
import { ZapoceniPregled } from "../../../components/Modal/ZapocniPregled";
import { useDisclosure } from "@chakra-ui/hooks";
import { useState } from "react";
import { zapociPregled } from "../../../util/api/pregled";
import { useRouter } from "next/dist/client/router";

interface PacijentiProps {
  session: Session;
}

const Pacijenti: NextPage<PacijentiProps> = (props) => {
  const router = useRouter();
  const { data, isLoading, error, mutate } = getPacijenti(props.session);
  const { isOpen, onOpen, onClose } = useDisclosure();
  const [izabrani, setIzabrani] = useState(0);
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
              <span> Pacijenti:</span>
            </HStack>
          </TableCaption>
          <Thead>
            <Tr>
              <Th>#</Th>
              <Th>Ime</Th>
              <Th>Prezime</Th>
              <Th>Email</Th>
              <Th>Pregled</Th>
            </Tr>
          </Thead>
          <Tbody>
            {data?.map((k) => (
              <Tr key={k.id}>
                <Td>{k.id}</Td>
                <Td>{k.ime}</Td>
                <Td>{k.prezime}</Td>
                <Td>{k.email}</Td>

                <Td>
                  <Button
                    colorScheme="green"
                    onClick={() => {
                      setIzabrani(k.id);
                      onOpen();
                    }}
                  >
                    Zapocni pregled
                  </Button>
                </Td>
              </Tr>
            ))}
          </Tbody>
        </Table>
      )}

      <ZapoceniPregled
        onClose={onClose}
        isOpen={isOpen}
        session={props.session}
        onAction={(d) => {
          return zapociPregled(props.session, d, izabrani).then((v) => {
            router.push(`/app/pregled/${v}`);
            return v;
          });
        }}
      />
    </Dashboard>
  );
};

export const getServerSideProps: GetServerSideProps = async (ctx) => {
  return { props: { session: await getSession(ctx) } };
};
export default Pacijenti;

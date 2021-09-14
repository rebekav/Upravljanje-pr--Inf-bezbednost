import {
  Table,
  TableCaption,
  Tbody,
  Th,
  Thead,
  Tr,
  Td,
} from "@chakra-ui/table";
import { Button } from "@chakra-ui/button";
import { HStack, Box } from "@chakra-ui/layout";
import { GetServerSideProps, NextPage } from "next";
import { Session } from "next-auth";
import { getSession } from "next-auth/client";
import { Dashboard } from "../../../components/layout/Dashboard";
import { KorisnikRow } from "../../../components/TableRows/KorisnikRow";
import {
  createAdminKlinike,
  createAdmin,
  getKorisnici,
} from "../../../util/api/korisnik";
import { useDisclosure } from "@chakra-ui/react";
import { KCreate } from "../../../components/Modal/KCreate";
import { AdminCreate } from "../../../components/Modal/AdminCreate";

interface KorsniciProps {
  session: Session;
}

const Korisnici: NextPage<KorsniciProps> = (props) => {
  const { data, isLoading, error, mutate } = getKorisnici(props.session);
  const { isOpen, onOpen, onClose } = useDisclosure();
  const {
    isOpen: _isOpen,
    onOpen: _onOpen,
    onClose: _onClose,
  } = useDisclosure();
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
              <span> Korisnici:</span>
              <Box>
                <Button
                  colorScheme="green"
                  onClick={() => {
                    onOpen();
                  }}
                >
                  Dodaj Admina Klinike
                </Button>
                <Button
                  ml="2"
                  colorScheme="green"
                  onClick={() => {
                    _onOpen();
                  }}
                >
                  Dodaj Admina
                </Button>
              </Box>
            </HStack>
          </TableCaption>
          <Thead>
            <Tr>
              <Th>#</Th>
              <Th>Ime</Th>
              <Th>Prezime</Th>
              <Th>Email</Th>
              <Th>Uloge</Th>
              <Th>Aktivan</Th>
              <Th>Akcije</Th>
            </Tr>
          </Thead>
          <Tbody>
            {data?.map((k) => (
              <KorisnikRow
                key={k.id}
                k={k}
                session={props.session}
                mutate={mutate}
              ></KorisnikRow>
            ))}
          </Tbody>
        </Table>
      )}
      <KCreate
        session={props.session}
        onClose={onClose}
        isOpen={isOpen}
        onAction={(d) => {
          return createAdminKlinike(props.session, d).then((v) => {
            return mutate();
          });
        }}
      />
      <AdminCreate
        onClose={_onClose}
        isOpen={_isOpen}
        onAction={(d) => {
          return createAdmin(props.session, d).then((v) => {
            return mutate();
          });
        }}
      />
    </Dashboard>
  );
};

export const getServerSideProps: GetServerSideProps = async (ctx) => {
  return { props: { session: await getSession(ctx) } };
};
export default Korisnici;

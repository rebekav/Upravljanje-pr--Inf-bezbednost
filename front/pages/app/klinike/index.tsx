import { useDisclosure, useToast } from "@chakra-ui/react";
import { Button } from "@chakra-ui/button";
import {
  Table,
  TableCaption,
  Tbody,
  Th,
  Thead,
  Tr,
  Td,
  Tfoot,
} from "@chakra-ui/table";
import { HStack } from "@chakra-ui/layout";
import { GetServerSideProps, NextPage } from "next";
import { Session } from "next-auth";
import { getSession } from "next-auth/client";
import { Dashboard } from "../../../components/layout/Dashboard";
import { KCCreate } from "../../../components/Modal/KCCreate";
import { createKlinika, getKlinike } from "../../../util/api/klinika";

interface KlinikaProps {
  session: Session;
}

const Klinika: NextPage<KlinikaProps> = (props) => {
  const { data, isLoading, error, mutate } = getKlinike(props.session);
  const toast = useToast();
  const { isOpen, onOpen, onClose } = useDisclosure();
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
            <HStack lign="stretch" justifyContent="space-between">
              <span> Klinike:</span>
              <Button
                colorScheme="green"
                onClick={() => {
                  onOpen();
                }}
              >
                Dodaj
              </Button>
            </HStack>
          </TableCaption>
          <Thead>
            <Tr>
              <Th>#</Th>
              <Th>Naziv</Th>
              <Th>Adresa</Th>
              <Th>Opis</Th>
            </Tr>
          </Thead>
          <Tbody>
            {data?.map((k) => (
              <Tr key={k.id}>
                <Td>{k.id}</Td>
                <Td>{k.naziv}</Td>
                <Td>{k.adresa}</Td>
                <Td>{k.opis}</Td>
              </Tr>
            ))}
          </Tbody>
        </Table>
      )}
      <KCCreate
        onClose={onClose}
        isOpen={isOpen}
        onAction={(data) => {
          return createKlinika(props.session, data).then((v) => {
            toast({
              title: "Upsesno",
              description: "Dodata je nova klinika",
              status: "success",
              duration: 3000,
              isClosable: true,
            });
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

export default Klinika;

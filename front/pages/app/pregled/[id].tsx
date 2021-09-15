import {
  Table,
  TableCaption,
  Tbody,
  Th,
  Thead,
  Tr,
  Td,
} from "@chakra-ui/table";
import { Divider, HStack, Text, VStack, Box } from "@chakra-ui/layout";
import { Textarea } from "@chakra-ui/textarea";
import { GetServerSideProps, NextPage } from "next";
import { Session } from "next-auth";
import { getSession } from "next-auth/client";
import { useRouter } from "next/dist/client/router";
import { useState } from "react";
import { Dashboard } from "../../../components/layout/Dashboard";
import { addRecept, getPregled } from "../../../util/api/pregled";
import { Button } from "@chakra-ui/button";
import { ReceptCreate } from "../../../components/Modal/ReceptCreate";
import { useDisclosure } from "@chakra-ui/react";

interface PregledProps {
  session: Session;
}
const Pregled: NextPage<PregledProps> = (props) => {
  const router = useRouter();
  const { id } = router.query;
  const { data, isLoading, mutate } = getPregled(props.session, id);
  const [podaciOPregledu, setPodaciOPregledu] = useState("");
  const { isOpen, onOpen, onClose } = useDisclosure();
  return (
    <Dashboard>
      {isLoading ? (
        <div>Loading...</div>
      ) : (
        <VStack pr="2" alignItems="start" fontSize="lg">
          <HStack w="full" align="stretch" justifyContent="space-between">
            <HStack>
              <Text fontWeight="bold" color="gray.400">
                Lekar:
              </Text>
              <Text> {data?.lekar}</Text>
            </HStack>{" "}
            <HStack>
              <Text fontWeight="bold" color="gray.400">
                Medicinska Sestra:
              </Text>
              <Text> {data?.sestra}</Text>
            </HStack>
            <HStack>
              <Text fontWeight="bold" color="gray.400">
                Pacijent:
              </Text>
              <Text> {data?.pacijent}</Text>
            </HStack>
          </HStack>
          <Divider />
          <HStack>
            <Text fontWeight="bold" color="gray.400">
              Usluga:
            </Text>
            <Text>{data?.usluga}</Text>
          </HStack>
          <Divider />
          <HStack
            w="full"
            alignItems="baseline"
            justifyContent="space-between"
            spacing="8"
          >
            <VStack w="50%" alignSelf="start" alignItems="start">
              <Text fontWeight="bold" color="gray.400" fontSize="xl" pt="1">
                Izvestaj:
              </Text>

              <Textarea
                w="full"
                rows={9}
                resize="none"
                value={podaciOPregledu}
                onChange={(e) => setPodaciOPregledu(e.target.value)}
              ></Textarea>
            </VStack>
            <Table width="50%">
              <TableCaption
                px="unset"
                color="gray.400"
                mt="unset"
                pt="unset"
                placement="top"
                textAlign="start"
                fontSize="xl"
                fontWeight="bold"
              >
                <HStack
                  align="stretch"
                  justifyContent="space-between"
                  alignItems="baseline"
                >
                  <span> Recepti:</span>
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
                  <Th pl="unset">Naziv</Th>
                  <Th>Napomena</Th>
                </Tr>
              </Thead>
              <Tbody>
                {data?.recepti.map((r) => (
                  <Tr key={r.id}>
                    <Td>{r.naziv}</Td>
                    <Td>{r.napomena}</Td>
                  </Tr>
                ))}
              </Tbody>
            </Table>
          </HStack>
          <HStack align="stretch" w="full" justifyContent="end">
            <Button colorScheme="green">Zavrsi pregled</Button>
          </HStack>
          <ReceptCreate
            isOpen={isOpen}
            onClose={onClose}
            onAction={(r) => {
              if (data)
                return addRecept(props.session, data?.id, r).then((v) => {
                  return mutate();
                });
              return new Promise((r, reject) => {
                reject("No data present");
              });
            }}
          ></ReceptCreate>
        </VStack>
      )}
    </Dashboard>
  );
};

export const getServerSideProps: GetServerSideProps = async (ctx) => {
  return { props: { session: await getSession(ctx) } };
};
export default Pregled;

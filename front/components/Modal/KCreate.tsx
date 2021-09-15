import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalCloseButton,
  ModalFooter,
} from "@chakra-ui/modal";
import { Button } from "@chakra-ui/button";
import { Input, Text, Select } from "@chakra-ui/react";
import { Box, Stack } from "@chakra-ui/layout";
import { useState } from "react";
import { KorisnikReqDTO } from "../../util/types/korisnik";
import { getKlinike } from "../../util/api/klinika";
import { Session } from "next-auth";
interface KCreateProps {
  isOpen: boolean;
  onClose: () => void;
  onAction: (data: KorisnikReqDTO) => Promise<any>;
  session: Session;
}

export function KCreate(props: KCreateProps) {
  const { data: klinike, isLoading, error, mutate } = getKlinike(props.session);
  const [data, setData] = useState<KorisnikReqDTO>({
    ime: "",
    prezime: "",
    adresa: "",
    email: "",
    idKlinika: 1,
    identifikator: "",
    pass: "",
    telefon: "",
  });
  console.log(klinike);
  return (
    <Modal isOpen={props.isOpen} onClose={props.onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Dodaj Admina Klinike</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <Stack spacing={3}>
            <Box>
              <Text pb="2">Naziv:</Text>
              <Input
                placeholder="Ime:"
                value={data["ime"]}
                onChange={(e) => setData({ ...data, ime: e.target.value })}
              ></Input>
            </Box>
            <Box>
              <Text pb="2">Prezime:</Text>
              <Input
                placeholder="Prezime:"
                value={data["prezime"]}
                onChange={(e) => setData({ ...data, prezime: e.target.value })}
              ></Input>
            </Box>
            <Box>
              <Text pb="2">Identifikator:</Text>
              <Input
                placeholder="Identifikator:"
                value={data["identifikator"]}
                onChange={(e) =>
                  setData({ ...data, identifikator: e.target.value })
                }
              ></Input>
            </Box>
            <Box>
              <Text pb="2">Email:</Text>
              <Input
                placeholder="Email:"
                value={data["email"]}
                onChange={(e) => setData({ ...data, email: e.target.value })}
              ></Input>
            </Box>
            <Box>
              <Text pb="2">Lozinka:</Text>
              <Input
                placeholder="Lozinka:"
                value={data["pass"]}
                onChange={(e) => setData({ ...data, pass: e.target.value })}
              ></Input>
            </Box>
            <Box>
              <Text pb="2">Adresa:</Text>
              <Input
                placeholder="Adresa:"
                value={data["adresa"]}
                onChange={(e) => setData({ ...data, adresa: e.target.value })}
              ></Input>
            </Box>
            <Box>
              <Text pb="2">Telefon:</Text>
              <Input
                placeholder="Telefon:"
                value={data["telefon"]}
                onChange={(e) => setData({ ...data, telefon: e.target.value })}
              ></Input>
            </Box>
            <Box>
              <Text pb="2">Klinika:</Text>
              <Select
                value={data["idKlinika"]}
                onChange={(e) =>
                  setData({ ...data, idKlinika: Number(e.target.value) })
                }
              >
                {!isLoading &&
                  klinike?.map((k) => (
                    <option key={k.id} value={k.id}>
                      {k.naziv}
                    </option>
                  ))}
              </Select>
            </Box>
          </Stack>
        </ModalBody>

        <ModalFooter>
          <Button colorScheme="blue" mr={3} onClick={props.onClose}>
            Zatvori
          </Button>
          <Button
            variant="ghost"
            onClick={() => {
              props.onAction(data).then((d) => {
                setData({
                  ime: "",
                  prezime: "",
                  adresa: "",
                  email: "",
                  idKlinika: 1,
                  identifikator: "",
                  pass: "",
                  telefon: "",
                });
                props.onClose();
              });
            }}
          >
            Dodaj
          </Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  );
}

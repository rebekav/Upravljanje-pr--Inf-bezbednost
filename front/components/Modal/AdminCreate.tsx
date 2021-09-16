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
import { Input, Text } from "@chakra-ui/react";
import { Box, Stack } from "@chakra-ui/layout";
import { useState } from "react";
import { KorisnikReqDTO } from "../../util/types/korisnik";

interface AdminCreateProps {
  isOpen: boolean;
  onClose: () => void;
  onAction: (data: KorisnikReqDTO) => Promise<any>;
}

export function AdminCreate(props: AdminCreateProps) {
  const [data, setData] = useState<KorisnikReqDTO>({
    ime: "",
    prezime: "",
    adresa: "",
    email: "",
    idKlinika: 0,
    identifikator: "",
    pass: "",
    telefon: "",
  });
  return (
    <Modal isOpen={props.isOpen} onClose={props.onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Dodaj Admina</ModalHeader>
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
          </Stack>
        </ModalBody>

        <ModalFooter>
          <Button colorScheme="blue" mr={3} onClick={props.onClose}>
            Zatvori
          </Button>
          <Button
            variant="ghost"
            onClick={() => {
              props
                .onAction(data)
                .then((d) => {
                  setData({
                    ime: "",
                    prezime: "",
                    adresa: "",
                    email: "",
                    idKlinika: 0,
                    identifikator: "",
                    pass: "",
                    telefon: "",
                  });
                  props.onClose();
                })
                .catch((err) => {
                  console.log(err);
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

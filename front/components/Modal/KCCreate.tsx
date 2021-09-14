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
import { Input, Text, Textarea } from "@chakra-ui/react";
import { Box, Stack } from "@chakra-ui/layout";
import { createKlinika } from "../../util/api/klinika";
import { useState } from "react";
import { KlinikaDtoReq } from "../../util/types/klinika";
import { Session } from "next-auth";
interface KCCreateProps {
  isOpen: boolean;
  onClose: () => void;
  onAction: (data: KlinikaDtoReq) => Promise<any>;
}

export function KCCreate(props: KCCreateProps) {
  const [data, setData] = useState<KlinikaDtoReq>({
    naziv: "",
    adresa: "",
    opis: "",
  });
  return (
    <Modal isOpen={props.isOpen} onClose={props.onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Dodaj Kliniku</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <Stack spacing={3}>
            <Box>
              <Text pb="2">Naziv:</Text>
              <Input
                placeholder="Naziv:"
                value={data["naziv"]}
                onChange={(e) => setData({ ...data, naziv: e.target.value })}
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
              <Text pb="2">Opis:</Text>
              <Textarea
                placeholder="Opis:"
                resize="none"
                rows={5}
                value={data["opis"]}
                onChange={(e) => setData({ ...data, opis: e.target.value })}
              ></Textarea>
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
                setData({ naziv: "", opis: "", adresa: "" });
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

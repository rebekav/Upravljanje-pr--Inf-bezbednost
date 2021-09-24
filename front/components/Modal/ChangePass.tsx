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
import { Input, Text, Alert, AlertTitle, AlertIcon } from "@chakra-ui/react";
import { Box, Stack } from "@chakra-ui/layout";
import { useState } from "react";

interface ChangePassProps {
  isOpen: boolean;
  onClose: () => void;
  onAction: (lozinka: string, staraLozinka: string) => Promise<any>;
}

export function ChangePass(props: ChangePassProps) {
  const [lozinka, setLozinka] = useState("");
  const [poruka, setPoruka] = useState("");
  const [staraLozinka, setStaraLozinka] = useState("");
  return (
    <Modal
      isOpen={props.isOpen}
      onClose={props.onClose}
      closeOnEsc={false}
      closeOnOverlayClick={false}
    >
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Promeni Lozinku</ModalHeader>
        <ModalBody>
          <Stack spacing={3}>
            {!!poruka && (
              <Box>
                <Alert status="error">
                  <AlertIcon />
                  <AlertTitle>{poruka}</AlertTitle>
                </Alert>
              </Box>
            )}
            <Box>
              <Text pb="2">Stara lozinka:</Text>
              <Input
                type="password"
                placeholder="Lozinka:"
                value={staraLozinka}
                onChange={(e) => setStaraLozinka(e.target.value)}
              ></Input>
            </Box>
            <Box>
              <Text pb="2">Nova lozinka:</Text>
              <Input
                type="password"
                placeholder="Lozinka:"
                value={lozinka}
                onChange={(e) => setLozinka(e.target.value)}
              ></Input>
            </Box>
          </Stack>
        </ModalBody>

        <ModalFooter>
          <Button
            variant="ghost"
            onClick={() => {
              props.onAction(lozinka, staraLozinka).then((d) => {
                if (d.poruka != "Uspesno!") {
                  setPoruka(d.poruka);
                } else {
                  props.onClose();
                }
              });
            }}
          >
            Promeni Lozinku
          </Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  );
}

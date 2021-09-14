import type { AppProps, NextWebVitalsMetric } from "next/app";
import { Provider } from "next-auth/client";
import { ChakraProvider } from "@chakra-ui/react";
import { Progress } from "@chakra-ui/react";
import { useRouter } from "next/dist/client/router";
import { useEffect, useState } from "react";

function MyApp({ Component, pageProps }: AppProps) {
  const router = useRouter();
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    router.events.on("routeChangeStart", (eve) => {
      setLoading(true);
    });

    router.events.on("routeChangeComplete", (eve) => {
      setLoading(false);
    });
  });
  return (
    <Provider session={pageProps.session}>
      <ChakraProvider>
        <Progress
          size="xs"
          isIndeterminate={loading}
          pos="fixed"
          width="full"
          colorScheme="blue"
        />
        <Component {...pageProps} />
      </ChakraProvider>
    </Provider>
  );
}
export function reportWebVitals(metric: NextWebVitalsMetric) {
  console.log(metric);
}
export default MyApp;

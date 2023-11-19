import {Flex, Text, Button, Box, Container, Link} from '@radix-ui/themes';
export default function AmiGoMainComponent() {
    return (
        <Box>
            <Container size="1">
                <Link href={"/pages/login"}>Login</Link>
                <Flex direction="column" gap="3">
                    <Text size="1">The quick brown fox jumps over the lazy dog.</Text>
                    <Text size="2">The quick brown fox jumps over the lazy dog.</Text>
                    <Text size="3">The quick brown fox jumps over the lazy dog.</Text>
                    <Text size="4">The quick brown fox jumps over the lazy dog.</Text>
                    <Text size="5">The quick brown fox jumps over the lazy dog.</Text>
                    <Text size="6">The quick brown fox jumps over the lazy dog.</Text>
                    <Text size="7">The quick brown fox jumps over the lazy dog.</Text>
                    <Text size="8">The quick brown fox jumps over the lazy dog.</Text>
                    <Text size="9">The quick brown fox jumps over the lazy dog.</Text>
                </Flex>
                    <Box py="9" />
            </Container>
        </Box>
    );
}
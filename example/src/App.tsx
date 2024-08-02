import { useState, useEffect } from 'react';
import { StyleSheet, View, Text } from 'react-native';
import { getAutoStartPermission } from 'react-native-auto-start';

export default function App() {
  const [result, setResult]: any = useState<number | undefined>();

  useEffect(() => {
    getAutoStartPermission();
    setResult('hello');
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});

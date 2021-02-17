import * as React from 'react';

import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import VnptEkyc from "react-native-vnpt-ekyc"


export default function App() {
  const [result, setResult] = React.useState<number | undefined>();
  console.log("VnptEkyc", VnptEkyc)

  const handleEkyc = () => {
    VnptEkyc.ekyc((result) => console.log("result", result))
  }
  return (
    <View style={styles.container}>
      <TouchableOpacity  onPress={handleEkyc}>
      <Text>Result: {result}</Text>
    </TouchableOpacity>
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

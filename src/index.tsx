import { NativeModules } from 'react-native';

type VnptEkycType = {
  multiply(a: number, b: number): Promise<number>;
};

const { VnptEkyc } = NativeModules;

export default VnptEkyc as VnptEkycType;

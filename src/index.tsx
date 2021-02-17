import { NativeModules } from 'react-native';

type VnptEkycType = {
  ekyc(): Promise<any>;
};

const { VnptEkyc } = NativeModules;

export default VnptEkyc as VnptEkycType;
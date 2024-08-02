import { NativeModules } from 'react-native';

export async function getAutoStartPermission(): Promise<void> {
  try {
    await NativeModules.AutoStart.addAutoStartup();
  } catch (error) {}
}


#ifdef RCT_NEW_ARCH_ENABLED
#import "RNAutoStartSpec.h"

@interface AutoStart : NSObject <NativeAutoStartSpec>
#else
#import <React/RCTBridgeModule.h>

@interface AutoStart : NSObject <RCTBridgeModule>
#endif

@end

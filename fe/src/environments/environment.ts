// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  // apiURL: 'http://172.16.2.188:8080/devicemanagement',
  apiURL:'http://localhost:8080',
  firebaseConfig : {
    apiKey: "AIzaSyD4Z-wLcrt-3QODRyFJXudxnf_SBGjeLkQ",
    authDomain: "devicemanagementsystem-68d82.firebaseapp.com",
    projectId: "devicemanagementsystem-68d82",
    storageBucket: "devicemanagementsystem-68d82.appspot.com",
    messagingSenderId: "100332355556",
    appId: "1:100332355556:web:fe44a64b61a1c63fa9d1f1",
    measurementId: "G-H39LJZLKSN"
  }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.

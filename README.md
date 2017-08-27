# FireBaseAuthDbStoragewithButterKnief
Firebase authentication via Email and password and how to save data in database.In this project i have implemented butterknief for the layout.

Project-level build.gradle (<project>/build.gradle):

buildscript {
  dependencies {
    // Add this line
    classpath 'com.google.gms:google-services:3.1.0'
  }
}
App-level build.gradle (<project>/<app-module>/build.gradle):

...
// Add to the bottom of the file
apply plugin: 'com.google.gms.google-services'
includes Analytics by default help_outline
Finally, press "Sync now" in the bar that appears in the IDE:



{
  "rules": {
    ".read": "true",
    ".write": "true"
  }
}

Authentication method should enabled
Include google.service.json file In your "app" Directory.


Include this gradle for butterknief.

    compile 'com.jakewharton:butterknife:8.8.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.0'
    
    
    Here is complete set of gradle
    
    dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.google.firebase:firebase-database:11.0.4'
     compile 'com.google.firebase:firebase-core:11.0.4'
    compile 'com.google.firebase:firebase-auth:11.0.4'
    compile 'com.android.support:cardview-v7:25.3.1'
    compile 'com.android.support:appcompat-v7:25.3.1'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    compile 'com.android.support:recyclerview-v7:25.3.1'
    compile 'com.jakewharton:butterknife:8.8.0'
    compile 'com.firebase:firebase-client-android:2.5.2'

    testCompile 'junit:junit:4.12'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.0'
}
apply plugin: 'com.google.gms.google-services'

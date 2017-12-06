node {
  // Mark the code checkout 'stage'....
  stage 'Obtaining Source Code From Repository'
    deleteDir()

  stage 'Building the App'
          if(isUnix()){
            sh "./gradlew assembleDebug"
            sh "./scripts/upload-app.sh"
          }
          else{
            bat "./gradlew assembleDebug"
            bat "./scripts/upload-app.bat"
          }

  //branch name from Jenkins environment variables
  echo "My branch is: b"

  stage 'Running Tests'
      if (isUnix()) {
      //build your gradle flavor, passes the current build number as a parameter to gradle

          parallel('Run appium tests': {
              sh "./gradlew testDebug --tests=tests.TestRunner"
              },
             )
          }
      else {
          parallel('Run appium tests': {
                bat "./gradlew testDebug --tests=tests.TestRunner"
                },
             )
      }

    stage 'Archiving Artifacts'
    //tell Jenkins to archive the apks
    archiveArtifacts artifacts: 'app/build/outputs/apk/*.apk', fingerprint: true

    stage 'Merging to Master'

    if(isUnix()){
              sh """"git checkout master \
              git merge ${branchName} \
              git commit -am ${commitMsg} and merged to master" \
              git push origin master"""
         }
         else{
              bat(/git checkout master
              git merge ${branchName}
              git commit -am ${commitMsg} and merged to master"
              git push origin master/)
         }

    stage 'clean'
     if(isUnix()){
          sh "./gradlew clean"
     }
     else{
          bat "./gradlew clean"
     }
}

// Pulls the android flavor out of the branch name the branch is prepended with /QA_
//build your gradle flavor, passes the current build number as a parameter to gradle


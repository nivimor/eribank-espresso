node {
  // Mark the code checkout 'stage'....
  stage 'Obtaining Source Code From Repository'
    deleteDir()
   checkout([$class: 'GitSCM', branches: [[name: '*/feature_branch']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '9012e5cc-475f-4e1f-959c-4f5997eeae70', url: 'https://github.com/nivimor/eribank-espresso.git']]])

    def branchName = "feature_branch"
    def commit = bat(returnStdout: true, script: 'git log -1 --oneline').trim()
    List commitMsgPre = commit.split(" ")
    String commitMsg = commitMsgPre.getAt(-1)
    bat "echo this is the msg ${commitMsg}"
    bat "echo this the branch %branchName%"

  stage 'Building the App'
          if(isUnix()){
            sh "./gradlew assembleDebug"
            sh "./scripts/upload-app.sh"
          }
          else{
            bat "gradlew assembleDebug"
            bat "scripts/upload-app.bat"
          }

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
                bat "gradlew testDebug --tests tests.TestRunner"
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
              git push https://$GIT_HUB_USER:$GIT_HUB_PASS@github.com/nivimor/eribank-espresso.git master"""
         }
         else{
              bat(/git checkout master
              git merge %branchName%
              git commit -am "${commitMsg} and merged to master"
              git push https://%GIT_HUB_USER%:%GIT_HUB_PASS%@github.com/nivimor/eribank-espresso.git master/)
         }

    stage 'clean'
     if(isUnix()){
          sh "./gradlew clean"
     }
     else{
          bat "gradlew clean"
     }
}

// Pulls the android flavor out of the branch name the branch is prepended with /QA_
//build your gradle flavor, passes the current build number as a parameter to gradle


node {
  // Mark the code checkout 'stage'....
  stage 'Obtaining Source Code From Repository'
    deleteDir()
  // Checkout code from repository and update any submodules
  checkout([$class: 'GitSCM', branches: [[name: '**']],
    doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'LocalBranch', localBranch: "**"]],
    submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'a8aa2462-fabf-4eb5-bf0b-bd4bec75bdda', url: 'https://github.com/nivimor/eribank-espresso.git']]])

    def branchName
    if(isUnix()){
      branchName = sh(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD').trim()
    } else {
      branchName = bat(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD').trim()
    }
    echo "this is the branch ${branchName}"

    def commitMsg
    if(isUnix()){
          commitMsg = sh(returnStdout: true, script: 'git log -1 --pretty=%B').trim()
        } else {
          commitMsg = bat(returnStdout: true, script: 'git log -1 --pretty=%%B').trim()
        }
        echo "this is the msg ${commitMsg}"
    bat "echo this is  %commitMsg%"
    bat "echo this is %branchName%"

  stage 'Building the App'
          if(isUnix()){
            sh "./gradlew assembleDebug"
            sh "./gradlew assembleAndroidTest"
            sh "./scripts/upload-app.sh"
          }
          else{
            bat "./gradlew assembleDebug"
            bat "./gradlew assembleAndroidTest"
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
              'Run Espresso Test': {
                sh "./scripts/run-tests.sh"
              })
          }
      else {
          parallel('Run appium tests': {
                bat "./gradlew testDebug --tests=tests.TestRunner"
                },
                'Run Espresso Test': {
                    bat "./scripts/run-tests.bat"
             })
      }

    stage 'Archiving Artifacts'
    //tell Jenkins to archive the apks
    archiveArtifacts artifacts: 'app/build/outputs/apk/*.apk', fingerprint: true


    stage 'Merging to Master'
        bat "SET PROJECT_PATH="%HOMEPATH\AndroidStudioProjects\eribank-espresso-ci%"
        cd %PROJECT_PATH%
        git checkout master
        git merge %branchName%
        git commit -am "%commitMsg% and merged to master"
        git push origin master"

    stage 'Publishing Artifacts'

    stage 'clean'
        bat "./gradlew clean"
}

// Pulls the android flavor out of the branch name the branch is prepended with /QA_
//build your gradle flavor, passes the current build number as a parameter to gradle


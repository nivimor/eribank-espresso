echo ${TRAVIS_BRANCH}
git checkout -b master
git merge ${BRANCH}
git commit -am "${TRAVIS_COMMIT_MESSAGE} and merged to master"
git push https://${GIT_AUTH}@github.com/nivimor/eribank-espresso.git master
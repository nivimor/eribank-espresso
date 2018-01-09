git checkout -t -b master origin/master
git merge ${TRAVIS_BRANCH}
git commit -am "${TRAVIS_COMMIT} and merged to master"
git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/nivimor/eribank-espresso.git master
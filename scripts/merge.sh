git config user.name "${GIT_USERNAME}"
git config user.password "${GIT_PASSWORD}"
git checkout -b master
git merge ${TRAVIS_BRANCH}
git commit -am "${TRAVIS_COMMIT_MESSAGE} and merged to master"
git push origin master